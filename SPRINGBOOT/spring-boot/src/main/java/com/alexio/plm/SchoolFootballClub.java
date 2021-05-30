package com.alexio.plm;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub {

    private String schoolName;

    public void setSchoolName(String schoolName){this.schoolName=schoolName;}

    public String getSchoolName(){return this.schoolName;}

    public SchoolFootballClub(){}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        SchoolFootballClub schoolFootballClub = (SchoolFootballClub) object;
        return schoolName.equals(schoolFootballClub.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }
}
