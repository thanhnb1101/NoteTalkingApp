import { FormControl } from "@angular/forms";

export class CustomValidators {
    noteValidator(control: FormControl): { [key: string]: boolean } {
        const nameRegexp: RegExp = /[!#$%^()_=\[\]{}':"\\|.<>\/?]/;
        if (control.value && nameRegexp.test(control.value)) {
           return { noteValidator: true };
        }
        return { noteValidator: false };
    }
}