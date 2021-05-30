package com.alexio.plm;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class FootballClub extends SportsClub implements Comparable<FootballClub>{

    SportsClub sportsClub = new SportsClub();

    private int wins;
    private int draws;
    private int defeats;
    private int matchesPlayed;
    private int goalsScored;
    private int goalsReceived;
    private int points;
    private int position;

    public int getWins() {return this.wins;}
    public int getDefeats() {return this.defeats;}
    public int getDraws() {return this.draws;}
    public int getMatchesPlayed() {return this.matchesPlayed;}
    public int getGoalsScored() {return this.goalsScored;}
    public int getGoalsReceived() {return this.goalsReceived;}
    public int getPoints() {return this.points;}

    public void setWins(int wins){this.wins=wins;}
    public void setDraws(int draws){this.draws=draws;}
    public void setDefeats(int defeats){this.defeats=defeats;}
    public void setMatchesPlayed(int matchesPlayed){this.matchesPlayed=matchesPlayed;}
    public void setGoalsScored(int goalsScored){this.goalsScored=goalsScored;}
    public void setGoalsReceived(int goalsReceived){this.goalsReceived=goalsReceived;}
    public void setPoints(int points){this.points=points;}

    public FootballClub(){};

    public FootballClub(String clubname,String clubLocation, String clubStadium){

        super(clubname,clubLocation,clubStadium);

    };

    public FootballClub(String clubname,String clubLocation, String clubStadium,int wins,int defeats,int draws,int matchesPlayed,int goalsScored,int goalsReceived,int points){

        super(clubname,clubLocation,clubStadium);
        this.wins=wins;
        this.defeats=defeats;
        this.draws=draws;
        this.matchesPlayed=matchesPlayed;
        this.goalsScored=goalsScored;
        this.goalsReceived=goalsReceived;
        this.points=points;

    };

    //constructor for TableView in javaFX
    // for the purpose of adding the position to the tableView
    // by setting it directly in a constructor
    // this constructor is only called in the GUI class
    public FootballClub(int position,String clubname,String clubLocation, String clubStadium,int wins,int defeats,int draws,int matchesPlayed,int goalsScored,int goalsReceived,int points){

        super(clubname,clubLocation,clubStadium);
        this.position=position;
        this.wins=wins;
        this.defeats=defeats;
        this.draws=draws;
        this.matchesPlayed=matchesPlayed;
        this.goalsScored=goalsScored;
        this.goalsReceived=goalsReceived;
        this.points=points;

    };

    public int getGoalDifference(){return goalsScored-goalsReceived;}

    public String toString(){
        return "Club name = " + getClubName() +
                " | club location = " + getClubLocation() +
                " | club stadium = " + getClubStadium() +
                " | wins = " + this.wins +
                " | defeats = " + this.defeats +
                " | draws = " + this.draws +
                " | matches played = " + this.matchesPlayed +
                " | goals scored = " + this.goalsScored +
                " | goals received = " + this.goalsReceived +
                " | G/D = " + this.getGoalDifference()+
                " | points = " + this.points;
    }

    public void setPosition(int position){this.position=position;}
    public int getPosition(){return this.position;}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FootballClub footballClub = (FootballClub) object;
        return sportsClub.equals(object) &&
                wins == footballClub.wins &&
                draws == footballClub.draws &&
                defeats == footballClub.defeats &&
                matchesPlayed == footballClub.matchesPlayed &&
                goalsScored == footballClub.goalsScored &&
                goalsReceived == footballClub.goalsReceived &&
                points == footballClub.points;
    }
    @Override
    public int hashCode() {
        return Objects.hash(wins, draws, defeats, matchesPlayed, goalsScored, goalsReceived, points);
    }
    // if points are the same, goal difference willl be compared
    // in order to decide who gets in a higher position
    @Override
    public int compareTo(FootballClub fc) {
        int comparePoints=fc.getPoints();
        if(comparePoints-this.points==0){
            int compareGD=fc.getGoalDifference();
            return compareGD-this.getGoalDifference();
        }else{
            return comparePoints-this.points;
        }
    }
}
