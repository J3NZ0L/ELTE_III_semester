const task1 = document.querySelector("#task1");
const task2 = document.querySelector("#task2");
const task3 = document.querySelector("#task3");
const task4 = document.querySelector("#task4");
const task5 = document.querySelector("#task5");

// data
console.log(resorts);
task_1();
var task1_text = document.createElement("p");
task1_text.textContent = task_1();
console.log(task_1());
task1.appendChild(task1_text);
// Tasks
// Adj meg egy várost, amely Svájcban vagy Franciaországban van, és a sípályáinak hossza több, mint 200km!
// Find a city in Switzerland or France with more than 200km of ski slopes!

function task_1(){
    var answer = resorts.filter(x => (x.country == "France" || x.country == "Switzerland") && x.skiSlopeLength >= 200).map(x => x.city)[0];
    return answer;
}

// Mely településeknek van 2000m alatti csúcsa? 
// Which city has a summit below 2000m?

function task_2(){
    console.log(resorts.filter(x => x.highestPoint < 2000).map(x => x.city));
    return resorts.filter(x => x.highestPoint < 2000).map(x => x.city);
}

task_2().forEach(element => {
    var task2_text = document.createElement("p");
    task2_text.textContent = element;
    task2.appendChild(task2_text);
});

// Melyik településnek van a leghosszabb sípálya-rendszere? 
// Which city has the longest ski slope system?

function task_3(){
    var max = Math.max(...resorts.map(x => x.skiSlopeLength));
    console.log(max);
    return resorts.find(x => x.skiSlopeLength === max).city;
}

var task3_text = document.createElement("p");
task3_text.textContent = task_3();
task3.appendChild(task3_text);

// Igaz, hogy mindegyik városban van legalább 40km hosszú pálya?
// Is it true that each city has at least 40km of tracks?

function task_4(){
    var length = resorts.length;
    return length == resorts.filter(x => x.skiSlopeLength >= 40).length
}

var task4_text = document.createElement("p");
if(task_4()){
task4_text.textContent = "Igaz";

} else {
task4_text.textContent = "Hamis";
}
task4.appendChild(task4_text);

// Add meg, minden országra, hogy hány város képviselteti magát a listában!
// Calculate, for each country, how many cities are represented in the list!

var country = [];
var countryCity = [];

function task_5(){
    var count = 0;
    //console.log(country.find(x => x.country == "France"))
    resorts.forEach(element => {
        if(country.find(x => x == element.country) == undefined){
            country[count] = element.country;
            count++;
        }
    });
    var ccount = 0;
    country.forEach(element => {
        resorts.forEach(item => {
            if(item.country == element){
                if(countryCity[ccount] == undefined){
                    countryCity[ccount] = 1;
                } else {
                    countryCity[ccount] += 1;
                }
            }
        })
        ccount++;
    })
}

task_5();

var counter = 0;
country.forEach(element => {
    var task5_text = document.createElement("p");
    task5_text.textContent = element + ": " + countryCity[counter]; 
    task5.appendChild(task5_text);
    counter++;
});

