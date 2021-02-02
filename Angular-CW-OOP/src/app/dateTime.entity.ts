export class DateTimeEntity {
  constructor(minutes: number,
              hour: number,
              day: number,
              month: number,
              year: number,
              )
  {
    this.minutes = minutes;
    this.hour = hour;
    this.day = day;
    this.month = month;
    this.year = year;
  }

  minutes: number;
  hour: number;
  day: number;
  month: number;
  year: number;
  toString(): string{
    return this.minutes + ':' + this.hour +  ' ' + this.day + '-' + this.month + '-' + this.year ;
  }
}

