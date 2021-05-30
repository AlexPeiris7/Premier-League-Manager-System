import { Component } from '@angular/core';
import {HttpClientService} from './service/http-client.service';
import {MatchEntity} from './match.entity';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular OOP CW Application';
  constructor(private httpClientService: HttpClientService) {
    this.newMatchAdded = [];
  }
  newMatchAdded: MatchEntity[];
  public addMatch(): void{
    if (this.newMatchAdded[0] === null){
      alert('League is over or teams in league is less than 2...');
      return;
    }
    this.httpClientService.getAutoPlayedMatch().subscribe(
      response2 => {
        this.handleSuccessfulResponse2(response2);
        try {
          const match = this.newMatchAdded[0];
          alert(
            match.dateTime.hour + ':' +
            match.dateTime.minutes + ' ' +
            match.dateTime.day + '-' +
            match.dateTime.month + '-' +
            match.dateTime.year + ' ' +
            match.homeTeam + ' ' +
            match.homeTeamGoals + ' - ' +
            match.awayTeam + ' ' +
            match.awayTeamGoals
          );
          location.reload();
        }catch (err){}
      });
  }
  handleSuccessfulResponse2(response2: any): void {
    this.newMatchAdded = response2;
  }
}
