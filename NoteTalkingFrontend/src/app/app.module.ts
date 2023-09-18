import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListNotesComponent } from './components/list-notes/list-notes.component';
import { DetailNoteComponent } from './components/detail-note/detail-note.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpOptionUtilService } from './utils/http-option-util.service';
import { NoteFormComponent } from './components/note-form/note-form.component';
import { CustomValidators } from './utils/custom-validation';

@NgModule({
  declarations: [
    AppComponent,
    ListNotesComponent,
    DetailNoteComponent,
    LoginComponent,
    RegisterComponent,
    NoteFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    HttpOptionUtilService,
    CustomValidators
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
