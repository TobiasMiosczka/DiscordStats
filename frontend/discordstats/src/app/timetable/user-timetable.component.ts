import { Component, SimpleChanges, OnChanges } from "@angular/core";
import { TimetableComponent } from "./timetable.component";
import { DiscordVoiceChannelUsage } from "../dto/DiscordVoiceChannelUsage";
import { MatDialog } from "@angular/material";

declare var google: any;

@Component({
  selector: 'user-timetable',
  templateUrl: './timetable.component.html'})
export class UserTimetableComponent extends TimetableComponent implements OnChanges{

  ngOnChanges(changes: SimpleChanges): void {
    super.ngOnChanges(changes);
  }

  constructor(public dialog: MatDialog) {super(dialog);}

  toArray(data: DiscordVoiceChannelUsage): any {
    return [ data.discordVoiceChannel.name, new Date(data.dateFrom), new Date(data.dateTo) ];
  }

  getDataTable(): any {
    let dataTable = new google.visualization.DataTable();
    dataTable.addColumn({ type: 'string', id: 'Channel' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
    return dataTable;
  }

  getNumRows(): number {
    let set: Set<string> = new Set<string>();
    for(let d of this.data) {
      set.add(d.discordVoiceChannel.id);
    }
    return set.size;
  }
}