# Call-Recorder

#### Recorder Features
1.	Records incoming/outgoing calls which might be single or conference calls.
2.	Stores all recordings in internal storage in a folder “MyRecords” which further has folders according to the dates.
3.	Displays the dates and call recording logs under each date.
4.	Displays the contact names as saved by user in phone contacts.
5.	On clicking the particular contact ,user has option to chose which media player to play it on. 
6.	Has a switch to turn the recorder on/off.
7.	Runtime permissions implemented.

#### Compatibility
The application is fully compatible till android 6. Some audio glitches can occur in higher android versions due to restrictions on accessing the audio stream of the other end in higher android versions.
Android 7 compatible app will come out soon.

#### Permissions
Runtime permissions are implemented in code for following permissions
1. READ_CONTACTS
2. RECORD_AUDIO
3. READ_EXTERNAL_STORAGE
4. WRITE_EXTERNAL_STORAGE
5. READ_PHONE_STATE

#### Database
SQLite database is used for saving the list of phone number, date and time which is futher displayed on screen via Recycler View.
Singleton Class is implemented to the database handler.

#### Broadcast Receiver
A receiver class is made to handle the broadcasts which is registered in the manifest.
The application receives broadcasts on particularly 3 events when phone is :
1. "Ringing".
2. "Picked up"
3. "Hung up"

#### Player
On clicking a call log, a menu pops up giving option to the user, to chose from various media payers on device.
FileReader class is used to give the access of mp4 file to music players on device.


