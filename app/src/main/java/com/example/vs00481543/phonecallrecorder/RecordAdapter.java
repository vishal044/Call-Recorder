package com.example.vs00481543.phonecallrecorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static android.support.v4.content.FileProvider.getUriForFile;

/**
 * Created by VS00481543 on 03-11-2017.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    List<CallDetails> callDetails;
    Context context;
    SharedPreferences pref;
    String checkDate = "";

    public RecordAdapter(List<CallDetails> callDetails, Context context) {
        this.callDetails = callDetails;
        this.context = context;
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number, time, date,name;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date1);
            name = (TextView) itemView.findViewById(R.id.name1);
            number = (TextView) itemView.findViewById(R.id.num);
            time = (TextView) itemView.findViewById(R.id.time1);
        }

        public void bind(final String dates, final String number, final String times) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "Clicked on " + number, Toast.LENGTH_SHORT).show();

                    String path = Environment.getExternalStorageDirectory() + "/My Records/" + dates + "/" + number + "_" + times + ".mp4"  ;
                    Log.d("path", "onClick: "+path);
//                    Uri uri = Uri.parse(path);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    File file = new File(path);
//                    intent.setDataAndType(Uri.fromFile(file), "audio/*");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(getUriForFile(context,"com.example.vs00481543.phonecallrecorder",file), "audio/*");
                    context.startActivity(intent);

                    pref.edit().putBoolean("pauseStateVLC",true).apply();

                    /*FileInputStream fis=null;
                    MediaPlayer mp=new MediaPlayer();
                    try {
                        fis=new FileInputStream(path);
                        mp.setDataSource(fis.getFD());
                        fis.close();
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                        }
                    });*/
                }
            });
        }
    }

    @Override
    public RecordAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder viewHolder = null;
        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = layoutInflator.inflate(R.layout.record_list, parent, false);
                viewHolder = new MyViewHolder(v1);
                break;
            /*case 1:
                View v2 = layoutInflator.inflate(R.layout.record_noname_list, parent, false);
                viewHolder = new MyViewHolder(v2);
                break;*/
            case 2:
                View v3 = layoutInflator.inflate(R.layout.date_layout, parent, false);
                viewHolder = new MyViewHolder(v3);
                break;
            /*case 3:
                View v4 = layoutInflator.inflate(R.layout.date_noname_layout, parent, false);
                viewHolder = new MyViewHolder(v4);
                break;*/
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecordAdapter.MyViewHolder holder, int position) {

        CallDetails cd1 = callDetails.get(position);
        String n=cd1.getNum();
        String name=new CommonMethods().getContactName(n,context);
        String name2="Unknown";
        Log.d("Names", "onBindViewHolder: "+name);
        holder.bind(cd1.getDate1(), cd1.getNum(), cd1.getTime1());
        switch (getItemViewType(position)) {
            case 0:
                if(name!=null && !name.equals("")) {
                    holder.name.setText(name);
                    holder.name.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                }
                else {
                    holder.name.setText(name2);
                    holder.name.setTextColor(context.getResources().getColor(R.color.red));
                }
                holder.number.setText(callDetails.get(position).getNum());
                holder.time.setText(callDetails.get(position).getTime1());
                break;
            /*case 1:
                holder.number.setText(callDetails.get(position).getNum());
                holder.time.setText(callDetails.get(position).getTime1());
                break;*/
            case 2:
                holder.date.setText(callDetails.get(position).getDate1());
                if(name!=null && !name.equals("")) {
                    holder.name.setText(name);
                    holder.name.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                }
                else {
                    holder.name.setText(name2);
                    holder.name.setTextColor(context.getResources().getColor(R.color.red));
                }
                holder.number.setText(callDetails.get(position).getNum());
                holder.time.setText(callDetails.get(position).getTime1());
                break;
            /*case 3:
                holder.date.setText(callDetails.get(position).getDate1());
                holder.number.setText(callDetails.get(position).getNum());
                holder.time.setText(callDetails.get(position).getTime1());
                break;*/
        }
    }

    @Override
    public int getItemCount() {
        return callDetails.size();
    }

    public int getItemViewType(int position) {
        CallDetails cd = callDetails.get(position);
        String dt = cd.getDate1();
        Log.d("Adapter", "getItemViewType: " + dt);
        Log.d("Adapter", "getItemViewType: " + pref.getString("date", ""));
        // String checkDate=pref.getString("date","");

        try {
            if (position!=0 && cd.getDate1().equalsIgnoreCase(callDetails.get(position - 1).getDate1())) {
                checkDate = dt;
                //pref.edit().putString("date",dt).apply();
                Log.d("Adapter", "getItemViewType: in if condition" + pref.getString("date", ""));
                return 0;
                /*if(name1!=null && !name1.equals(""))
                    return 0;
                else
                    return 1;*/
            } else {
                checkDate = dt;
                //pref.edit().putString("date",dt).apply();
                Log.d("Adapter", "getItemViewType: in else condition" + pref.getString("date", ""));
               /* if(name1!=null && !name1.equals(""))
                    return 2;
                else
                    return 3;*/
               return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }
}
