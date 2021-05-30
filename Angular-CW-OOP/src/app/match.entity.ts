import {DateTimeEntity} from './dateTime.entity';

export class MatchEntity {
  constructor(dateTime: DateTimeEntity,
              homeTeam: string,
              awayTeam: string,
              homeTeamGoals: number,
              awayTeamGoals: number)
  {
    this.dateTime = dateTime;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamGoals = homeTeamGoals;
    this.awayTeamGoals = awayTeamGoals;
  }
  dateTime: DateTimeEntity;
  homeTeam: string;
  awayTeam: string;
  homeTeamGoals: number;
  awayTeamGoals: number;
}
