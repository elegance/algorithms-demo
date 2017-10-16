const SortHelper = require('./SortHelper');
const MergeSort1 = require('./MergeSort');
const MergeSort2 = require('./MergeSort2');

let arr1 = SortHelper.generateRandomArray(1000000, 0, 100);
let arr2 = Array.from(arr1);

let startTime = new Date().getTime();


// 归并排序：分两个数组的理解初版
startTime = new Date().getTime();
MergeSort1.sort(arr1);
console.log(SortHelper.isSorted(arr1));
console.log('Elapsed time:' + (new Date().getTime() - startTime));

// 归并排序：用一个aux辅助数组 结合mid 来代替两个数组
startTime = new Date().getTime();
MergeSort2.sort(arr2);
console.log(SortHelper.isSorted(arr2));
console.log('Elapsed time:' + (new Date().getTime() - startTime));