const sourceInput = document.querySelector("#src")
const destInputs = document.querySelectorAll("input.dest")
const button = document.querySelector("#button")

button.addEventListener("click", copySrcToDest)

function copySrcToDest(){
    for (const destInput of destInputs){
        destInput.value = sourceInput.value

    }
}