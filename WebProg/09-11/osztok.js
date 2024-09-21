function aisdivisorofb(a, b){
    return b%a ===0;
}

const a = parseInt(prompt("Number A:"));
const b = parseInt(prompt("Number B:"));

console.log(`A is divisor of B: ${aisdivisorofb(a,b)}`)