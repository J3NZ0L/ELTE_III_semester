const numInput = document.querySelector("#num")
const button = document.querySelector("#btn")
const tbody = document.querySelector("tbody")

button.addEventListener("click", degenerateTable)

function degenerateTable(){
    const n = numInput.value
    let row = tbody.insertRow()
    row.insertCell().textContent = 0
    for(let i=1; i<=n; i++){
        row.insertCell().textContent = i
    }
    for(let i=1; i<=n; i++){
        row = tbody.insertRow()
        row.insertCell().textContent = i
        for (let j = 1; j<=n; j++){
            row.insertCell().textContent = i * j
        }
    }
}