package com.alexio.plm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Controller {
    PlmApplication plm = new PlmApplication();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/leagueList")
    public List<FootballClub> getPremierLeague(){
        return plm.getPremierLeague();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/matchList")
    public List<Match> getMatchRegistry(){
        return plm.getMatchRegistry();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/match")
    public List<Match> getAutoMatchPlayed(){
        List<Match> newAddedMatch = new ArrayList<>();
        newAddedMatch.add(plm.addAutoMatch());
        return newAddedMatch;
    }
}
