import { TestBed } from '@angular/core/testing';

import { ViewGradesService } from './view-grades.service';

describe('ViewGradesService', () => {
  let service: ViewGradesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewGradesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
