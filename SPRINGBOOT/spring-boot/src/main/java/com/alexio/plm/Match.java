package com.alexio.plm;

import java.io.Serializable;
import java.util.Comparator;

public class Match implements Comparator<Match>, Serializable {

    private DateTime dateTime;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;

    public Match(){}
    public Match(DateTime dateTime,String homeTeam,String awayTeam,int homeTeamGoals,int awayTeamGoals){

        this.dateTime=dateTime;
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
        this.homeTeamGoals=homeTeamGoals;
        this.awayTeamGoals=awayTeamGoals;

    }
    
    public void setDateTime(DateTime dateTime){this.dateTime=dateTime;}
    public void setHomeTeam(String homeTeam){this.homeTeam=homeTeam;}
    public void setAwayTeam(String awayTeam){this.awayTeam=awayTeam;}
    public void setHomeTeamGoals(int homeTeamGoals){this.homeTeamGoals=homeTeamGoals;}
    public void setAwayTeamGoals(int awayTeamGoals){this.awayTeamGoals=awayTeamGoals;}

    public DateTime getDateTime(){return dateTime;}
    public String getHomeTeam(){return this.homeTeam;}
    public String getAwayTeam(){return this.awayTeam;}
    public int getHomeTeamGoals(){return this.homeTeamGoals;}
    public int getAwayTeamGoals(){return this.awayTeamGoals;}


    public String getWinner(){
        if(this.homeTeamGoals>this.awayTeamGoals){
            return homeTeam;
        }else if(this.homeTeamGoals==this.awayTeamGoals){
            return "draw";
        }else{
            return awayTeam;
        }
    };

    public String toString(){
        return dateTime.toString()+" "+": "+ homeTeam + " " + homeTeamGoals +" - " + awayTeamGoals +" "+ awayTeam;
    }

    @Override
    public int compare(Match m1,Match m2) {
        return m1.dateTime.compareTo(m2.dateTime);
    }


}
