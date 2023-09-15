import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Note } from 'src/app/models/note';
import { NoteService } from 'src/app/services/note.service';

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
    private noteService: NoteService
  ) { this.createForm() }

  get f() { return this.addNoteForm.controls; }
  createForm() {
    this.addNoteForm = new FormGroup({
      title: new FormControl(''),
      content: new FormControl('')
    });
  }

  onSubmit() {
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
