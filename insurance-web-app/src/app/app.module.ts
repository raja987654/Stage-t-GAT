import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContactComponent } from './components/contact/contact.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { QuoteDetailsComponent } from './components/quote-details/quote-details.component';
import { QuoteFormComponent } from './components/quote-form/quote-form.component';
import { InsuranceService } from './services/insurance.service';

@NgModule({
  declarations: [
    AppComponent,
    QuoteFormComponent,
    HomeComponent,
    NotFoundComponent,
    QuoteDetailsComponent,
    ContactComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule],
  providers: [InsuranceService],
  bootstrap: [AppComponent],
})
export class AppModule {}
