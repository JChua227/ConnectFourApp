import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectFourGamePage } from './connect-four-game.page';

describe('ConnectFourGamePage', () => {
  let component: ConnectFourGamePage;
  let fixture: ComponentFixture<ConnectFourGamePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConnectFourGamePage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConnectFourGamePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
