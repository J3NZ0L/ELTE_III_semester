const array = [1, 2, 3, 5, 6]

function searchFirstNegative(l){
    i = 0
    while ( i< l.length && l[i]>0){
        i++
    } 
    if (i<l.length){
        return l[i]
    }
    else {
        return null
    }
}


console.log(searchFirstNegative(array))
console.log(l.find(a => a<0))