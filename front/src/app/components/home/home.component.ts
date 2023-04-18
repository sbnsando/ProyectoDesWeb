
import { Component } from '@angular/core';
import { ToolsService } from 'src/app/services/tools.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  toolsArray = [
    {imgSrc: 'https://img.freepik.com/premium-vector/mechanic-tool-set-vector-construction-tools-home-repairs-isolated-white-background_68708-1541.jpg?w=600&q=60', title: 'Yellow tools', desc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel. Vivamus tellus tellus, lobortis id massa id, ultricies mattis urna', brand: 'CAT'},
    {imgSrc: 'https://images.unsplash.com/photo-1530124566582-a618bc2615dc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', title: 'Red tools', desc: 'orem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel. Vivamus tellus tellus, lobortis id massa id, ultricies mattis urna', brand: 'RED'},
    {imgSrc: 'https://images.unsplash.com/photo-1502068898470-ad70c83938be?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', title: 'Black tools', desc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel. Vivamus tellus tellus, lobortis id massa id, ultricies mattis urna', brand: 'VLACK'},
    {imgSrc: 'https://images.unsplash.com/photo-1567361808960-dec9cb578182?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=600&q=60', title: 'Orange', desc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel. Vivamus tellus tellus, lobortis id massa id, ultricies mattis urna', brand: 'OITNB'},
    {imgSrc: 'https://images.unsplash.com/photo-1586864387789-628af9feed72?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDN8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60', title: 'Brown', desc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer venenatis pulvinar leo, eget mattis tortor imperdiet vel. Vivamus tellus tellus, lobortis id massa id, ultricies mattis urna', brand: 'BRWN'}
  ]

  constructor( private toolsService: ToolsService ){ 

    //this.toolsService.getAllTools();

  }
}
