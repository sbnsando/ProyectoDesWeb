import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ToolsService } from './tools.service';

describe('ToolsService', () => {
  let service: ToolsService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ToolsService]
    });

    service = TestBed.inject(ToolsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all tools', () => {
    const mockResponse = { /* mocked response */ };

    service.getAllTools().then((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('http://localhost:8081/tool/list');
    expect(req.request.method).toBe('GET');

    req.flush(mockResponse);
  });

  it('should get all brands', () => {
    const mockResponse = { /* mocked response */ };

    service.getAllBrands().then((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('http://localhost:8080/brand/list');
    expect(req.request.method).toBe('GET');

    req.flush(mockResponse);
  });

  it('should get filtered tools by name', () => {
    const mockResponse = { /* mocked response */ };

    service.getFilteredToolsByName('test').then((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('http://localhost:8081/tool/filter?name=test');
    expect(req.request.method).toBe('GET');

    req.flush(mockResponse);
  });

  it('should get filtered tools by brand', () => {
    const mockResponse = { /* mocked response */ };

    service.getFilteredToolsByBrand('test').then((data) => {
      expect(data).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('http://localhost:8081/tool/filter?brand=test');
    expect(req.request.method).toBe('GET');

    req.flush(mockResponse);
  }
});


