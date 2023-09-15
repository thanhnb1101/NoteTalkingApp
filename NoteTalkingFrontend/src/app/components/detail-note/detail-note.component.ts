import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Note } from 'src/app/models/note';
import { NoteService } from 'src/app/services/note.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-detail-note',
  templateUrl: './detail-note.component.html',
  styleUrls: ['./detail-note.component.scss',]
})
export class DetailNoteComponent {
  note: Note;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private noteService: NoteService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    if (this.id != null) {
      this.getNote();
    } else {
      this.note = {
        id: -1,
        title: "",
        content: "",
        date: ""
      };
    }
    
  }

  getNote(): void {
    // const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.noteService.getNote(this.id)
      .subscribe(note => this.note = note);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.note) {
      this.noteService.updateNote(this.note)
        .subscribe(() => this.goBack());
    }
  }
}
