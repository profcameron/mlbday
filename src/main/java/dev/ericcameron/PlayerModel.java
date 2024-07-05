package dev.ericcameron;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import static java.util.Objects.isNull;

// PlayerModel implements comparable to allow sorting
public class PlayerModel implements Comparable<PlayerModel> {

    @JsonProperty("FirstName") private String firstName;
    @JsonProperty("LastName") private String lastName;
    @JsonProperty("Status") private String status;
    @JsonProperty("BirthDate") private LocalDate birthDate;
    private int birthMonth = 0;
    private int birthDay = 0;
    private int birthYear = 0;
    // sortDate will be used to allow sorting by birthdate, oldest to newest.
    // It will be a combination of the 4 digit year, followed by 2 digit month, then two digit day
    // January 1, 1999 is 19990101
    // January 2, 1999 is 19990102
    // String sort will list the earlier date first
    public String sortDate = "";

    public PlayerModel(@JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName, @JsonProperty("Status") String status, @JsonProperty("BirthDate") LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.birthDate = birthDate;
        loadPlayerDate();
    }

    public void loadPlayerDate(){
        // Ensure the birthDate is not null. Though a value is expected, the free SportsData API will
        // sometimes be (intentionally) scrambled since it is a free API
        if(!isNull(birthDate)) {
            this.birthMonth = birthDate.getMonthValue();
            this.birthDay = birthDate.getDayOfMonth();
            this.birthYear = birthDate.getYear();
            this.sortDate = String.valueOf(birthYear) + ((birthMonth < 10) ? "0" : "") + String.valueOf(birthMonth) + (((birthDay < 10)) ? "0" : "") + String.valueOf(birthDay);
        }
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + status + ") born: " + birthYear + "-" + birthMonth + "-" + birthDay + " " + sortDate;
    }

    @Override
    public int compareTo(PlayerModel o2) {
        return sortDate.compareTo(o2.sortDate);
    }
}


