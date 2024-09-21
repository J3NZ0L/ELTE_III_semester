function gcd(a,b){
    if (a<b){
        c=a
        a=b
        b=c
    }
    remainder = a %b
    while (remainder > 0){
        a = b
        b = remainder
        remainder = a % b
    }
    return b
}

const a = parseInt(prompt("A:"))
const b = parseInt(prompt("A:"))

alert(`The greatest common divisor of the provided numbers is ${gcd(a,b)}`)