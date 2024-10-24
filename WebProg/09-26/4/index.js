document.addEventListener("input", onInput)

function onInput(e){
    if (e.target.matches(".szam")){
        if (e.target.value.match(/\s/) || isNaN(e.target.value)){
            //\s a spaceket talalja meg
            e.target.value = e.target.value.slice(0,-1)
            //ez esetben az eddigi marad az ertek, -1 a veget vagja le
        }
    }
}