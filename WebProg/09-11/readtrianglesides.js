const a = parseInt(prompt("side a:"));
const b = parseInt(prompt("side b:"));
const c = parseInt(prompt("side c:"));

if (a+b>c && a+c>b && b+c>a){
    console.log("The sides can make up a valid triangle");
}
else{
    console.log("The lengths you provided as the sides of a triangle are invalid!")
}