import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ListNotesComponent } from './components/list-notes/list-notes.component';
import { DetailNoteComponent } from './components/detail-note/detail-note.component';
import { RegisterComponent } from './components/register/register.component';
import { NoteFormComponent } from './components/note-form/note-form.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'add', component: NoteFormComponent },
  { path: 'list-notes', component: ListNotesComponent },
  { path: 'detail/:id', component: DetailNoteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
