import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToolsService } from 'src/app/services/tools.service';

@Component({
  selector: 'app-new-tool',
  templateUrl: './new-tool.component.html',
  styleUrls: ['./new-tool.component.css']
})
export class NewToolComponent {

  formulario: FormGroup = new FormGroup({});
  Options: any = [];
  tool: any = {};

  constructor(private router: Router,private _Activatedroute:ActivatedRoute,  private fb: FormBuilder, private toolsService: ToolsService) {
    this.crearFormulario();
    //this.toolId=this._Activatedroute.snapshot.paramMap.get("id");
    /*this.toolsService.getToolById(this.toolId)
      .then((t: any) => {
        this.tool = t;
        //console.log(this.tool)
        //this.setFormulario();
      });*/

    
  }

  crearFormulario() {
    this.formulario = this.fb.group({
      inputTextNombre: ['', Validators.required],
      selected: ['', Validators.required],
      inputTextDesc: ['', Validators.required],
      inputCant: ['', Validators.required],
      inputTextCountry: ['', Validators.required],
      inputPrice: ['', Validators.required]
    })

    
  }

  /*setFormulario(){
    this.formulario.setValue({
      inputTextNombre: this.tool.name,
      selected: this.tool.idBrand,
      inputTextDesc: this.tool.description,
      inputCant: this.tool.quantity,
      inputTextCountry: this.tool.country,
      inputPrice: this.tool.price
    });
  }*/

  get inputNotValid() {
    return this.formulario.get('inputText')?.invalid && this.formulario.get('inputText')?.touched;
  }

  get selectNotValid() {
    return this.formulario.get('selected')?.invalid && this.formulario.get('selected')?.touched;
  }

  save(){
    //console.log("save" + this.toolId);
    this.tool.idBrand = 1;
    this.tool.name = this.formulario.get('inputTextNombre')?.value;
    //this.tool.idBrand = this.formulario.get('selected')?.value;
    this.tool.description = this.formulario.get('inputTextDesc')?.value;
    this.tool.price = this.formulario.get('inputPrice')?.value;
    this.tool.img='https://www.rhsupplyco.com/images/content/GenericTools_TestInstrumentsHandTools_06222020.png';
    this.tool.country = this.formulario.get('inputTextCountry')?.value;
    this.tool.cities='';
    this.tool.quantity = this.formulario.get('inputCant')?.value;    

    this.toolsService.createTool(this.tool)
    .then((t: any) => {
      alert("Herramienta creada");
      this.router.navigate(['/home']);
    }).finally(() => {
      alert("Herramienta creada");
      this.router.navigate(['/home']);
    });
    
  }

  cancel(){
    this.router.navigate(['/home']); 
  }

}
