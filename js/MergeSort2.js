/**
 * 将数组 [l-r] 拆分左右两断
 * @param {Array<Number>} arr 
 */
function reduce(arr, l, r) { 
    if (l >= r) {
        return;
    }
    let mid = Math.floor((r + l) / 2); // js “/”会有 小数 余数

    reduce(arr, l, mid);
    reduce(arr, mid+1, r);
    merge(arr, l, mid, r);
}

/**
 * 将：[l-mid] 与 (mid-r] 两断分别有序数组merge到arr响应位置
 */
function merge(arr, l, mid, r) {
    // console.log('=========================▼▼============================');
    // console.log(`l: ${l}, mid: ${mid}, r: ${r}`);
    let aux = new Array(r-l+1); // 开辟辅助空间，长度为： 终点索引减偏移量 + 1

    // 辅助空间赋值: 根据偏移量 l 去 原始 arr 中取值
    for (let i = 0; i < aux.length; i++) {
        aux[i] = arr[l+i];
    }
    // console.log(`aux: ${aux}`);

    // 辅助空间建立完毕，即 aux的 [0, mid-l] 为左端有序数组，[mid-l+1, r-l] 为右端有序数组
    // 开始 使用 left,right 来带入 arr [l, r] 间的值，现在可以理解为：arr 数组 [l, r] 为空白
    let i = 0, j = 0, k = 0;

    do{
        if (j >= mid-l+1) { // j >=  left.length，取 右端数组的值
            arr[l+i] = aux[mid-l+k+1];
            // console.log('debug 1', arra);
            k++;
        } else if(k >= r-mid) { // k > = right.length 取左端数组值
            arr[l+i] = aux[j];
            // console.log('debug 2', arr);
            j++;
        } else if (aux[j] < aux[mid-l+k+1]) {
            arr[l+i] = aux[j];
            // console.log('debug 3', arr);
            j++;
        } else {
            arr[l+i] = aux[mid-l+k+1];
            // console.log('debug 4', arr);
            k++;
        }
        i++;
    }while(i < aux.length);
    // console.log(arr)
    // console.log('==================merge over ▲▲===========================');
}

function sort(arr) {
    reduce(arr, 0, arr.length - 1);
}

// let arr = [3, 2, 1];
// sort(arr);
// console.log(arr);

exports.sort = sort;