//question at http://www.spoj.com/problems/PRIME1/
object Main extends App{
	// your code goes here
	import scala.io.Source

	def listBasicPrimes(i: Int) = (2 to i).filter(integer => (2 to math.sqrt(integer).toInt).forall(factor => integer %factor != 0))
	val basicPrimes = listBasicPrimes(100000).toArray
	val basicPrimesSet = basicPrimes.toSet
	def listPrimes(m:Int, n:Int) = {
		if (n < 2) ()
		else {
			if(m <= 2) println(2)
			var start = if (m <= 2) 3 
				else if (m % 2 == 0) m + 1 
				else m
			while (start <= n){
				var isPrimeYet = true
				val upper = math.sqrt(start).toInt
				var i = 0
				while(isPrimeYet && basicPrimes(i) <= upper){
					isPrimeYet = start % basicPrimes(i) != 0
					i += 1
				}
				if (isPrimeYet)
					println(start)
				start += 2
			}
		}
	}

	def listPrimesSlow(m: Int, n: Int) = {//written quickly but run slowly
		(m to n).filter{integer =>
			integer != 1 && (basicPrimesSet.contains(integer) || basicPrimes.forall(prime => integer % prime != 0) )
		}.foreach(println)
	}

	lazy val nums = io.StdIn.readInt
	for (i <- 1 to nums){
		val Array(m, n) = io.StdIn.readLine.split(" ").map(_.toInt)
		listPrimesSlow(m, n)
	}
	
}
