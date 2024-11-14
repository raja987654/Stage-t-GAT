import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './components/contact/contact.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { QuoteDetailsComponent } from './components/quote-details/quote-details.component';
import { QuoteFormComponent } from './components/quote-form/quote-form.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'calculate', component: QuoteFormComponent },
  { path: 'quotes', component: QuoteDetailsComponent },
  { path: 'contact', component: ContactComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
