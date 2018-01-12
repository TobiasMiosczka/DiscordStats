import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoiceChannelDetailComponent } from './voice-channel-detail.component';

describe('VoiceChannelDetailComponent', () => {
  let component: VoiceChannelDetailComponent;
  let fixture: ComponentFixture<VoiceChannelDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoiceChannelDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoiceChannelDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
