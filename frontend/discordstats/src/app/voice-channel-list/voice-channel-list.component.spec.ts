import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoiceChannelListComponent } from './voice-channel-list.component';

describe('VoiceChannelListComponent', () => {
  let component: VoiceChannelListComponent;
  let fixture: ComponentFixture<VoiceChannelListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoiceChannelListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoiceChannelListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
