import { Component, OnInit } from '@angular/core';
import {HttpClientService} from '../service/http-client.service';
import {MatchEntity} from '../match.entity';

@Component({
  selector: 'app-match-page',
  templateUrl: './match-page.component.html',
  styleUrls: ['./match-page.component.css']
})
export class MatchPageComponent implements OnInit {

  constructor(private httpClientService: HttpClientService) {
    this.matchRegistry = [];
    this.tempMatchRegistry = [];
  }
  matchRegistry: MatchEntity[];
  tempMatchRegistry: MatchEntity[];

  ngOnInit(): void {
    this.httpClientService.getMatchRegistry().subscribe(
      response1 => this.handleSuccessfulResponse1(response1),
    );
  }

  handleSuccessfulResponse1(response1: any): void {
    this.matchRegistry = response1;
  }
  public searchDate(): void{
    const userDate = (document.getElementById('formInput') as HTMLInputElement).value;
    const strDay: string = userDate.slice(8, 10);
    const strMonth: string = userDate.slice(5, 7);
    const strYear: string = userDate.slice(0, 4);
    const day: number = +strDay;
    const month: number = +strMonth;
    const year: number = +strYear;
    this.matchRegistry = this.matchRegistry.filter(
      match => match.dateTime.day === day && match.dateTime.month === month && match.dateTime.year === year);
  }
  public backDate(): void{
    location.reload();
  }
}
