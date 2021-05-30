package com.alexio.plm;
import java.io.IOException;

public interface LeagueManager {
    void addClub();
    void deleteClub();
    void displayClub();
    void displayTable();
    void addMatch();
    void saveToFile(String file)throws IOException;
    void loadInformation(String file)throws IOException,ClassNotFoundException;
    void exitSystem();
}
