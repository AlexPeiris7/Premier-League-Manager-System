package com.alexio.plm;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;
    private String clubStadium;

    public SportsClub(){}

    public SportsClub(String clubName,String clubLocation,String clubStadium){

        this.clubName=clubName;
        this.clubLocation=clubLocation;
        this.clubStadium=clubStadium;

    }

    public String getClubName() {return this.clubName;}
    public String getClubLocation() {return this.clubLocation;}
    public String getClubStadium() {return this.clubStadium;}

    public void setClubName(String clubName){this.clubName=clubName;};
    public void setClubLocation(String clubLocation){this.clubLocation=clubLocation;};
    public void setClubStadium(String clubStadium){this.clubStadium=clubStadium;};

    public String toString(){
        return "Club name = " + this.clubName +
                " club location = " + this.clubLocation +
                " club stadium = " + this.clubStadium ;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SportsClub sportsClub = (SportsClub) object;
        return Objects.equals(clubName, sportsClub.clubName) &&
                Objects.equals(clubLocation, sportsClub.clubLocation) &&
                Objects.equals(clubStadium, sportsClub.clubStadium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubLocation, clubStadium);
    }
}
