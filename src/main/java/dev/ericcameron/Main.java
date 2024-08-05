package dev.ericcameron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {

        // Retrieve API key from environment variable for security
        // Sign up for your own key at https://sportsdata.io/members/subscriptions
        // MLB API Free Trial is available
        String key = System.getenv("SportsDataIOApiKey");

        // Use TeamPicker to get user input for team selection
        TeamPicker teamPicker = new TeamPicker();
        String team = teamPicker.chooseTeam();

        // Construct API endpoint URL with selected team and API key
        String url = "https://api.sportsdata.io/v3/mlb/scores/json/PlayersBasic/" + team + " ?key=" + key;

        // Set up RestTemplate for API request
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Fetch player data from API and deserialize into PlayerModel array
        PlayerModel[] result = restTemplate.getForObject(url, PlayerModel[].class);

        // Sort players by birth date (oldest to youngest)
        Arrays.sort(result);

        // Display sorted dates for debugging
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i].sortDate);
        }

        // Filter active players (exclude non-roster invitees and minor league players)
        List<PlayerModel> active = new ArrayList<>();
        int playerCount = 0;
        for (int i = 0; i < result.length; i++) {
            PlayerModel current = result[i];
            if(!current.getStatus().equals("Non-Roster Invitee") && !current.getStatus().equals("Minors")){
                active.add(current);
            }
        }

        // Display total count of active players
        System.out.println("Total players on team roster: " + active.size());

        // Print details of all active players
        for (int i = 0; i < active.size(); i++) {
            System.out.println(active.get(i));
        }

    }
}