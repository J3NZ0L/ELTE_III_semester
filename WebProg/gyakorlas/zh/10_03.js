const hozzaadGomb = document.querySelector("#btn")

const beviteliMezo = document.querySelector("#num")

const lista = document.querySelector("ul")

hozzaadGomb.addEventListener("click", checkAndAddToList)

hozzaadGomb.addEventListener("click", checkListCount)

function checkAndAddToList(e){
    let ertek = beviteliMezo.value
    if (NaN(ertek)){
        return
    }
    let listItemValues = Object.values(document.querySelectorAll("li"))

    let allSmaller = listItemValues.every( value => value<ertek)

    if (!allSmaller){
        return
    }

    const newli = document.createElement("li")
    newli.appendChild(document.createTextNode(ertek))
    lista.appendChild(newli)
}

function checkListCount(e){
    let listItemValues = Object.values(document.querySelectorAll("li"))
    hozzaadGomb.disabled = listItemValues.length=5
}



