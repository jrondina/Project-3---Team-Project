Welcome to News-Ish!
this is a New York Times news reader app that allows the user to schedule notifications and bookmark articles for later reading.

![Project Screenshot](https://github.com/jrondina/Project-3---Team-Project/blob/master/Screenshot_20160819-152850.png)

The app has the following functionality:
- User can read from the New York Times sections Top News, Business, World, Sports, Technology, Politics, Science, and Entertainment
- The user can scroll through a recycler view of all the articles in a section
- The user can bookmark articles from within the section view and the article detail
- the user can share the article url to social media 
- The user can access all the sections via the nav drawer
- Notifications for the latest Top News story that can be given a frequency in the settings page

Future Features?:
- ability to search the NYT for articles
- more sources using the NewsAPI
- Settings page to change order of sections in view pager and change what sources are active
- more fine grained notifications settings





Known Bugs:
- crashes on returning to Portrait Mode
- Bookmarks section does not reload and remove de-bookmarked items
- Last two loaded section fragments do not reload when returning from another activity
- Also do not reload after sharing (another intent based thing)
- web view can be clunky
- UserPreferences can cause a crash on the initial app launch (not being initialized but then accessed)
- Settings page unavailable from the Article Detail
- Search not functional
- sometimes un-bookmarking two items in a row can cause a crash
