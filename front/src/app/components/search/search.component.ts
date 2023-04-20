import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToolsService } from 'src/app/services/tools.service';

/**
 * Componente que muestra el formulario de búsqueda
 */
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  formulario: FormGroup = new FormGroup({});

  Options: any = ['Nombre', 'Marca'];

  tools: any = [];

  constructor(private fb: FormBuilder, private toolsService: ToolsService) {
    this.crearFormulario();
  }

  ngOnInit(): void { }

  get inputNotValid() {
    return this.formulario.get('inputText')?.invalid && this.formulario.get('inputText')?.touched;
  }

  get selectNotValid() {
    return this.formulario.get('selected')?.invalid && this.formulario.get('selected')?.touched;
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

  /**
   * Método que realiza la búsqueda
   */
  search() {
    //console.log(this.formulario.value);
    if(!this.formulario.invalid && this.formulario.value.inputText != '')
    {
      if ( this.formulario.value.selected == 'Nombre') {
        this.toolsService.getFilteredToolsByName(this.formulario.value.inputText)
          .then((data: any) => {
            console.log(data);
            this.tools = data;
          });
      }

      if(this.formulario.value.selected == 'Marca'){
        this.toolsService.getFilteredToolsByBrand(this.formulario.value.inputText)
          .then((data: any) => {
            this.tools = data;
            //console.log(this.tools);
          });
      }

    }

  }
}
