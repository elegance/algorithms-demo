const SortHelper = require('./SortHelper');

function reduce(arr, l, r) {
    if (l >= r) { // return recursive when this area has just 1 element.
        // console.log('return');
        return;
    }
    var mid = Math.floor((r + l) / 2); // mid 使用 (左 + 右) /2 则意味着得到的mid，是索引量
    // 则 leftArr:  [l, mid -1]
    //    rightArr: [mid, r]

    // 如果 mid 使用 (右 - 左) /2 ，则意味着mid 是 相对于 索引 "左" 的偏移量
    // 则 leftArr:  [l, l + mid -1]
    //    rightArr: [l + mid, r]

    reduce(arr, l, mid); // left
    reduce(arr, mid + 1, r) // right
    merge(arr, l, mid, r);
}

function merge(arr, l, mid, r) {
    var aux = new Array(r - l + 1); // init a array to store l-mind, mind+1 - r

    for (var i = 0; i < aux.length; i++) {
        aux[i] = arr[l + i];
    }
    // console.log(`aux: [${aux}], mid: ${mid}, l: ${l}, r: ${r}`);

    var left = [];
    for (var i = 0; i < mid-l+1; i++) {
        left.push(aux[i]);
    }

    var right = [];
    for (var i = 0; i < r-mid; i++) {
        right.push(aux[mid-l+i+1]);
    }
    // console.log(`left: [${left}], right: [${right}]`);

    var i = 0, j = 0, k = 0;
    let tmp;
    do {
        if (j >= left.length) {
            arr[i+l] = right[k];
            k++;
        } else if (k >= right.length) {
            arr[i+l] = left[j];
            j++;
        } else if (left[j] < right[k]) {
            arr[i+l] = left[j];
            j++;
        } else {
            arr[i+l] = right[k];
            k++;
        }
        i++;
    } while (i < aux.length);
}

// console.log(`
// ==========================================
// ==========================================
// ==========================================`);
//var arr = [5, 4, 3, 2, 1];
//sort(arr, 0, arr.length - 1);
//console.log(arr);
//
//var arr2 = SortHelper.generateRandomArray(1000000, 0, 100);
//let startTime = new Date().getTime();
//sort(arr2, 0, arr2.length -1);
//console.log(SortHelper.isSorted(arr2));
//console.log('Elapsed time:' + (new Date().getTime() - startTime));

function sort(arr) {
    reduce(arr, 0, arr.length -1);
}
exports.sort = sort;