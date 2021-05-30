package com.alexio.plm;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import static java.lang.Integer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlmApplication implements LeagueManager  {

    //Add only two matches (away and home)
    // ADD relegate 3 teams at the end of the season

    private static ArrayList<FootballClub> premierLeague = new ArrayList<>();
    private static PlmApplication plm = new PlmApplication();
    private static ArrayList<Match>matchRegistry= new ArrayList<>();

    public static void main(String [] args){

        SpringApplication.run(PlmApplication.class, args);

        try {
            plm.loadInformation("PremierLeague.txt");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e+" File might be empty...");
        }

        while (true) {
            if(seasonOver()){
                System.out.println("\nSeason is Over!!!\n" +
                        "when done checking the stats press 8 to relegate teams...\n" +
                        "and to start a new premier league season press 8 which will reset club stats and matches");
            };

            int userInput;

            while (true) {
                System.out.println("\nEnter:\n1 - To create and add a football team to the premier league" +
                        "\n2 - To remove a football club from the premier league" +
                        "\n3 - To display the statistics of a specific football club" +
                        "\n4 - To display the premier league according to the points they have" +
                        "\n5 - To add a played match" +
                        "\n6 - To save all the information entered" +
                        "\n7 - To clear all files" +
                        "\n8 - To automatically relegate clubs from PL(when season is over" +
                        "\n0 - To exit the system");

                Scanner scanner = new Scanner(System.in);

                try {
                    userInput = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Wrong Input! Enter digits only!");
                    continue;
                }
                if (userInput >= 0 && userInput <= 8) {
                    break;
                } else {
                    System.out.println("Wrong Input! Enter a digit from 0 t 8 only! ");
                }

            }
            switch (userInput) {
                case 1:
                    //method used to check if premier league is full
                    // if not add method gets called
                    isPremierLeagueFull();
                    break;
                case 2:
                    plm.deleteClub();
                    break;
                case 3:
                    plm.displayClub();
                    break;
                case 4:
                    openHomePage();
                    Collections.sort(premierLeague);
                    Collections.sort(matchRegistry,new Match());
                    for(FootballClub fc : premierLeague){
                        // setting clubs position by finding the index of the specific table+1
                        int fcPosition=premierLeague.indexOf(fc)+1;
                        fc.setPosition(fcPosition);
                    }
                    plm.displayTable();
                    break;
                case 5:
                    //addAutoMatch();
                    plm.addMatch();
                    break;
                case 6:
                    try {
                        plm.saveToFile("PremierLeague.txt");
                    }
                    catch(IOException e) {
                        System.out.println(e+"Something has gone wrong while saving the premier league...");
                    }

                    break;
                case 7:
                    plm.clearFiles();
                    break;
                case 8:
                    if(!seasonOver()){
                        System.out.println("Season is not over! Cant relegate any clubs");
                    }
                    relegateTeams(seasonOver());
                    break;
                case 0:
                    plm.exitSystem();
                    break;

                default:
            }
        }

    }

    public static ArrayList<FootballClub> getPremierLeague() {
        Collections.sort(premierLeague);
        for(FootballClub fc : premierLeague){
            // setting clubs position by finding the index of the specific table+1
            int fcPosition=premierLeague.indexOf(fc)+1;
            fc.setPosition(fcPosition);
        }
        return premierLeague;
    }

    public static ArrayList<Match> getMatchRegistry() {
        Collections.sort(matchRegistry,new Match());
        return matchRegistry;
    }

    //method used to check if premier league is full
    // if not add method gets called
    public static void isPremierLeagueFull(){
        if (premierLeague.size()==20){
            System.out.println("Premier League is full !!!\n" +
                    "You can enter any more teams unless you delete one...");
        }else{ plm.addClub();}
    }

    @Override
    public void addClub(){

        String clubName;
        String clubLocation;
        String clubStadium;
        int wins;
        int defeats;
        int draws;
        int matchesPlayed;
        int goalsScored;
        int goalsReceived;
        int points;

        Scanner stringScanner = new Scanner(System.in);

        System.out.print("Enter club name: ");
        clubName = stringScanner.nextLine();
        System.out.print("Enter club location: ");
        clubLocation = stringScanner.nextLine();
        System.out.print("Enter club stadium name: ");
        clubStadium = stringScanner.nextLine();
        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.print("Enter number of wins : ");
            try{
                Scanner intScanner = new Scanner(System.in);
                wins = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }
        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.print("Enter number of defeats : ");
            try{
                Scanner intScanner = new Scanner(System.in);
                defeats = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }
        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.print("Enter number of draws : ");
            try{
                Scanner intScanner = new Scanner(System.in);
                draws = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }

        while(true) {
            //User input validation(if user enters a string instead of an int)
            while(true) {
                System.out.print("Enter number of matches played : ");
                try{
                    Scanner intScanner = new Scanner(System.in);
                    matchesPlayed = intScanner.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("\n" + e + " - Only integers allowed(digits)");
                }
            }

            int correctNoOfMatchesPlayed = wins+draws+defeats;
            //Checking if number of matches played is correct
            if(correctNoOfMatchesPlayed==matchesPlayed){
                break;
            }else{
                System.out.println("\nWrong number of matches played !!!\n" +
                        "Do you want to set "+ correctNoOfMatchesPlayed +" as correct number of matches played?\n"+
                        "OR re-enter stats from the beginning\n"+ "Yes - to correct number of matches played\n" +
                        "No - to re-enter stats");
                String matchesPlayedError = stringScanner.nextLine().toLowerCase();
                //going back to menu
                if(matchesPlayedError.equals("yes")||matchesPlayedError.equals("y")){
                    matchesPlayed=correctNoOfMatchesPlayed;
                    break;
                }
                // setting correct number of matches played
                else if(matchesPlayedError.equals("no")||matchesPlayedError.equals("n")){
                    return;
                }
                else{
                    System.out.println("Wrong input! Enter Yes or No only...");
                }

            }
        }
        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.print("Enter number of goals scored : ");
            try{
                Scanner intScanner = new Scanner(System.in);
                goalsScored = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }

        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.print("Enter number of goals received : ");
            try{
                Scanner intScanner = new Scanner(System.in);
                goalsReceived = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }


        outerloop:
        while(true) {
            //User input validation(if user enters a string instead of an int)
            while(true) {
                System.out.print("Enter number of points : ");
                try{
                    Scanner intScanner = new Scanner(System.in);
                    points = intScanner.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("\n" + e + " - Only integers allowed(digits)");
                }
            }
            //Calculating correct number of points
            int calculatedPoints= ((wins*3)+(defeats*0)+(draws*1));
            //Checking  if entered number of points is correct or not and
            //Telling user that points entered are wrong and telling him the correct number of points
            //to be assigned or to re enter number of wins-draws-defeats
            if (points!=calculatedPoints){
                System.out.println("\nYou have entered the wrong number of points\n" +
                        "The correct number of points is " + calculatedPoints +"\n" +
                        "Do you want to set that as the correct number of points? Yes/No");
                String pointsError = stringScanner.nextLine().toLowerCase();
                // setting correct number of points
                if(pointsError.equals("yes")||pointsError.equals("y")){
                    System.out.println(calculatedPoints + " have been entered as points");
                    points=calculatedPoints;
                    break outerloop;
                }
                // asking user what he wants to do for the error he committed
                else if(pointsError.equals("no")||pointsError.equals("n")){
                    System.out.println("Do you want to go back and re-enter the team details? (Statistics entered are wrong...)" +
                            "Yes/No");
                    String reEnterDetails = stringScanner.nextLine();
                    //going back to main menu so that user re-enters details
                    if(reEnterDetails.equals("yes")||reEnterDetails.equals("y")){
                        return;
                    }
                    //Ultimately setting the correct number of points if he doesnt want to re-enter either
                    else if(reEnterDetails.equals("no")||reEnterDetails.equals("n")){
                        System.out.println("Setting " + calculatedPoints + " as correct number of points...");
                        points=calculatedPoints;
                        break outerloop;
                    }
                    else{
                        System.out.println("Wrong input ! Enter Yes or No only");
                    }
                }
                // user input validation
                else{
                    System.out.println("Wrong input ! Enter Yes or No only");
                }
            }else{break outerloop;}
        }

        FootballClub fc = new FootballClub(clubName,clubLocation,clubStadium,wins,defeats,draws,matchesPlayed,goalsScored,goalsReceived,points);

        for(FootballClub tempfc: premierLeague) {
            if(fc.equals(tempfc)) {
                System.out.println("\nTeam is already in the Premier League");
                return;
            }
        }
        premierLeague.add(fc);
        System.out.println("Club added: "+fc.toString()+"\n");

    }
    @Override
    public void deleteClub(){
        //
        Scanner scanner = new Scanner(System.in);
        boolean foundTeam = false;
        System.out.print("Enter name of the club you want to delete: ");
        String clubName = scanner.nextLine();

        for (FootballClub fc : premierLeague) {
            if (fc.getClubName().equals(clubName)) {
                System.out.println(fc.toString());
                System.out.println("Are you sure that you want to remove this team?(yes or no)");
                String sure = scanner.nextLine();
                sure.toLowerCase();
                if(sure.equals("yes")||sure.equals("y")){
                    premierLeague.remove(fc);
                    foundTeam = true;
                    System.out.println(fc.getClubName() + " was succesfully removed!");
                    System.out.println("Number of teams in the premier league : " + premierLeague.size());
                }else if(sure.equals("no")||sure.equals("n")){return;}
                else{
                    System.out.println("Enter yes or no only!");
                }
                break;
            }
        }

        if (!foundTeam) {
            System.out.println("Team was not found! Name was incorrect! Try again...");
        }
    }

    @Override
    public void displayClub(){
        //Display the statistics of a specific club
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of the club you want to display: ");
        String clubName=scanner.nextLine();
        boolean foundTeam = false;
        for(FootballClub fc : premierLeague) {
            if (fc.getClubName().equals(clubName)) {
                System.out.println(fc.toString());
                foundTeam=true;
            }
        }
        if (!foundTeam) {
            System.out.println("Team was not found! Name was incorrect! Try again...");
        }
    }

    @Override
    public void displayTable(){
        //String Formatting
        System.out.printf("|%3s|","Pos");
        System.out.printf("%-15s|","Name");
        System.out.printf("%8s|","M Played");
        System.out.printf("%5s|","Wins");
        System.out.printf("%5s|","Draws");
        System.out.printf("%7s|","Defeats");
        System.out.printf("%4s|","G/S");
        System.out.printf("%4s|","G/R");
        System.out.printf("%4s|","G/D");
        System.out.printf("%6s|","Points");
        int pos = 1;
        Collections.sort(premierLeague);
        for(FootballClub fc : premierLeague){
            // setting clubs position by finding the index of the specific table+1
            int fcPosition=premierLeague.indexOf(fc)+1;
            fc.setPosition(fcPosition);
        }
        for(FootballClub fc:premierLeague){
            System.out.printf("\n|%-3s|",pos);
            System.out.printf("%-15s|",fc.getClubName());
            System.out.printf("%8d|",fc.getMatchesPlayed());
            System.out.printf("%5d|",fc.getWins());
            System.out.printf("%5d|",fc.getDraws());
            System.out.printf("%7d|",fc.getDefeats());
            System.out.printf("%4d|",fc.getGoalsScored());
            System.out.printf("%4d|",fc.getGoalsReceived());
            System.out.printf("%4d|",fc.getGoalDifference());
            System.out.printf("%6d|",fc.getPoints());
            pos++;
        }
    }
    @Override
    public void addMatch(){
        System.out.println("Matches played: ");
        for(Match match : matchRegistry){
            System.out.println(match.toString());
        }

        if(premierLeague.size()<2){
            System.out.println("Cannot play matches if there are less than 2 teams in PL");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date and time of played match(hh-mm-DD-MM-YYYY): \n" +
                "hh-hours(Ex: 12) | " +
                "mm-minutes(Ex: 30) | " +
                "DD-day(Ex: 20) | " +
                "MM-month(Ex: 08) | " +
                "YYYY-year(Ex: 2020) | " +
                "Remember to enter a (-) in between");
        String tempDate = scanner.nextLine();
        int minutes=0;
        int hours=0;
        int day=0;
        int month=0;
        int year=0;
        int homeTeamGoals=0;
        int awayTeamGoals =0;
        String homeTeam="";
        String awayTeam="";
        try {

            hours=parseInt(tempDate.substring(0,2));
            minutes=parseInt(tempDate.substring(3,5));
            day=parseInt(tempDate.substring(6,8));
            month=parseInt(tempDate.substring(9,11));
            year=parseInt(tempDate.substring(12,16));

        }catch (NumberFormatException | StringIndexOutOfBoundsException e){

            System.out.println("You have entered the date in a wrong manner!!! Try again...");
            return;

        }

        DateTime dateTime = new DateTime(hours,minutes,day,month,year);
        System.out.println(dateTime.toString());
        //User input validation(if user enters a team which is not in the Premier League)
        outerloop:
        while(true) {
            System.out.println("Enter home team name: ");
            homeTeam = scanner.nextLine();
            if(isClubInPL(homeTeam)){
                break outerloop;
            }
        }


        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.println("Enter home team goals (int only): ");
            try{
                Scanner intScanner = new Scanner(System.in);
                homeTeamGoals = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }
        //User input validation(if user enters a team which is not in the Premier League)
        outerloop:
        while(true) {
            System.out.println("Enter away team name: ");
            awayTeam = scanner.nextLine();
            if(isClubInPL(awayTeam)&& !awayTeam.equals(homeTeam)){
                break outerloop;
            }
            if(awayTeam.equals(homeTeam)){
                System.out.println("A team cannot compete with itself... PLease choose another team");
            }
        }

        //User input validation(if user enters a string instead of an int)
        while(true) {
            System.out.println("Enter away team goals (int only): ");
            try{
                Scanner intScanner = new Scanner(System.in);
                awayTeamGoals = intScanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("\n" + e + " - Only integers allowed(digits)");
            }
        }
        if(isMatchOkayToPlay(homeTeam,awayTeam)){
            Match match = new Match(dateTime,homeTeam,awayTeam,homeTeamGoals,awayTeamGoals);
            matchRegistry.add(match);
            updateStats(match);
            System.out.println("Match has been added to registry and stats have been updated...");
        }else{
            System.out.println("These two teams cannot play a match" +
                    "as they already played two matches againts eachother" +
                    ", please enter it correctly next time");
        }
    }

    public static void updateStats(Match match){

        String winner="";
        if(match.getWinner().equals(match.getHomeTeam())){winner="home";}
        else if(match.getWinner().equals(match.getAwayTeam())){winner="away";}
        else if(match.getWinner().equals("draw")){winner="draw";}

        for(FootballClub fc : premierLeague){
            if(fc.getClubName().equals(match.getHomeTeam())){
                fc.setGoalsScored(fc.getGoalsScored()+match.getHomeTeamGoals());
                fc.setGoalsReceived(fc.getGoalsReceived()+match.getAwayTeamGoals());
                fc.setMatchesPlayed(fc.getMatchesPlayed()+1);
                if(winner.equals("home")){
                    fc.setWins(fc.getWins()+1);
                    fc.setPoints(fc.getPoints()+3);
                }
                else if(winner.equals("draw")){
                    fc.setDraws(fc.getDraws()+1);
                    fc.setPoints(fc.getPoints()+1);
                }
                else if(winner.equals("away")){
                    fc.setDefeats(fc.getDefeats()+1);
                    fc.setPoints(fc.getPoints()+0);
                }
            }

            else if(fc.getClubName().equals(match.getAwayTeam())){
                fc.setGoalsScored(fc.getGoalsScored()+match.getAwayTeamGoals());
                fc.setGoalsReceived(fc.getGoalsReceived()+match.getHomeTeamGoals());
                fc.setMatchesPlayed(fc.getMatchesPlayed()+1);
                if(winner.equals("away")){
                    fc.setWins(fc.getWins()+1);
                    fc.setPoints(fc.getPoints()+3);
                }
                else if(winner.equals("draw")){
                    fc.setDraws(fc.getDraws()+1);
                    fc.setPoints(fc.getPoints()+1);
                }
                else if(winner.equals("home")){
                    fc.setDefeats(fc.getDefeats()+1);
                    fc.setPoints(fc.getPoints()+0);
                }
            }
        }
    }

    @Override
    public void saveToFile(String leagueFile)throws IOException{
        FileOutputStream fOS_League = new FileOutputStream(leagueFile);
        ObjectOutputStream oOS_League = new ObjectOutputStream(fOS_League);
        //oOS_League.writeObject(premierLeague);
        for (FootballClub fc : premierLeague) {
            oOS_League.writeObject(fc);
        }
        System.out.println("All premier league clubs have been saved!");
        while(true) {
            System.out.println("Do you want to save the played matches as well?(Yes/No)");
            Scanner scanner = new Scanner(System.in);
            String decision = scanner.nextLine();
            decision.toLowerCase();
            if (decision.equals("yes") || decision.equals("y")) {
                try {
                    saveMatchesToFile("MatchRegistry.txt");
                }
                catch(IOException e) {
                    System.out.println(e+"Something has gone wrong while saving match registry...");
                }
                break;
            }
            else if (decision.equals("no") || decision.equals("n")) {
                return;
            } else {
                System.out.println("Wrong input! Enter Yes or No only...");
            }
        }
    }

    public void saveMatchesToFile(String matchesFile)throws IOException{
        FileOutputStream fOS_League = new FileOutputStream(matchesFile);
        ObjectOutputStream oOS_League = new ObjectOutputStream(fOS_League);
        //another method - oOS_League.writeObject(matchRegistry);
        for (Match match : matchRegistry) {
            oOS_League.writeObject(match);
        }
        System.out.println("Matches have been saved to file!");
    }
    @Override
    public void loadInformation(String leagueFile)throws IOException,ClassNotFoundException{
        FileInputStream fIS = new FileInputStream(leagueFile);
        ObjectInputStream oIS = new ObjectInputStream(fIS);

        while(true) {
            try {
                premierLeague.add((FootballClub) oIS.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        System.out.println("Premier League have been loaded successfully");
        try {
            loadMatchRegistry("MatchRegistry.txt");
        }
        catch(IOException e) {
            System.out.println(e+"Something has gone wrong while loading match registry...");
        }
    }

    public void loadMatchRegistry(String leagueFile)throws IOException,ClassNotFoundException{
        FileInputStream fIS = new FileInputStream(leagueFile);
        ObjectInputStream oIS = new ObjectInputStream(fIS);
        while(true) {
            try {
                matchRegistry.add((Match) oIS.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        System.out.println("Match Registry have been loaded successfully");
    }
    @Override
    public void exitSystem(){
        while (true){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Are you sure you want to exit the system? Yes/No");
            String exit=scanner.nextLine();
            exit.toLowerCase();
            if (exit.equals("yes")||exit.equals("y")){
                while (true){
                    System.out.println("Do you want to save before exiting the system? Yes/No");
                    String save=scanner.nextLine();
                    save.toLowerCase();
                    if (save.equals("yes")||save.equals("y")){
                        /*saveToFile method*/
                        System.out.println("Informations saved...");
                        try {
                            plm.saveToFile("PremierLeague.txt");
                        }
                        catch(IOException e) {
                            System.out.println(e+"Something has gone wrong while saving the premier league...");
                        }
                        System.out.println("Exiting the system...");
                        System.exit(0);
                    }
                    else if(save.equals("no")||save.equals("n")){
                        System.out.println("Exiting the system...");
                        System.exit(0);
                    }
                    else{System.out.println("Wrong input! Try again...");}
                }
            }
            else if(exit.equals("no")||exit.equals("n")){
                System.out.println("Returning to the main menu...");
                return;
            }
            else{System.out.println("Wrong input! Try again...");}
        }
    }

    //method to check if a club is already in the premier leaggue or not
    //used for matchmaking purposes
    //to check if theres a team with that name if yes it can play a match
    //if not system will give suggestions
    public boolean isClubInPL(String enteredClubName){
        boolean isTeamPresent=false;
        for(FootballClub fc : premierLeague){
            if(fc.getClubName().equals(enteredClubName)){
                isTeamPresent=true;
            }
        }
        if(!isTeamPresent){
            String allClubNames = "";
            for(FootballClub fcc : premierLeague){
                allClubNames=allClubNames+fcc.getClubName()+",";
            }
            System.out.println("Team not in Premier League...\n" +
                    "Here is the list of the premier league clubs: \n" +
                    allClubNames);
        }
        if(isTeamPresent){return true;}
        else{return false;}
    }
    // method which checks if the home team has already played 2 matches with the away team
    //if yes it suggests names of teams that it can play with
    public static boolean isMatchOkayToPlay(String homeTeam, String awayTeam){
        int matchesPlayedAgainstEachOther=0;
        //Checks if 2 matches between these two opponent have already been played
        //checks home team vs away team and viceversa
        for(Match match:matchRegistry){
            if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                matchesPlayedAgainstEachOther++;
            }else if(match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam)){
                matchesPlayedAgainstEachOther++;
            }
        }
        if(matchesPlayedAgainstEachOther==2){
            return false;
        }else return true;
    }

    //Method to cleat both the files if you want to start from scrath
    public static void clearFiles(){
        while(true) {
            System.out.println("Do you really want to clear both the files?(premierLeague & MatchRegistry)");
            Scanner scanner = new Scanner(System.in);
            String decision = scanner.nextLine();
            decision.toLowerCase();
            if (decision.equals("yes") || decision.equals("y")) {
                try {
                    new FileOutputStream("PremierLeague.txt").close();
                    new FileOutputStream("MatchRegistry.txt").close();
                    System.out.println("Files Cleared");
                    System.out.println("Restart system to see changes");
                }
                catch(Exception e) {
                    System.out.println(e+" Something has gone wrong while clearing the files...");
                }
                break;
            }
            else if (decision.equals("no") || decision.equals("n")) {
                return;
            }
            else {
                System.out.println("Wrong input! Enter Yes or No only...");
            }
        }
    }
    //check if season is over
    //the end of the season is calculated by ((noOfTeams*2)-2)*noOfTeams which is the number
    //of matches to be played by a single team by the end of the season
    //total matches played by teams is taken from the footballclub objects by getMatchesPlayed
    public static boolean seasonOver(){
        boolean seasonFinished=false;
        if(premierLeague.size()>2){
            int totalMatches = ((premierLeague.size()*2)-2);
            int teamsDoneWithMatches=0;
            for(FootballClub fc:premierLeague){
                if(fc.getMatchesPlayed()==totalMatches){
                    teamsDoneWithMatches++;
                }
            }
            if(teamsDoneWithMatches==premierLeague.size()){
                seasonFinished=true;
            }else {seasonFinished=false;}
        }
        return seasonFinished;
    }

    //Method used to relegate the last 3 teams in the league at the end of the season
    public static void relegateTeams(Boolean seasonOver){
        String relegatedTeams="";
        if(seasonOver){
            System.out.println("\nSeason is over!!!\n");
            //SAVING BEFORE RELEGATING SO THAT USER CAN CHECK STATS
            System.out.println("Premier League Table at the end of season: ");
            plm.displayTable();
            Collections.sort(premierLeague);
            int plSize=premierLeague.size();
            if(premierLeague.size()>=10){
                //IF THERE ARE MORE TEAMS THAN 10
                //Remove the last 3 teams in the list by
                // substracting x from 0 as the last element in a list is
                //-1, before the last -2.... etc
                for(int x = 1; x <= 3 ; x++){
                    relegatedTeams=relegatedTeams+", ";
                    relegatedTeams=relegatedTeams+premierLeague.get(plSize-x).getClubName();
                    premierLeague.remove(plSize-x);
                }
                System.out.println("\nLast 3 teams in the league relegated...\n" +
                        "Teams relegated : " + relegatedTeams);
            }
            else if(premierLeague.size()>=4){
                //if premier league size is less than 10
                //i decided to relegate 2 teams only
                //Remove the last 2 teams in the list by
                // substracting x from 0 as the last element in a list is
                //-1, before the last -2.... etc
                for(int x = 1; x <= 2 ; x++){
                    relegatedTeams=relegatedTeams+", ";
                    relegatedTeams=relegatedTeams+premierLeague.get(plSize-x).getClubName();
                    premierLeague.remove(plSize-x);
                }
                System.out.println("\nLast 2 teams in the league relegated(less teams in PL)...\n" +
                        "Teams relegated : " + relegatedTeams);
            }
            else{
                System.out.println("You cant relegate teams as number of clubs in PL is too low...");
            }
            //Saving the premier league clubs without relegated teams
            try {
                plm.saveToFile("PremierLeague.txt");
            }
            catch(IOException e) {
                System.out.println(e+"Something has gone wrong while saving the premier league...");
            }
            //Resetting the league stats and matches without the relegated teams
            newSeason();

        }
        //if the number of total matches hasnt still been reached skip
        else{return;}

    }

    public static void newSeason(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Are you sure you want to reset the stats of the teams that stayed in the PL" +
                " and the matchRegistry as well?\n" +
                "Enter Yes or Y to continue or anything else to not reset");
        String sure= scn.nextLine();
        if(sure.toLowerCase().equals("yes")||sure.toLowerCase().equals("y")){
            clearStats();
            try {
                new FileOutputStream("MatchRegistry.txt").close();
                System.out.println("File Cleared");
            }
            catch(Exception e) {
                System.out.println(e+" Something has gone wrong while clearing the files...");
            }

            System.out.println("The system will reset every stat, please restart the system to see changes...");
        }else{return;}
    }
    public static void clearStats(){
        for(FootballClub fc : premierLeague){
            fc.setWins(0);
            fc.setDraws(0);
            fc.setDefeats(0);
            fc.setMatchesPlayed(0);
            fc.setGoalsScored(0);
            fc.setGoalsReceived(0);
            fc.setPoints(0);
        }
        System.out.println("-Stats Cleared!-");
        try {
            plm.saveToFile("PremierLeague.txt");
        }
        catch(IOException e) {
            System.out.println(e+"Something has gone wrong while saving the premier league...");
        }
    }

    public static Match addAutoMatch(){

        if(premierLeague.size()<2){
            System.out.println("Cannot play matches if there are less than 2 teams in PL");
            return null;
        }

        //Checking if season is over before starting to auto matchmaking process
        //to eliminate possible errors

        if(seasonOver()){
            System.out.println("No matches to play! Season is over! Enter 8 in the menu  to relegate teams...");
            return null;
        }

        Random random = new Random();

        int totalMatchesPerTeam=((premierLeague.size()*2)-2);

        String homeTeam="";
        String awayTeam="";
        //Picking a random club from the premier league list and checking if it has any matches to play...
        //if yes it will get selected as homeTeam else loop will continue until it finds a suitable team
        // same with awayTeam...Loop will exit when both will be selected
        while(true) {
            int randHomeTeamIndex = random.nextInt(premierLeague.size());
            if(premierLeague.get(randHomeTeamIndex).getMatchesPlayed()!=totalMatchesPerTeam){
                homeTeam=premierLeague.get(randHomeTeamIndex).getClubName();
            }
            int randAwayTeamIndex = random.nextInt(premierLeague.size());
            if(premierLeague.get(randAwayTeamIndex).getMatchesPlayed()!=totalMatchesPerTeam){
                awayTeam=premierLeague.get(randAwayTeamIndex).getClubName();
            }
            if(!homeTeam.equals("")&& !awayTeam.equals("")){
                if(!homeTeam.equals(awayTeam)){
                    if(isMatchOkayToPlay(homeTeam,awayTeam)){
                        break;
                    }
                }
            }
        }
        int homeTeamGoals = random.nextInt(5);

        int awayTeamGoals = random.nextInt(5);

        int minute = randomNumberBetween(1,59);
        int hour = randomNumberBetween(12,22);
        int day = randomNumberBetween(1,31);
        int month = randomNumberBetween(1,12);
        int year = randomNumberBetween(2020,2021);
        DateTime dateTime = new DateTime(hour,minute,day,month,year);
        Match match = new Match(dateTime,homeTeam,awayTeam,homeTeamGoals,awayTeamGoals);
        System.out.println(match.toString());
        matchRegistry.add(match);
        updateStats(match);
        return match;
    }
    private static void openHomePage() {
        System.setProperty("java.awt.headless", "false");
        try {
            URI homepage = new URI("http://localhost:4200/");
            Desktop.getDesktop().browse(homepage);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int randomNumberBetween(int startNum, int endNum) {
        return startNum + (int)Math.round(Math.random() * (endNum - startNum));
    }

    // method for descending order sorting purposes for GUI
    // asks for what stat to sort and sorts accordingly
    // ONLY USED IN GUI
    public static ArrayList<FootballClub> sortDescOrder(String type){
        ArrayList<FootballClub> orderedPL = premierLeague;
        while(true){
            // listSorted integer to check if every element in the list is sorted correcly
            // for each an every correct sort it will add 1, so when it will reach the size
            // of the list it will mean that its sorted
            int listSorted=0;
            // -1 because else it will throw a null pointer except when it checks the
            // if condition inside the for loop as it checks x+1 index of the list
            for (int x=0; x<orderedPL.size()-1;x++){
                Boolean roundSorted=false;
                if(type.equals("goalsScored")){
                    if(orderedPL.get(x).getGoalsScored()>=orderedPL.get(x+1).getGoalsScored()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                else if(type.equals("goalsDifference")){
                    if(orderedPL.get(x).getGoalDifference()>=orderedPL.get(x+1).getGoalDifference()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                else if(type.equals("goalsReceived")){
                    if(orderedPL.get(x).getGoalsReceived()>=orderedPL.get(x+1).getGoalsReceived()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                else if(type.equals("wins")){
                    if(orderedPL.get(x).getWins()>=orderedPL.get(x+1).getWins()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                else if(type.equals("draws")){
                    if(orderedPL.get(x).getDraws()>=orderedPL.get(x+1).getDraws()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                else if(type.equals("defeats")){
                    if(orderedPL.get(x).getDefeats()>=orderedPL.get(x+1).getDefeats()){
                        listSorted++;
                        roundSorted=true;
                    }
                }
                if(!roundSorted){
                    FootballClub temp = orderedPL.get(x);
                    orderedPL.set(x,orderedPL.get(x+1));
                    orderedPL.set(x+1,temp);
                }
            }
            if(listSorted==orderedPL.size()-1){
                break;
            }
        }
        return orderedPL;
    }

    public static ArrayList<FootballClub> goalScoredDescOrder(){
        return sortDescOrder("goalsScored");
    }
    public static ArrayList<FootballClub> goalReceivedDescOrder(){
        return sortDescOrder("goalsReceived");
    }
    public static ArrayList<FootballClub> goalDifferenceDescOrder(){
        return sortDescOrder("goalsDifference");
    }
    public static ArrayList<FootballClub> winsDescOrder(){
        return sortDescOrder("wins");
    }
    public static ArrayList<FootballClub> drawsDescOrder(){
        return sortDescOrder("draws");
    }
    public static ArrayList<FootballClub> defeatsDescOrder(){
        return sortDescOrder("defeats");
    }
}
