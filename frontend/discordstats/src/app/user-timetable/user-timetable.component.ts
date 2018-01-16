import { Component, OnInit, Input, SimpleChanges} from '@angular/core';
import { DiscordVoiceChannelUsage } from '../dto/DiscordVoiceChannelUsage';
import { DiscordStatsService } from '../discord-stats.service';
import { Observable } from 'rxjs/Observable';
import { OnChanges } from '@angular/core/src/metadata/lifecycle_hooks';

declare var google:any;

@Component({
  selector: 'user-timetable',
  templateUrl: './user-timetable.component.html'})
export class UserTimetableComponent implements OnChanges {

  googleLoaded: boolean = false; 

  @Input()
  data: Array<DiscordVoiceChannelUsage> = new Array<DiscordVoiceChannelUsage>();

  ngOnChanges(changes: SimpleChanges): void {
    this.update(this.data);
  }

  drawChart(data: Array<DiscordVoiceChannelUsage>){
    let rowCount: number = 0;
    let dataTable = new google.visualization.DataTable();
    dataTable.addColumn({ type: 'string', id: 'Channel' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
    for(let i of data) {
      dataTable.addRows([[ i.discordVoiceChannel.name, new Date(i.dateFrom), new Date(i.dateTo) ]]);
      //TODO: only count distinct user
      rowCount++;
    }
    let chart = document.getElementById('chart');
    if(chart != null) {
      chart.style.height = (41 * rowCount + 50)+"px";
      new google.visualization.Timeline(chart).draw(dataTable);      
    } 
  }

  update(data: Array<DiscordVoiceChannelUsage>) {
    if(this.data.length > 0) {
      if(!this.googleLoaded) {
        google.charts.load('current',  {packages: ['timeline']});
        this.googleLoaded = true;
      }
      google.charts.setOnLoadCallback(() =>  this.drawChart(data));
    }
  }
}