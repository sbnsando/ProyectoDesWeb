import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { LoginComponent } from './components/login/login.component';
import { EditToolComponent } from './components/edit-tool/edit-tool.component';
import { EditBrandComponent } from './components/edit-brand/edit-brand.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { NewToolComponent } from './components/new-tool/new-tool.component';
import { NewBrandComponent } from './components/new-brand/new-brand.component';
import { UsersComponent } from './components/users/users.component';

export const ROUTES: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'edit-tool/:id', component: EditToolComponent },
    { path: 'edit-brand/:id', component: EditBrandComponent },
    { path: 'edit-user/:id', component: EditUserComponent },
    { path: 'new-tool', component: NewToolComponent },
    { path: 'new-brand', component: NewBrandComponent },
    { path: 'users', component: UsersComponent },
    // { path: 'search', component: SearchComponent },
    { path: 'login', component: LoginComponent },
    { path: '', pathMatch: 'full', redirectTo: 'home' },
    { path: '**', pathMatch: 'full', redirectTo: 'home' }
];