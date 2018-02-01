import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoiceChannelUsagePreviewComponent } from './voice-channel-usage-preview.component';

describe('VoiceChannelUsagePreviewComponent', () => {
  let component: VoiceChannelUsagePreviewComponent;
  let fixture: ComponentFixture<VoiceChannelUsagePreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoiceChannelUsagePreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoiceChannelUsagePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
