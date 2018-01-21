import { Component, OnInit, Input, SimpleChanges} from '@angular/core';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';
import { DiscordStatsService } from '../discord-stats.service';
import { Observable } from 'rxjs/Observable';
import { OnChanges } from '@angular/core/src/metadata/lifecycle_hooks';

declare var google:any;

@Component({
  selector: 'channel-timetable',
  templateUrl: './channel-timetable.component.html'})
export class ChannelTimetableComponent implements OnChanges {

  googleLoaded: boolean = false;

  dataTable: any;
  chart: any;
  container: any;

  @Input()
  data: Array<DiscordVoiceChannelUsage> = new Array<DiscordVoiceChannelUsage>();

  ngOnChanges(changes: SimpleChanges): void {
    this.update();
  }

  drawChart(){
    this.dataTable = new google.visualization.DataTable();
    this.dataTable.addColumn({ type: 'string', id: 'User' });
    this.dataTable.addColumn({ type: 'date', id: 'Start' });
    this.dataTable.addColumn({ type: 'date', id: 'End' });

    let userCount: number = 0;
    for(let i of this.data) {
      this.dataTable.addRow([ i.discordUser.name, new Date(i.dateFrom), new Date(i.dateTo) ]);
      //TODO: only count distinct user
      userCount++;
    }
    
    if(this.container == null) {
      this.container = document.getElementById('chart');
      this.chart = new google.visualization.Timeline(this.container);   
    } 
    this.container.style.height = (41 * userCount + 100) + "px";
    this.chart.draw(this.dataTable);   
  }

  update() {
    if(this.data.length > 0) {
      if(!this.googleLoaded) {
        google.charts.load('current',  {packages: ['timeline']});
        this.googleLoaded = true;
      }
      google.charts.setOnLoadCallback(() =>  this.drawChart());
    }
  }
}