const hozzaadGomb = document.querySelector("#btn")

const beviteliMezo = document.querySelector("#num")

const lista = document.querySelector("ul")

hozzaadGomb.addEventListener("click", checkAndAddToList)
hozzaadGomb.addEventListener("click", checkIfShouldBeDisabled)

lista.addEventListener("click", selectListItem)

let style = document.createElement("style")
style.innerHTML = ".selected {color: red}"

document.getElementsByTagName("head")[0].appendChild(style)

function checkAndAddToList(_){
    const beviteliErtek = beviteliMezo.value 
    if (isNaN(beviteliErtek)){
        return
    }

    let listItemValues = Object.values(document.querySelectorAll("li"))

    let allSmaller = listItemValues.every(listItem => parseInt(listItem.innerText)<beviteliErtek)

    if (!allSmaller){
        return
    }

    const newli = document.createElement("li")
    newli.appendChild(document.createTextNode(beviteliErtek))
    lista.appendChild(newli)

    resetSelectedItems()
    refreshAvg()
}

function resetSelectedItems(){
    let listItems = document.querySelectorAll("li")
    listItems.forEach(element => {
        if (element.classList.contains("selected")){
            element.classList.remove("selected")
        }
    });
}

function checkIfShouldBeDisabled(_){
    let listItemValues = document.querySelectorAll("li")
    hozzaadGomb.disabled = listItemValues.length==5
}

function selectListItem(e){
    if (e.target.matches("li")){
        e.target.classList.add("selected")
    }
    refreshAvg()
}

function refreshAvg(){
    const selectedElements = document.querySelectorAll(".selected")
    const avgSpanElement = document.querySelector("span")
    let sum = 0
    if (selectedElements.length == sum){
        avgSpanElement.textContent = String(sum)
        return
    }
    selectedElements.forEach(element => {
        sum+=parseInt(element.innerText)
    });
    avgSpanElement.textContent = String(sum/selectedElements.length)
}
