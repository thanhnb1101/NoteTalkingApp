import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Note } from 'src/app/models/note';
import { NoteService } from 'src/app/services/note.service';
import { CustomValidators } from 'src/app/utils/custom-validation';

@Component({
  selector: 'app-note-form',
  templateUrl: './note-form.component.html',
  styleUrls: ['./note-form.component.scss']
})
export class NoteFormComponent {
  addNoteForm: any;
  newNote: Note;

  constructor(
    private router: Router,
    private noteService: NoteService,
    private customValidators: CustomValidators
  ) { this.createForm() }

  get f() { return this.addNoteForm.controls; }
  createForm() {
    this.addNoteForm = new FormGroup({
      title: new FormControl('', [Validators.required, this.customValidators.noteValidator]),
      content: new FormControl('', [Validators.required, this.customValidators.noteValidator])
    });
  }

  onSubmit() {
    if (this.f.title.errors.required || this.f.title.errors.noteValidator || this.f.content.errors.required || this.f.content.errors.noteValidator) {
      console.log('addNoteForm invalid');
      console.log(this.addNoteForm.value);
      return;
    }
    this.newNote = this.addNoteForm.value;
    console.log(this.newNote);
    this.noteService.addNote(this.newNote)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['list-notes']);
        },
        error => {
          console.log(error);
          this.router.navigate(['add']);
        });
  }
}
