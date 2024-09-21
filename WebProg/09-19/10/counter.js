
const substractButton = document.querySelector("#substract")
const addButton = document.querySelector("#add")
const numElement = document.querySelector("#num")

let num = numElement.value

addButton.addEventListener("click", addToNum)
substractButton.addEventListener("click",substractFromNum)

const min = 0
const max = 500

const delay = 800 //800 ms
const rate = 100
const delay2 = 3000
const rate2 = 30

let delayTimer
let rateTimer



function addToNum(){
    numElement.value = ++num
    updateDisable()
}
function substractFromNum(){
    numElement.value = --num
    updateDisable()
}
updateDisable()
function updateDisable(){
    addButton.disabled = (num ==max)
    substractButton.disabled = (num == min)
    if ((num ==min ) || (num == max)){
        killTimers()
    }
}

addButton.addEventListener("mousedown", () => {
    delayTimer = setTimeout(() => {
        rateTimer = setInterval(addToNum, rate)
    }, delay)
})

addButton.addEventListener("mouseup", killTimers)

substractButton.addEventListener("mousedown", () => {
    delayTimer = setTimeout(() => {
        rateTimer = setInterval(addToNum, rate)
    }, delay)
})

substractButton.addEventListener("mouseup", killTimers)

function killTimers(){
    clearTimeout(delayTimer)
    clearTimeout(rateTimer)
}