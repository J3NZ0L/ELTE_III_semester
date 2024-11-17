const adds = document.querySelectorAll("#add")

adds[0].addEventListener("click", () => add(0))
adds[1].addEventListener("click", () => add(1))

const table = document.querySelector("tbody")
const uls = document.querySelectorAll("ul")

const texts = document.querySelectorAll("#text")

const langs =["en","hu"]

async function translate(text, x){
    const options = {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            'X-RapidAPI-Key': '30de154409mshf010179c8c90513p159ed0jsna60ac1d68892',
            'X-RapidAPI-Host': 'microsoft-translator-text-api3.p.rapidapi.com'
        },
        body: `[{"Text":"${text}"}]`
    };

    const response = await fetch(`https://microsoft-translator-text-api3.p.rapidapi.com/translate?to=${langs[x]}&from=${langs[1 - x]}&api-version=3.0&textType=plain`, options)

    const json = await response.json()
    console.log(json)
    console.log(json[0])
    console.log(json[0].translations[0].text)
    return json[0].translations[0].text
}

async function add(x){
    if (texts[x].value.trim() != ""){
        addLi(uls[x], texts[x].value)
        addLi(uls[1-x], await translate(texts[x].value, x))
    }

    texts[x].value == ""
}

function addLi(ul, li){
    ul.appendChild(document.createElement("li")).innerText = li
}