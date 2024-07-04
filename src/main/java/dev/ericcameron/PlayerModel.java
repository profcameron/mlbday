package dev.ericcameron;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PlayerModel (

    @JsonProperty("FirstName") String firstName,
    @JsonProperty("LastName") String lastName,
    @JsonProperty("Status") String status,
    @JsonProperty("BirthDate") LocalDate birthDate

) {
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + status + ") born: " + birthDate;
    }

}

