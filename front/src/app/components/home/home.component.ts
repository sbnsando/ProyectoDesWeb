
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToolsService } from 'src/app/services/tools.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  tools: any[] = [];
  brands: any[] = [];
  formulario: FormGroup = new FormGroup({});
  Options: any = ['Nombre', 'Marca'];


  constructor(private fb: FormBuilder, private toolsService: ToolsService ){ 

    this.crearFormulario();

    this.toolsService.getAllTools()
      .then((arrayTools: any) => {
        this.tools = arrayTools;
        this.toolsService.getAllBrands()
          .then((arrayBrands: any) => {
            this.brands = arrayBrands;
            this.tools.map((tool: any) => {
              tool.brand = this.brands.find((b: any) => b.id === tool.idBrand).name || 'Sin marca';
            })
            //console.log(this.tools);
          });
      });
  }

  /**
   * Método que crea el formulario
   */
  crearFormulario() {
    this.formulario = this.fb.group({
      inputText: ['', Validators.required],
      selected: ['', Validators.required]
    })
  }

  get inputNotValid() {
    return this.formulario.get('inputText')?.invalid && this.formulario.get('inputText')?.touched;
  }

  get selectNotValid() {
    return this.formulario.get('selected')?.invalid && this.formulario.get('selected')?.touched;
  }

  getFilteredToolsByName(name: string) {
    return this.toolsService.getFilteredToolsByName(name);
  }

  getFilteredToolsByBrand(brand: string) {
    return this.toolsService.getFilteredToolsByBrand(brand);
  }

  search() {
    console.log(this.formulario.value.selected);
  
    if(!this.formulario.invalid && this.formulario.value.inputText != '')
    {
      if ( this.formulario.value.selected == 'Nombre') {
        this.getFilteredToolsByName(this.formulario.value.inputText)
          .then((arrayTools: any) => {
            //console.log(data);
            this.tools = arrayTools;

            this.tools.map((tool: any) => {
              tool.brand = this.brands.find((b: any) => b.id === tool.idBrand).name || 'Sin marca';
            } )
          });
      }

      if(this.formulario.value.selected == 'Marca'){
        this.getFilteredToolsByBrand(this.formulario.value.inputText)
          .then((arrayTools: any) => {
            this.tools = arrayTools;
            this.tools.map((tool: any) => {
              tool.brand = this.brands.find((b: any) => b.id === tool.idBrand).name || 'Sin marca';
            } )
            
          });
      }
    }

  }

}
