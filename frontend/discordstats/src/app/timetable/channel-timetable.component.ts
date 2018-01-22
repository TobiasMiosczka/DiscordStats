import { Component, SimpleChanges } from "@angular/core";
import { TimetableComponent } from "./timetable.component";
import { DiscordVoiceChannelUsage } from "../dto/DiscordVoiceChannelUsage";
import { MatDialog } from "@angular/material";
import { OnChanges } from "@angular/core/src/metadata/lifecycle_hooks";

declare var google: any;

@Component({
  selector: 'channel-timetable',
  templateUrl: './timetable.component.html'})
export class ChannelTimetableComponent extends TimetableComponent implements OnChanges{
  
  
  ngOnChanges(changes: SimpleChanges): void {
    super.ngOnChanges(changes);
  }

  constructor(public dialog: MatDialog) {super(dialog);}

  

  toArray(data: DiscordVoiceChannelUsage): any {
    return [ data.discordUser.name, new Date(data.dateFrom), new Date(data.dateTo) ];
  }

  getDataTable(): any {
    let dataTable = new google.visualization.DataTable();
    dataTable.addColumn({ type: 'string', id: 'User' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
    return dataTable;
  }

  getNumRows(): number {
    let set: Set<string> = new Set<string>();
    for(let d of this.data) {
      set.add(d.discordUser.id);
    }
    return set.size;
  }
}