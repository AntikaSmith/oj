//http://www.spoj.com/problems/FCTRL/
object Main extends App{
//test cases
/*
6
3
60
100
1024
23456
8735373
answer:
0
14
24
253
5861
2183837
*/

    val fivePowers = (1 to 12).map(math.pow(5, _).toInt)

    def factorial(n: Int) = fivePowers.map(n / _).sum

    val num = io.StdIn.readInt
    for(i <- 1 to num){
        val integer = io.StdIn.readLine
        println(factorial(integer.toInt))
    }
}
