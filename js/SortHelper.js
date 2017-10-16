
exports.generateRandomArray = function(len, low, upper) {
    let arr = new Array(len);
    let choices = upper - low + 1;

    for (var i = 0; i < arr.length; i++) {
        arr[i] = Math.floor(Math.random() * choices) + low;       
    }
    return arr;
}

exports.isSorted = function(arr) {
    for (var index = 1; index < arr.length; index++) {
        if (arr[index] < arr[index-1]) {
            return false;
        }
    }
    return true;
}