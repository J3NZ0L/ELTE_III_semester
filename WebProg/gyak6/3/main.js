import {setCookie, getCookie} from "../cookies.js"

const style = document.querySelector("#style")

document.querySelector("#small").addEventListener("click", () => setStylesheet("small"))
document.querySelector("#medium").addEventListener("click", () => setStylesheet("medium"))
document.querySelector("#large").addEventListener("click", () => setStylesheet("large"))

function setStylesheet(stylesheet){
    setCookie("stylesheet", stylesheet)
    style.setAttribute("href", stylesheet + ".css")
}

const stylesheet = getCookie("stylesheet")
if (stylesheet != ""){
    setStylesheet(stylesheet)
}
