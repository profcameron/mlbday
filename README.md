# MLB Days
I've always enjoyed birthdays. I was looking for a Java project to do, because it's been a few months since I was laid off and I have been so focused on Power BI and databases.

It happened to be my cat's birthday, and it gave me the idea to play around with birthdays. I also like sports, so I wanted to see if there were some free open sites where I could pull data (I found sportsdata.io).

If you clone it, never ever share your API key in a repository. In my case, I set my API Key up as a Windows environment variable to obscure it. It's also a pain when I want to demonstrate it on a new machine, but you can always just replace my system call with your key.

Some key pieces features so far:
* So far I am using a command-line menu. Created a little driver class for it.
* I am using the Spring framework for API calls.
* Using Jackson to make life easier.
* Data needed parsing - by default you got EVERYONE in a team's organization (there were 277 players in the Mets organization). I wanted to focus on the folks in and close to the majors, so for now I exclude people in the minors and on non-roster invites (Note: non-roster invites don't matter during the season but might in February/March; it's a baseball thing).
* Had to create and set up the constructor for the Spring call, also overrode the toString and compareTo.
* Sorting proved to be difficult. I used a old programmer trick to get the date into a sortable integer. Multiply the year by 10000. Multiply the month times 100. Add the year, month, and day. So, January 1, 2002 would be 20020101 and January 14, 2002 would be 20020114. You can now sort by that number.

Future plans
* I want to also then turn this into something that can be imported into a Google calendar. Still in progress, but you can generate a file in either .ics or .csv for importing.
* Maybe in the future use the Google API to create and load a calendar. As team rosters change all the time, could also even remove players (maybe just clear out the calendar and reload from the real data to get rid of any former players?)
