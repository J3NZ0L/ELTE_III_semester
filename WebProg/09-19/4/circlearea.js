const btn = document.querySelector("#btn")
const num = document.querySelector("#num")
const output = document.querySelector("output")
btn.addEventListener("click", circleArea)
function circleArea(){
    const r = num.value
    output.innerHTML = `Circle area: ${r * r * Math.PI}`
}