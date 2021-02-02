package com.alexio.plm;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

    private String uniName;

    public void setUniName(String uniName){this.uniName=uniName;}

    public String getUniName(){return this.uniName;}

    public UniversityFootballClub(){}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UniversityFootballClub universityFootballClub = (UniversityFootballClub) object;
        return uniName.equals(universityFootballClub.uniName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniName);
    }
}
