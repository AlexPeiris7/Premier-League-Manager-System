export class FootballClubEntity {
  constructor(position: number,
              clubName: string,
              matchesPlayed: number,
              wins: number,
              draws: number,
              defeats: number,
              goalsScored: number,
              goalsReceived: number,
              goalsDifference: number,
              points: number)
  {
    this.position = position;
    this.clubName = clubName;
    this.matchesPlayed = matchesPlayed;
    this.wins = wins;
    this.draws = draws;
    this.defeats = defeats;
    this.goalsScored = goalsScored;
    this.goalsReceived = goalsReceived;
    this.points = points;
    this.goalsDifference = goalsDifference;
  }
  position: number;
  clubName: string;
  matchesPlayed: number;
  wins: number;
  draws: number;
  defeats: number;
  goalsScored: number;
  goalsReceived: number;
  goalsDifference: number;
  points: number;
}
