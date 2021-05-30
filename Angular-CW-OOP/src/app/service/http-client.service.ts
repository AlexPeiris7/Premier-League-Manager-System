import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {FootballClubEntity} from '../footballClub.entity';
import {Observable} from 'rxjs';
import {MatchEntity} from '../match.entity';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) {}

  getPL(): Observable<FootballClubEntity[]> {
    return this.httpClient.get<FootballClubEntity[]>('http://localhost:8080/leagueList');
  }
  getMatchRegistry(): Observable<MatchEntity[]> {
    return this.httpClient.get<MatchEntity[]>('http://localhost:8080/matchList');
  }
  getAutoPlayedMatch(): Observable<MatchEntity[]> {
    return this.httpClient.get<MatchEntity[]>('http://localhost:8080/match');
  }
}
