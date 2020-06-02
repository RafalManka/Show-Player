Show Player App
===============

Project consists of 3 modules
- **core** - Shared code between app and tv modules. Contains networking, deserialisation,
ViewModels and more. It uses Retrofit and Coroutines for networking. ViewModel and LiveData, Gson
for deserialisation.
- **app** - Android app UI. It uses exoplayer for video playback, CardView for displaying search
list, SearchView to implement search in actionBar menu, Picasso for loading images.
- **tv** - AndroidTV app. It uses Leanback for UI and Picasso for loading images.



Architecture
------------

Both app and tv share code contained inside core module. This architecture choice was made in order
to maximise code reusability. App and tv modules do not depend on each other directly.


        |-----------|
        |    core   |
        |-----------|
         |         |
         |         |
     |-----|    |-----|
     | app |    |  tv |
     |-----|    |-----|


App module
==========

Run app module on a phone or a simulator. **The task stated, that a list should load after 3
characters are typed.** Since there is only few videos in the list, I decided to load all video on
screen start and filter movies only if typed 3 characters or more. Otherwise the app might appear
as if missing content. **The task also described the list item to show category**. Since there was no category in the json payload I decided not to substitute it with a subtitle. This solution also looked better with current implementation of UI design.

Portrait
----------

![Search portrait][search]

Landscape
----------
In landscape mode content displays in two columns.

![Search landscape][searchLandscape]

Player
------

**The task was to always play the same video.** I assumed this is to make the task easier.
So in the actual implementation a selected video from the list plays.

In order to maximise viewing area actionbar alwasy displays in portrait mode, but in landscape mode
it hides along with player controller.

![Player][player]


TV
--

Tv app created with the use of Leanback library. Data is populated with the same service and 
ViewModel as in the Android App. Additionally background image is displaying when user navigates 
from one section to another.

![TV][tv]

[search]: documentation/app-search.png
[searchLandscape]: documentation/app-search-landcape.png
[player]: documentation/app-player.png
[tv]: documentation/tv-app.png
