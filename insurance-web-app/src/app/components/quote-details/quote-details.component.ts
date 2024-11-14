import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Quote } from 'src/app/models/quote';
import { InsuranceService } from 'src/app/services/insurance.service';

@Component({
  selector: 'app-quote-details',
  templateUrl: './quote-details.component.html',
  styleUrls: ['./quote-details.component.css'],
})
export class QuoteDetailsComponent {
  referenceForm: FormGroup;

  // object which contains the details of a requested quote
  quote?: Quote;

  // optional error message if user enter an invalid quote
  showErrorMsg: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private insuranceService: InsuranceService
  ) {
    this.referenceForm = this.formBuilder.group({
      reference: ['', [Validators.required, Validators.minLength(16), Validators.maxLength(16)]],
    });

    // subscribe to query params so user can refresh page and access quote by url
    this.route.queryParams.subscribe((params) => {
      this.showErrorMsg = false;

      // if no reference in url, empty the quote object and display input form instead
      let reference = params['reference'];
      if (!reference) {
        this.quote = undefined;
        return;
      }

      this.insuranceService.getQuoteByReference(reference).subscribe((res: Quote) => {
        if (!res) {
          this.showErrorMsg = true;
          return;
        }
        this.quote = res;
      });
    });
  }

  get form() {
    return this.referenceForm.controls;
  }

  onSubmit() {
    // this is to highlight any invalid fields on submit
    this.referenceForm.markAllAsTouched();

    if (this.referenceForm.invalid) {
      return;
    }

    // if entered reference is 16 chars, set it to query params
    // this will trigger above subscription and display quote details if found
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        reference: this.form['reference'].value,
      },
    });
  }

  backClicked() {
    this.location.back();
  }

  supportClicked() {
    this.router.navigate(['/contact']);
  }

  printPage(): void {
    window.print();
  }
}
