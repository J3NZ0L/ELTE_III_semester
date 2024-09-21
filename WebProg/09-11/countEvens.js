const array = [1, 2, 3, 4, 5, 6, 7]

console.log(`Number of evens in array: ${array.reduce((a,b)=> a+ (b%2 == 0 ? 1 : 0), 0)}`)