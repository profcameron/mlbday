package dev.ericcameron;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.isNull;

/**
 * Represents a player model with sortable capabilities based on birth date.
 * This class implements Comparable to allow sorting of players by their birth date.
 */
public class PlayerModel implements Comparable<PlayerModel> {

    @JsonProperty("FirstName") private String firstName;
    @JsonProperty("LastName") private String lastName;
    @JsonProperty("Status") private String status;
    @JsonProperty("BirthDate") private LocalDate birthDate;
    private int birthMonth = 0;
    private int birthDay = 0;
    private int birthYear = 0;
    /**
     * sortDate is used for sorting players by birth date, from oldest to newest.
     * Format: YYYYMMDD (e.g., January 1, 1999 is represented as 19990101)
     * This allows for simple string comparison to sort dates chronologically.
     */
    public String sortDate = "";

    /**
     * Constructs a new PlayerModel with the given properties.
     * This constructor will be used by Jackson to create a new PlayerModel
     * @param firstName The player's first name
     * @param lastName The player's last name
     * @param status The player's status
     * @param birthDate The player's birthdate
     */
    public PlayerModel(@JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName, @JsonProperty("Status") String status, @JsonProperty("BirthDate") LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.birthDate = birthDate;
        loadPlayerDate();
    }


    /**
     * Loads the player's birthdate information and calculates the sortDate.
     * This method is called from the constructor.
     */
    public void loadPlayerDate(){
        // Ensure the birthDate is not null. Though a value is expected, the free SportsData API will
        // sometimes be (intentionally) scrambled since it is a free API
        if(!isNull(birthDate)) {
            this.birthMonth = birthDate.getMonthValue();
            this.birthDay = birthDate.getDayOfMonth();
            this.birthYear = birthDate.getYear();
            // Construct sortDate with zero-padding for month and day
            this.sortDate = String.valueOf(birthYear) + ((birthMonth < 10) ? "0" : "") + String.valueOf(birthMonth) + (((birthDay < 10)) ? "0" : "") + String.valueOf(birthDay);
        }
    }

    /**
     * Getter to fetch the player's status.
     *
     * @return The player's status
     */
    public String getStatus() {
        return status;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Returns a string representation of the PlayerModel (typical toString).
     *
     * @return A string containing the player's name, status, birthdate, and sortDate
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + status + ") born: " + birthYear + "-" + birthMonth + "-" + birthDay + " " + sortDate;
    }

    /**
     * Compares this PlayerModel to another PlayerModel based on their sortDate.
     * Need to override the compareTo method to do a comparison on the sort date
     * So we can sort the players by their birthdate. This value is calculated in the loadPlayerDate function
     *
     * @param o2 The PlayerModel to compare with
     * @return A negative integer, zero, or a positive integer as this PlayerModel
     *         is less than, equal to, or greater than the specified PlayerModel
     */
    @Override
    public int compareTo(PlayerModel o2) {
        return sortDate.compareTo(o2.sortDate);
    }
}


