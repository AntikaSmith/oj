 def find[T : Ordering](k: T, arr:Array[T]):Boolean = {
      val n = arr.size
      val comp = implicitly[Ordering[T]]
      if(arr.size == 0) false
      else if(comp.compare(k, arr(n/2)) == 0) true
      else if(comp.compare(k, arr(n/2)) > 0) find(k, arr.slice(0, n/2))
      else find(k, arr.slice(n/2, n))
}

def qSort(arr: Array[Nothing]) = {}
def qSort[T: Ordering](arr: Array[T]): Unit = {
    val comp = implicitly[Ordering[T]]

    def swap(i: Int, j: Int) = {
        val tmp = arr(i)
        arr(i) = arr(j)
        arr(j) = tmp
    }

    def partition(start: Int, end: Int): Int = {
        val pivot = arr(start)
        var pivotIndex = start
        var forward = start
        var backward = end
        while (forward < backward){
            if(comp.gteq(arr(forward), pivot)){
                while(comp.gteq(arr(backward), pivot) && backward > forward)
                    backward = backward - 1
                swap(forward, backward)
                if (forward == start)
                    pivotIndex = backward
            }
            forward += 1
        }
        swap(pivotIndex, backward)
        backward
    }
    def inner(start:Int, end: Int):Unit = {
        if (start == end) ()
        else{
            val pivotIndex = partition(start, end)
            if(pivotIndex < end)
                inner(pivotIndex + 1, end)
            if(pivotIndex > start)
                inner(start, pivotIndex - 1)
        }
    }
    inner(0, arr.size - 1)
}
