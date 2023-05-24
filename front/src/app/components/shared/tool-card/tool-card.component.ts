import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tool-card',
  templateUrl: './tool-card.component.html',
  styleUrls: ['./tool-card.component.css']
})
export class ToolCardComponent {
  @Input() items:any[] = [];

  constructor(private router: Router) {
    
  }


  editTool(id:number) {
    this.router.navigate(['/edit-tool', id]); 
  }
    

}
