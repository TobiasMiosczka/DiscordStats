import { TestBed, inject } from '@angular/core/testing';

import { DiscordStatsService } from './discord-stats.service';

describe('DiscordStatsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiscordStatsService]
    });
  });

  it('should be created', inject([DiscordStatsService], (service: DiscordStatsService) => {
    expect(service).toBeTruthy();
  }));
});
