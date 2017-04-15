1. 快速排序算法

def quickSort(arr: Array[Int]): Unit = {
    def partition(start: Int, end: Int): Int = {// 返回最后的pivot所在的下标
        var (i, j) = (start, end)
        var pivotIndex = start
        var pivot = arr(pivotIndex)
        while(i < j) {
            while(arr(j) >= pivot && j > i){
                j -= 1
            }
            if( i < j && arr(j) < arr(pivotIndex))
                arr(i) = arr(j)
            while(arr(i) < pivot && j > i){
                i += 1
            }
            if(i < j && arr(i) >= arr(pivotIndex))
                arr(j) = arr(i)
        }
        arr(i) = pivot
        i
    }
    
    def inner(start:Int, end: Int):Unit = {
        if(start >= end)()
        else{
            val pivotIndex = partition(start, end)
            inner(start, pivotIndex - 1)
            if (pivotIndex + 1 < end)
                inner(pivotIndex + 1, end)
            }
    }
    
    inner(0, arr.size - 1)

}

2. 实现一个栈
class StackEmptyException extends Exception
class Stack{
    private val maxSize = 1000
    
    private val elems = Array.fill(maxSize)(0)
    
    private var size = 0
    
    //返回放入元素是否成功
    def push(elem: Int): Boolean = {
        if(size >= maxSize){
            false
        }
        else{
            elems(size) = elem
            size += 1
            true
        }
    }
    
    //取元素如果栈为空则抛出异常
    def pop: Int = {
        if(size <= 0){
            throw new StackEmptyException
        }
        size -= 1
        val tmp = elems(size)
        elems(size) = 0
        tmp
    }
}

3. 给定一个矩阵matrix[n][m]，每一行从左到右递增，每一列从上到下递增；现在给定一个数字x，在matrix中搜索x的位置，有则打印坐标。

