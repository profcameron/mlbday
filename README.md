# MLB Days
## Inspiration
I've always enjoyed birthdays. I was looking for a Java project to do, because it's been a few months since I was laid off and I have been so focused on Power BI and databases. That said, I am a data conversion geek, I love taking data and transmogrifying it.

I got the idea to play around with birthdays. I like sports, so I wanted to see if there were some free open sites where I could pull data (I found sportsdata.io). This can be altered to use other sports as well.

## Some key pieces features so far:
* Maven/IntelliJ environment
* Uses a command-line menu. Created a little driver class for it.
* Using the Spring framework for API calls.
* API Key needs to be set as a Windows Environment Variable named `SportsDataIOApiKey` - alternately, you could choose to just set it in Main or in application.properties, but I wanted to demonstrate good procedure and keep the API key private. If you clone this program, never ever share your API key in a repository. 
* Using Jackson to make life easier.
* Data needed parsing - by default you got EVERYONE in a team's organization (there were 277 players in the Mets organization). I wanted to focus on the folks in and close to the majors, so for now I exclude people in the minors and on non-roster invites (Note: non-roster invites don't matter during the season but might in February/March; it's a baseball thing).
* Had to create and set up the constructor for the Spring call, also overrode the toString and compareTo.
* Sorting proved to be difficult. I used a old programmer trick to get the date into a sortable integer. Multiply the year by 10000. Multiply the month times 100. Add the year, month, and day. So, January 1, 2002 would be 20020101 and January 14, 2002 would be 20020114. You can now sort by that number. May not be necessary right now; but I wanted to add it in case we wanted to print to the screen.
* Generate a series of calendar events (starting with the first occurrence at the birth year for each player) - create a repeating event for each player, with their name and year of birth.
* Events have the birth year appended rather than the age (otherwise, the player age would be stuck in time)
* Automatically generates a file in .ICS format. This is a standard import you can use in most calendar applications. [Info about ICS Files.](https://fileinfo.com/extension/ics)
* Folder includes a sample ICS file for New York Mets as of August 10, 2024. Hopefully this is the roster for the 2024 World Series champions.

## Future plans
* Give the option to choose output as a Markdown file
* Give user the option to create it as a series of events (as currently constituted) or as a full calendar (which would require setting a name and a few other properties)
* Use the Google API to create and load a calendar. As team rosters change all the time, could also even remove players (maybe just clear out the calendar and reload from the real data to get rid of any former players?)

## What are the important files?
|FILE|Description|
|-|-|
|CalendarProcessor.java|Contains methods for generating calendars|
|Main.java|Main driver program|
|PlayerModel.java|Matches the data from the sportsdata.io API|
|TeamPicker.java|Menu class to allow user to select a team to process|