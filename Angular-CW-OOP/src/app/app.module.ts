import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { MatchPageComponent } from './match-page/match-page.component';
import { LeaguePageComponent } from './league-page/league-page.component';

@NgModule({
  declarations: [
    AppComponent,
    MatchPageComponent,
    LeaguePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [LeaguePageComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
