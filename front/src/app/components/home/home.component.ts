
import { Component } from '@angular/core';
import { ToolsService } from 'src/app/services/tools.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  tools: any[] = [];
  brands: any[] = [];

  constructor( private toolsService: ToolsService ){ 

    this.toolsService.getAllTools()
      .then((data: any) => {
        this.tools = data;

        this.tools.map((tool: any) => {
          this.toolsService.getAllBrands()
            .then((brandd: any) => {
              tool.brand = brandd.find((b: any) => b.id === tool.idBrand).name || 'Sin marca';
            })
        })
      }).finally(() => {
        // console.log(this.tools);
      });
  }

}
