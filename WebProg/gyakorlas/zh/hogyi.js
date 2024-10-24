let num = document.querySelector("#num")
const btn = document.querySelector("#btn")
let arr = []
const span = document.querySelector("span")
const ul = document.querySelector("ul")
let selectedNumbers = []



document.addEventListener("input", onInput)
btn.addEventListener("click", () => addNumToList(num.value), resetSelections())


disable()
function disable() {
    btn.disabled = (arr.length >= 5)
}


function onInput(e) {
    if (isNaN(e.target.value) || e.target.value.match(/\s/)) {
        e.target.value = e.target.value.slice(0, -1)
    }
}

function toggleSelection(li, num1) {
    if (li.classList.contains("selected")) {
        li.classList.remove("selected")
        li.style.color = ""
        selectedNumbers = selectedNumbers.filter(n => n !== num1)
    } else {
        li.classList.add("selected")
        li.style.color = "red"
        selectedNumbers.push(num1)
    }

    console.log(selectedNumbers)
    updateAverage()
}


function resetSelections() {
    const lis = ul.querySelectorAll("li")
    lis.forEach(li => {
        li.classList.remove("selected")
        li.style.color = ""
    });
    selectedNumbers = []
    updateAverage()
}

function addNumToList(num1) {
    
    let bigger = true;
    num1 = parseInt(num1)
    for (let i = 0; i < arr.length; i++) {
        if (num1 <= arr[i]) {
            bigger = false;
        }
    }
    if (!num1 || isNaN(num1)  || !bigger) {

        return
    }

    if (bigger && arr.length < 5) {
        arr.push(num1)

        const li = document.createElement("li")
        li.textContent = num1
        ul.appendChild(li)
        li.addEventListener("click", () => toggleSelection(li, num1))

        resetSelections()
        disable()
    }

    
    num.value = ""

}



function updateAverage() {
    if (selectedNumbers.length > 0) {
        let sum = 0
        for (let i = 0; i < selectedNumbers.length; i++) {
            sum += selectedNumbers[i]
        }
        let avg = sum / selectedNumbers.length
        span.textContent = avg.toFixed(2)
    } else {
        span.textContent = ""
    }
}