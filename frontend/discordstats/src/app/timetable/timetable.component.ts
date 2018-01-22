import { DiscordVoiceChannelUsageDialogComponent } from "../discord-voice-channel-usage-dialog/discord-voice-channel-usage-dialog.component";
import { MatDialogRef, MatDialog } from "@angular/material";
import { DiscordVoiceChannelUsage } from "../dto/DiscordVoiceChannelUsage";
import { Input, SimpleChanges, Component } from "@angular/core";
import { OnInit, OnChanges } from "@angular/core/src/metadata/lifecycle_hooks";


declare var google: any;

export abstract class TimetableComponent implements OnChanges {
    googleLoaded: boolean = false; 
    container: any;
    chart: any;

    options: any = {
      tooltip: { trigger: 'none' }
    };
  
    discordVoiceChannelUsageDialogRef: MatDialogRef<DiscordVoiceChannelUsageDialogComponent>;
  
    @Input()
    data: Array<DiscordVoiceChannelUsage> = new Array<DiscordVoiceChannelUsage>();
  
    constructor(public dialog: MatDialog) {}

     abstract getDataTable(): any;
  
    abstract getNumRows(): number;

    abstract toArray(data: DiscordVoiceChannelUsage): any; 

    ngOnChanges(changes: SimpleChanges): void {
      this.update();
    }
  
    updateSelection() {
      if(this.chart != null) {
        let index: number = this.chart.getSelection()[0].row;
        let selected = this.data[index];
        this.discordVoiceChannelUsageDialogRef = this.dialog.open(DiscordVoiceChannelUsageDialogComponent, {
          hasBackdrop: true,
          data: selected
        });
      }
    }
  
    drawChart(){
      let dataTable = this.getDataTable();
      for(let i of this.data) {
        dataTable.addRow(this.toArray(i));
      }
      this.container = document.getElementById('chart');
      if(this.container != null) {
        this.container.style.height = (41 * this.getNumRows() + 100) + "px";
        this.chart = new google.visualization.Timeline(this.container);
        if (this.chart != null) {
          this.chart.draw(dataTable, this.options);
          let t: TimetableComponent = this;
          google.visualization.events.addListener(this.chart, 'select', function() {
            t.updateSelection();
          });
        }
      } 
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