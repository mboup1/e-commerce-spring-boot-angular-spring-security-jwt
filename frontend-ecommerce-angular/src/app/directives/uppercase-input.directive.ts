import { Directive, HostListener } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appUppercaseInput]'
})
export class UppercaseInputDirective {

  constructor(
    private control: NgControl
  ) { }

  //Directive pour que les lettres d'un champ input soient en majuscule

  @HostListener('input', ['$event']) onInputChange($event: any) {
    if (this.control) {
      this.control.control?.setValue($event.target.value.toUpperCase());
    }
  }

}
