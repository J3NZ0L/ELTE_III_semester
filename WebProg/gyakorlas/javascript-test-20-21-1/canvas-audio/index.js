const canvas = document.querySelector("canvas")
const ctx = canvas.getContext("2d")
function randomNumber(lower, upper){
    return Math.floor(Math.random()*(upper-lower+1))+lower
}
arr = []

for(i=0; i<20; i++){
arr[i] = randomNumber(-5, 5)
}

console.log(arr)