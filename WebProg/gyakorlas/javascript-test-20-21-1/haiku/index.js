const textArea = document.querySelector("#haiku-editor")

const numberOfCharsElement = document.querySelector("#number-of-characters")

const numberOfRowsElement = document.querySelector("#number-of-rows")

const numberOfVowelsListElement = document.querySelector("#vowels-per-row")

const haikus = document.querySelector("#haikus")

const button = document.querySelector("#btn-copy-haiku")
//textArea.addEventListener("input", logHaikuInputToConsole)

textArea.addEventListener("input", onInput)

button.addEventListener("click", addHaiku)

function onInput(e){
    logHaikuInputToConsole(e)
    monitorNumberOfRows(e)
    logNumberOfVowels(e)
    const novdict = monitorNumberOfVowelsPerRow(e)
    textArea.closest('p').classList.toggle("haiku", novdict.length == 3 && novdict[0] == 5 && novdict[1] == 7 && novdict[2] == 5 )
    console.log(novdict.length == 3)
}

function addHaiku(e){
    const pre = document.createElement("pre")
    pre.innerText = textArea.value
    haikus.appendChild(pre)
}
    

const vowels = "aáeéiíoóöőuúüű"
const vowelsRegexp = new RegExp(`[${vowels}]`, 'g')

function logHaikuInputToConsole(e){
    console.log(e.target.value)
}

function monitorNumberOfChars(e){
    const len = textArea.value.length
    numberOfCharsElement.textContent = len
}

function monitorNumberOfRows(e){
    const iter = textArea.value.matchAll(/\S+\n/g)
    const len = Array.from(iter).length
    numberOfRowsElement.textContent = len+1
}

function logNumberOfVowels(e){
    const text = textArea.value
    const firstLineEndIndex = text.indexOf("\n")
    const row = text.substring(0, firstLineEndIndex>0 ? firstLineEndIndex : text.length)
    const num = calculateNumberOfVowels(row)
    console.log("Number of vowels in the first row: ",num)
}

function calculateNumberOfVowels(str){
    iter = str.matchAll(vowelsRegexp)
    return Array.from(iter).length
}

function monitorNumberOfVowelsPerRow(e){
    const text = textArea.value
    const rowArray = text.split("\n")
    const novdict = []
    let rowindex = 0
    numberOfVowelsListElement.innerHTML = ""
    rowArray.forEach(row => {
        novdict[rowindex] = calculateNumberOfVowels(row)
        const li = document.createElement("li")
        li.innerText = novdict[rowindex]
        numberOfVowelsListElement.appendChild(li)
        rowindex++
    });
    return novdict
}