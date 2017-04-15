package com.interview

import java.util.concurrent.atomic.AtomicReference

//retry if push or pop failed to make the stack to be thread-safe
class ConcurrentStack[T]{
    private[this] val data = new AtomicReference(List[T]())

    def size = data.get.size

    def push(elem: T) = {
        var current = data.get
        var newList = elem :: current
        while(!data.compareAndSet(current, newList)){
            current = data.get
            newList = elem :: current
        }
    }

    def pop = {
        var current = data.get
        var newList = data.get.tail
        var result = data.get.head
        while(!data.compareAndSet(current, newList)){
            current = data.get
            newList = data.get.tail
            result = data.get.head
        }
        result
    }
}

type ThreadNum = Int
def concurrentRun[T, U](threadsNum: Int, func: (ThreadNum, U) => T, param: U) = {
    val threads: Array[Thread] = Array.fill(threadsNum)(null)
    for (i <- 0 until threadsNum){
        threads(i) = new Thread(new Runnable(){
            override def run(){
                func.apply(i, param)
            }
            })
        threads(i).start
    }
}

def test() = {
    import java.util.concurrent.CountDownLatch

    //concrrent push test
    val stack = new ConcurrentStack[Int]
    val threadsNum = 10
    val runTimes = 1000
    val pushLatch = new CountDownLatch(threadsNum)

    val pushFunc: (ThreadNum, Int) => Unit  = {(_, y) =>
        for(x <- 1 to runTimes){
            stack.push(x)
        }
        pushLatch.countDown
    }
    concurrentRun(threadsNum, pushFunc, 0)
    pushLatch.await()
    assert(stack.size == threadsNum * runTimes)

    //concurrent pop test
    val bufferArray = Array.fill(threadsNum)(List[Int]())
    val popLatch = new CountDownLatch(threadsNum)
    val popFunc: (ThreadNum, Array[List[Int]]) => Unit = {(no, arr) =>
        for(_ <- 1 to runTimes)
            arr(no) = stack.pop :: arr(no)
        popLatch.countDown
    }
    concurrentRun(threadsNum, popFunc, bufferArray)
    popLatch.await()
    assert(stack.size == 0)
    assert(bufferArray.map(_.sum).sum == 5005000)

    println("all test is passed")
}
test
