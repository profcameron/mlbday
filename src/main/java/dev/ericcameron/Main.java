package dev.ericcameron;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // API Key is stored in a Windows environment variable
        // You can sign up for your own key here:
        // https://sportsdata.io/members/subscriptions
        // You can sign up for the MLB API Free Trial
        String key = System.getenv("SportsDataIOApiKey");

        // TeamPicker translates the team name to abbreviation
        TeamPicker teamPicker = new TeamPicker();
        String team = teamPicker.chooseTeam();

        // Pull data from the Players endpoint with the team code and key
        String url = "https://api.sportsdata.io/v3/mlb/scores/json/PlayersBasic/" + team + " ?key=" + key;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // PlayerModel is a record containing the important information from the player data API
        PlayerModel[] result = restTemplate.getForObject(url, PlayerModel[].class);

        List<PlayerModel> active = new ArrayList<>();
        int playerCount = 0;
        for (int i = 0; i < result.length; i++) {
            PlayerModel current = result[i];
            if(!current.status().equals("Non-Roster Invitee") && !current.status().equals("Minors")){
                System.out.println(current);
                active.add(current);
                playerCount++;
            }
        }
        System.out.println("Total on team: " + playerCount);

    }
}