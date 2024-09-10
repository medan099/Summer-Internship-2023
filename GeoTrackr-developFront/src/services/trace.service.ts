import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Trace } from '../models/trace';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class TraceService {
  private apiServerUrl = environment.apiUrl;

  constructor(private http: HttpClient){}

  public getTraces(): Observable<Trace[]> {
    return this.http.get<Trace[]>(`${this.apiServerUrl}/trace/all`);
  }

  public getValideTraces(): Observable<Trace[]> {
    return this.http.get<Trace[]>(`${this.apiServerUrl}/trace/ValideTraces`);
  }
  public getBrouillons(): Observable<Trace[]> {
    return this.http.get<Trace[]>(`${this.apiServerUrl}/trace/Brouillons`);
  }


  public addTraceByAdmin(trace: Trace): Observable<Trace> {
    return this.http.post<Trace>(`${this.apiServerUrl}/trace/addbyadmin`, trace);
  }
  public addTrace(trace: Trace): Observable<Trace> {
    return this.http.post<Trace>(`${this.apiServerUrl}/trace/add`, trace);
  }



  public updateTrace(data:any,id: Number): Observable<Trace> {
    return this.http.patch<Trace>(`${this.apiServerUrl}/trace/`+id,data);
  }

  public deleteTrace(traceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/trace/delete/${traceId}`);
  }


}
