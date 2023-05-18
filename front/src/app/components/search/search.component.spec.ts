import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ToolsService } from 'src/app/services/tools.service';
import { SearchComponent } from './search.component';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [SearchComponent],
      providers: [
        FormBuilder,
        {
          provide: ToolsService,
          useValue: {
            getFilteredToolsByName: jasmine.createSpy('getFilteredToolsByName'),
            getFilteredToolsByBrand: jasmine.createSpy('getFilteredToolsByBrand'),
          },
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create the form correctly', () => {
    expect(component.formulario).toBeDefined();
    expect(component.formulario.controls['inputText']).toBeDefined();
    expect(component.formulario.controls['selected']).toBeDefined();
  });

  it('should show error message when inputText is empty', () => {
    component.formulario.controls['inputText'].setValue('');
    component.formulario.controls['inputText'].markAsTouched();
    expect(component.inputNotValid).toBeTrue();
  });

  it('should show error message when selected is empty', () => {
    component.formulario.controls['selected'].setValue('');
    component.formulario.controls['selected'].markAsTouched();
    expect(component.selectNotValid).toBeTrue();
  });

  it('should call getFilteredToolsByName method when selected is "Nombre"', () => {
    const inputText = 'test';
    component.formulario.controls['inputText'].setValue(inputText);
    component.formulario.controls['selected'].setValue('Nombre');
    component.search();
    expect(component.getFilteredToolsByName).toHaveBeenCalledWith(inputText);
  });

  it('should call getFilteredToolsByBrand method when selected is "Marca"', () => {
    const inputText = 'test';
    component.formulario.controls['inputText'].setValue(inputText);
    component.formulario.controls['selected'].setValue('Marca');
    component.search();
    expect(component.getFilteredToolsByBrand).toHaveBeenCalledWith(inputText);
  });
});
