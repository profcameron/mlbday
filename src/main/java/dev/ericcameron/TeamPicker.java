package dev.ericcameron;

import java.util.Scanner;

public class TeamPicker {

    public String chooseTeam(){
        Scanner input = new Scanner(System.in);
        String choiceString;

        String[] teamList = {"ARI", "ATL", "BAL", "BOS", "CHC", "CHW", "CIN", "CLE", "COL", "DET", "HOU", "KC", "LAA", "LAD", "MIA", "MIL", "MIN", "NYM", "NYY", "OAK", "PHI", "PIT", "SD", "SF", "SEA", "STL", "TB", "TEX", "TOR", "WSH"};

        System.out.println("1. Arizona Diamondbacks");
        System.out.println("2. Atlanta Braves");
        System.out.println("3. Baltimore Orioles");
        System.out.println("4. Boston Red Sox");
        System.out.println("5. Chicago Cubs");
        System.out.println("6. Chicago White Sox");
        System.out.println("7. Cincinnati Reds");
        System.out.println("8. Cleveland Guardians");
        System.out.println("9. Colorado Rockies");
        System.out.println("10. Detroit Tigers");
        System.out.println("11. Houston Astros");
        System.out.println("12. Kansas City Royals");
        System.out.println("13. Los Angeles Angels");
        System.out.println("14. Los Angeles Dodgers");
        System.out.println("15. Miami Marlins");
        System.out.println("16. Milwaukee Brewers");
        System.out.println("17. Minnesota Twins");
        System.out.println("18. New York Mets");
        System.out.println("19. New York Yankees");
        System.out.println("20. Oakland Athletics");
        System.out.println("21. Philadelphia Phillies");
        System.out.println("22. Pittsburgh Pirates");
        System.out.println("23. San Diego Padres");
        System.out.println("24. San Francisco Giants");
        System.out.println("25. Seattle Mariners");
        System.out.println("26. St. Louis Cardinals");
        System.out.println("27. Tampa Bay Rays");
        System.out.println("28. Texas Rangers");
        System.out.println("29. Toronto Blue Jays");
        System.out.println("30. Washington Nationals");

        int choice = 0;
        boolean inputOk = false;

        while(inputOk == false) {
            System.out.print("Please pick a team number 1-30 >> ");
            choiceString = input.nextLine();
            if (isNumeric(choiceString)) {
                choice = Integer.parseInt(choiceString);

                if (choice < 1 || choice > 30) {
                    System.out.println("Please choose a team between 1 and 30 from the list.");
                } else {
                    inputOk = true;
                }
            } else {
                System.out.println("Please enter a numeric value.");
            }
        }
        return teamList[choice-1];
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
