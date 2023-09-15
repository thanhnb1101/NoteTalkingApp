import { Injectable } from '@angular/core';
import { NOTES } from '../models/mock-note';
import { Note } from '../models/note';
import { Observable, map, of, tap } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environments';
import {catchError} from 'rxjs/operators'; 
import { HttpOptionUtilService } from '../utils/http-option-util.service';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(
    private http: HttpClient,
    private httpOption: HttpOptionUtilService
  ) {
   
  }


  getNotes(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/note/list`, this.httpOption.getOptions());
  }

  getNote(id: number): Observable<any> {
    const url = `${environment.apiUrl}/note/detail/${id}`;
    return this.http.get<any>(url, this.httpOption.getOptions());
  }

  
  addNote(note: Note): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}/note/create`, note, this.httpOption.getOptions());
  }

  
  deleteNote(id: number): Observable<Note> {
    const url = `${environment.apiUrl}/note/delete/${id}`;

    return this.http.delete<Note>(url, this.httpOption.getOptions());
  }


  updateNote(note: Note): Observable<any> {
    return this.http.put(`${environment.apiUrl}/note/update`, note, this.httpOption.getOptions());
  }

  
}
