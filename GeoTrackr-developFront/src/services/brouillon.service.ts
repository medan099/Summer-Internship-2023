import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Trace } from '../models/trace';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class BrouillonService {
  private apiServerUrl = environment.apiUrl;

  constructor(private http: HttpClient){}


  public validerTrace(traceId: number): Observable<Trace> {
    return this.http.patch<Trace>(`${this.apiServerUrl}/trace/valider/${traceId}`,traceId);
  }
  public rejeterTrace(traceId: number): Observable<Trace> {
    return this.http.patch<Trace>(`${this.apiServerUrl}/trace/rejeter/${traceId}`,traceId);
  }

}
