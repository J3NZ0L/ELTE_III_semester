const movies = [
    {
        "title": "Inception",
        "year": 2010,
        "duration": 148,
        "director": "Christopher Nolan"
    },
    {
        "title": "The Matrix",
        "year": 1999,
        "duration": 136,
        "director": "The Wachowskis"
    },
    {
        "title": "Interstellar",
        "year": 2014,
        "duration": 169,
        "director": "Christopher Nolan"
    },
    {
        "title": "Pulp Fiction",
        "year": 1994,
        "duration": 154,
        "director": "Quentin Tarantino"
    },
    {
        "title": "The Godfather",
        "year": 1972,
        "duration": 175,
        "director": "Francis Ford Coppola"
    },
    {
        "title": "The Dark Knight",
        "year": 2008,
        "duration": 152,
        "director": "Christopher Nolan"
    },
    {
        "title": "Fight Club",
        "year": 1999,
        "duration": 139,
        "director": "David Fincher"
    },
    {
        "title": "Forrest Gump",
        "year": 1994,
        "duration": 142,
        "director": "Robert Zemeckis"
    },
    {
        "title": "The Lord of the Rings: The Fellowship of the Ring",
        "year": 2001,
        "duration": 178,
        "director": "Peter Jackson"
    },
    {
        "title": "The Lord of the Rings: The Two Towers",
        "year": 2002,
        "duration": 179,
        "director": "Peter Jackson"
    },
    {
        "title": "The Lord of the Rings: The Return of the King",
        "year": 2003,
        "duration": 201,
        "director": "Peter Jackson"
    },
    {
        "title": "The Shawshank Redemption",
        "year": 1994,
        "duration": 142,
        "director": "Frank Darabont"
    },
    {
        "title": "The Social Network",
        "year": 2010,
        "duration": 120,
        "director": "David Fincher"
    },
    {
        "title": "Gladiator",
        "year": 2000,
        "duration": 155,
        "director": "Ridley Scott"
    },
    {
        "title": "The Wolf of Wall Street",
        "year": 2013,
        "duration": 180,
        "director": "Martin Scorsese"
    },
    {
        "title": "Schindler's List",
        "year": 1993,
        "duration": 195,
        "director": "Steven Spielberg"
    },
    {
        "title": "Titanic",
        "year": 1997,
        "duration": 195,
        "director": "James Cameron"
    },
    {
        "title": "Avatar",
        "year": 2009,
        "duration": 162,
        "director": "James Cameron"
    },
    {
        "title": "Braveheart",
        "year": 1995,
        "duration": 178,
        "director": "Mel Gibson"
    },
    {
        "title": "The Grand Budapest Hotel",
        "year": 2014,
        "duration": 99,
        "director": "Wes Anderson"
    }
]

const tbody = document.querySelector("tbody")
document.addEventListener("DOMContentLoaded", createHeaderForMoviesTable)
document.addEventListener("DOMContentLoaded", updatetBody)

function createHeaderForMoviesTable(e){
    header = tbody.insertRow(0)
    for (let key in movies[0]){
        let newCell = document.createElement("th")
        newCell.textContent = key
        header.appendChild(newCell)
    }
}

const searchBar = document.getElementById("search")
const searchBtn = document.getElementById("searchBtn")

searchBtn.addEventListener("click", updatetBody)

function updatetBody(e){
    filterString = searchBar.value
    filterStringIsEmpty = filterString === ""
    if (!filterStringIsEmpty){
        tbody.innerHTML = ""
        createHeaderForMoviesTable()
    }
    if (tbody.rows.length != 1 && filterStringIsEmpty){
        return
    }
    for (let movieIndex in movies){
        if (filterString === "" || movies[movieIndex].title.toLowerCase().includes(filterString.toLowerCase())) {
            newRow = tbody.insertRow(-1)
            for (let propertyKey in movies[movieIndex]){
                let newCell = document.createElement("td")
                let newText = document.createTextNode(movies[movieIndex][propertyKey])
                newCell.appendChild(newText)
                newRow.appendChild(newCell)
            }
        }
    }
}




