import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ToolCardComponent } from './components/shared/tool-card/tool-card.component';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { NavBarComponent } from './components/shared/nav-bar/nav-bar.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
//Importar rutas
import { ROUTES } from './app.routes';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { UsersComponent } from './components/users/users.component';
import { ToolsComponent } from './components/tools/tools.component';
import { BrandsComponent } from './components/brands/brands.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { EditToolComponent } from './components/edit-tool/edit-tool.component';
import { EditBrandComponent } from './components/edit-brand/edit-brand.component';
import { NewToolComponent } from './components/new-tool/new-tool.component';
import { NewBrandComponent } from './components/new-brand/new-brand.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolCardComponent,
    HomeComponent,
    SearchComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    UsersComponent,
    ToolsComponent,
    BrandsComponent,
    EditUserComponent,
    EditToolComponent,
    EditBrandComponent,
    NewToolComponent,
    NewBrandComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot( ROUTES, {useHash: true} ),
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
