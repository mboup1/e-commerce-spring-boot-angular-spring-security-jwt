import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'replace'
})
export class ReplacePipe implements PipeTransform {
  transform(value: number, find: number, replace: number): number {
    // Convertir la valeur en chaîne de caractères pour effectuer le remplacement
    let valueAsString = value.toString();
    let findAsString = find.toString();
    let replaceAsString = replace.toString();

    // Effectuer le remplacement dans la chaîne de caractères
    let replacedValueAsString = valueAsString.replace(findAsString, replaceAsString);

    // Convertir la chaîne de caractères résultante en nombre
    let replacedValue = parseFloat(replacedValueAsString);

    return replacedValue;
  }
}
