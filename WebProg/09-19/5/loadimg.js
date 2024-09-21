const text = document.querySelector("#text")
const button = document.querySelector("#btn")
const img = document.querySelector("img")

button.addEventListener("click", loadImg)

function loadImg(){
    img.src = text.value
}