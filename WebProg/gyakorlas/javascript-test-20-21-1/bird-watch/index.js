const placesInput = document.querySelector('#places')
const speciesInput = document.querySelector('#species')
const button = document.querySelector('#btn-generate')
const tableContainer = document.querySelector('#table-container')
const task1 = document.querySelector('#task-1')
const task2 = document.querySelector('#task-2')
const task3 = document.querySelector('#task-3')
const task4 = document.querySelector('#task-4')
const task5 = document.querySelector('#task-5')

let matrix = []

const table = document.createElement("table")
tableContainer.appendChild(table)

delegate(tableContainer, "click", "tr td", handleTableMouseClick)

function handleTableMouseClick(event){
  event.preventDefault()
  logTableRowCol(this)
  manageFact()
}

function manageFact(){
  task1.textContent = matrix[0].some( num => num>=1) ? "(Yes)" : "(No)"
}

function logTableRowCol(td){
  const rowandcol = getRowAndColFromCell(td)
  row = rowandcol.row
  col = rowandcol.col
  matrix[row][col]+=1
  td.textContent = matrix[row][col]
  console.log(row+1, col+1)
}

function getRowAndColFromCell(td){
  const row = td.parentNode.rowIndex
  const col = Array.from(td.parentNode.children).indexOf(td)
  return {row, col}
}

function delegate(parent, type, selector, handler) {
  parent.addEventListener(type, function (event) {
    const targetElement = event.target.closest(selector);

    if (this.contains(targetElement)) {
      handler.call(targetElement, event);
    }
  });
}

button.addEventListener('click', onGenerate)
function onGenerate(e) {
  const n = placesInput.valueAsNumber
  const m = speciesInput.valueAsNumber

  matrix = generateMatrix(n, m)
  console.log(matrix);
  generateHTMLMatrix(matrix)
}

function generateMatrix(n, m) {
  const matrix = []
  for(let i = 0; i<n; i++) {
    const row = []
    for(let j = 0; j<m; j++) {
      row.push(0)
    }
    matrix.push(row)
  }
  return matrix
}

function generateHTMLMatrix(matrix){
  table.innerHTML = ""
  matrix.forEach(row => {
    const tableRow = document.createElement("tr")
    row.forEach( elem => {
      const text = document.createTextNode(elem)
      const cell = document.createElement("td")
      cell.appendChild(text)
      tableRow.appendChild(cell)
    })
    table.appendChild(tableRow)
  });
}
