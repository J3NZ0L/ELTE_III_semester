function hello() {
  console.log('Hello');
}
window.hello()

// Unintentional global variable
function sideEffect() {
    let aVeryBigVariable
    aVeryBigvariable = 12; // window.aVeryBigvariable
}
sideEffect()
console.log(aVeryBigvariable);
console.log(window.aVeryBigvariable);

// strict mode
function sideEffectStrict() {
    "use strict"
    let anotherVeryBigVariable
    anotherVeryBigVariable = 12;
}
sideEffectStrict()
console.log(anotherVeryBigVariable);
