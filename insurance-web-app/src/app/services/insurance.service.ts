import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Driver } from '../models/driver';
import { Quote } from '../models/quote';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class InsuranceService {
  
  baseUrl: string = 'http://localhost:8080';
  calculateParam: string = '/calculate';
  quotesParam: string = '/quotes';

  constructor(private http: HttpClient) {}

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Une erreur est survenue';
    
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur: ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => errorMessage);
  }

  public getNewQuote(driver: Driver) {
    return this.http.post(this.baseUrl + this.calculateParam, driver, { 
      responseType: 'text' 
    }).pipe(
      catchError(this.handleError)
    );
  }

  public getQuoteByReference(reference: string) {
    return this.http.get<Quote>(this.baseUrl + this.quotesParam, {
      params: {
        reference: reference,
      },
    }).pipe(
      catchError(this.handleError)
    );
  }
}