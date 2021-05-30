import { Component, OnInit } from '@angular/core';
import {HttpClientService} from '../service/http-client.service';
import {FootballClubEntity} from '../footballClub.entity';
import { Router } from '@angular/router';

@Component({
  selector: 'app-league-page',
  templateUrl: './league-page.component.html',
  styleUrls: ['./league-page.component.css']
})
export class LeaguePageComponent implements OnInit {

  constructor(private httpClientService: HttpClientService, private router: Router) {
    this.premierLeague = [];
  }
  premierLeague: FootballClubEntity[];

  ngOnInit(): void {
    this.httpClientService.getPL().subscribe(
      response1 => this.handleSuccessfulResponse1(response1),
    );
  }

  handleSuccessfulResponse1(response1: any): void {
    this.premierLeague = response1;
  }
  sortWins(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.wins < fc2.wins) {
        return 1;
      }
      if (fc1.wins > fc2.wins) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortDefeats(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.defeats < fc2.defeats) {
        return 1;
      }
      if (fc1.defeats > fc2.defeats) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortDraws(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.draws < fc2.draws) {
        return 1;
      }
      if (fc1.draws > fc2.draws) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortGS(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.goalsScored < fc2.goalsScored) {
        return 1;
      }
      if (fc1.goalsScored > fc2.goalsScored) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortGR(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.goalsReceived < fc2.goalsReceived) {
        return 1;
      }
      if (fc1.goalsReceived > fc2.goalsReceived) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortGD(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.goalsScored - fc1.goalsReceived < fc2.goalsScored - fc2.goalsReceived) {
        return 1;
      }
      if (fc1.goalsScored - fc1.goalsReceived > fc2.goalsScored - fc2.goalsReceived) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
  sortPoints(): void{
    let sortedList: FootballClubEntity[] = this.premierLeague.sort((fc1, fc2) => {
      if (fc1.points < fc2.points) {
        return 1;
      }
      if (fc1.points > fc2.points) {
        return -1;
      }
      return 0;
    });
    sortedList = this.premierLeague;
  }
}
