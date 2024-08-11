package dev.ericcameron;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.*;
import net.fortuna.ical4j.model.property.*;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class CalendarProcessor {

    /**
     * Method to generate an ICS file from a list of PlayerModel objects.
     *
     * @param players A list of PlayerModel objects containing player data
     * @param teamId The 2-3 letter abbreviation for the team
     */
    public void createICSFile(List<PlayerModel> players, String teamId) {
        // Create a FileOutputStream for writing the ICS file
        FileOutputStream fout = null;
        try {
            String today = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            // File name is mlb_players_<key>_<today>.ics
            // So mlb_players_NYM_02040808.ics
            String filename = "mlb_players_" + teamId + "_" + today + ".ics";
            fout = new FileOutputStream(filename);
            System.out.println("Creating ICS file: " + filename);
        } catch (FileNotFoundException e) {
            // Handle the exception appropriately
            System.out.println("Exception occurred while creating ICS file");
        }

        // Create a CalendarOutputter
        CalendarOutputter outputter = new CalendarOutputter();

        // Create a new calendar object using iCal4j library
        Calendar calendar = new Calendar();
        // Set the calendar's properties
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new ProdId("-//Eric Cameron//MLB Birthday Calendar Generator 1.0//EN"));
        calendar.getProperties().add(Method.PUBLISH);

        // For each player in the list, create a new calendar entry
        for (PlayerModel player : players) {
            try {
                Date birthDate = new Date(player.getBirthDate().format(DateTimeFormatter.BASIC_ISO_DATE));
                // Create a new event object
                String birthYear = String.valueOf(player.getBirthDate().getYear());
                // Build the event name in a format like "Eric Cameron's birthday (1903)"
                String eventSummary = player.getFullName() + "'s" + " birthday (" + birthYear + ")";
                VEvent event = new VEvent(birthDate,eventSummary);
                // Set the event's recurring property to yearly
                Recur recur = new Recur.Builder()
                        .frequency(Recur.Frequency.YEARLY)
                        .build();
                RRule rule = new RRule(recur);
                event.getProperties().add(rule);
                // Set the event to not be busy (so it doesn't prevent other events from being added)
                event.getProperties().add(Transp.TRANSPARENT);
                // Add the event to the calendar
                calendar.getComponents().add(event);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        // Write the calendar to the file
        try {
            outputter.output(calendar, fout);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Generates CSV content from a list of PlayerModel objects.
     *
     * @param players A list of PlayerModel objects containing player data
     * @return A string representing the CSV content of the player data
     */
    public String generatePlayerCsv(List<PlayerModel> players) {
        // TODO: Implement CSV file generation
        return null;
    }

}
