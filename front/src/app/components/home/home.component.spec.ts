import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HomeComponent } from './home.component';
import { ToolsService } from 'src/app/services/tools.service';


describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let toolsService: ToolsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ FormsModule, HttpClientTestingModule ],
      declarations: [ HomeComponent ],
      providers: [ ToolsService ]
    })
    .compileComponents();

    toolsService = TestBed.inject(ToolsService);
    spyOn(toolsService, 'getAllTools').and.returnValue(Promise.resolve([
      { id: 1, name: 'Tool 1', idBrand: 1 },
      { id: 2, name: 'Tool 2', idBrand: 2 }
    ]));
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load tools and brands', async () => {
    await fixture.whenStable();
    fixture.detectChanges();
    expect(component.tools).toEqual([
      { id: 1, name: 'Tool 1', idBrand: 1, brand: undefined },
      { id: 2, name: 'Tool 2', idBrand: 2, brand: undefined }
    ]);
  });
  it('should load tools and brands', async () => {
    spyOn(toolsService, 'getAllBrands').and.returnValue(Promise.resolve([
      { id: 1, name: 'Brand 1' },
      { id: 2, name: 'Brand 2' }
    ]));
  
    await fixture.whenStable();
    fixture.detectChanges();
    expect(component.tools).toEqual([
      { id: 1, name: 'Tool 1', idBrand: 1, brand: 'Brand 1' },
      { id: 2, name: 'Tool 2', idBrand: 2, brand: 'Brand 2' }
    ]);
  });
  
  
});

