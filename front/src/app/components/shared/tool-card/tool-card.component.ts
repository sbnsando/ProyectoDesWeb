import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tool-card',
  templateUrl: './tool-card.component.html',
  styleUrls: ['./tool-card.component.css']
})
export class ToolCardComponent {
  @Input() items:any[] = [];
}
