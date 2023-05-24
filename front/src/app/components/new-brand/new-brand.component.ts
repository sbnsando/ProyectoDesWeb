import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToolsService } from 'src/app/services/tools.service';

@Component({
  selector: 'app-new-brand',
  templateUrl: './new-brand.component.html',
  styleUrls: ['./new-brand.component.css']
})
export class NewBrandComponent {
  formulario: FormGroup = new FormGroup({});
  Options: any = [];
  brand: any = {};

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
      inputTextNombre: ['', Validators.required]
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


  save(){
    //console.log("save" + this.toolId);
    this.brand.name = this.formulario.get('inputTextNombre')?.value;
  

    this.toolsService.createBrand(this.brand)
    .then((t: any) => {
      alert("Marca creada");
      this.router.navigate(['/home']);
    }).finally(() => {
      alert("Marca creada");
      this.router.navigate(['/home']);
    });
    
  }

  cancel(){
    this.router.navigate(['/home']); 
  }

}
