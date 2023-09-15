import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NOTES } from 'src/app/models/mock-note';
import { Note } from 'src/app/models/note';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-list-notes',
  templateUrl: './list-notes.component.html',
  styleUrls: ['./list-notes.component.scss']
})
export class ListNotesComponent {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private noteService: NoteService
  ) { }

  data = [];
  notes: Note[] = [];
  selectedNote?: Note;

  ngOnInit(): void {
    this.getNotes();
    // this.notes = NOTES;
  }

  onSelect(note: Note): void {
    this.selectedNote = note;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }

  getNotes(): void {
    this.noteService.getNotes().subscribe(res => {
      this.notes = res.list;
      console.log(this.notes);
    });
  }

  delete(note: Note): void {
    this.notes = this.notes.filter(h => h !== note);
    this.noteService.deleteNote(note.id).subscribe();
  }

  addNote() {
    this.router.navigate(['/add']);
  };  
}
