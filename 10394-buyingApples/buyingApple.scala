object Main extends App {
	def minCost(n: Int, k:Int , price: Array[Int]) = {
		val cache = Array.fill(n + 1, k + 1)(0)
		for (i <- 0 until k){
			if (price(i) == -1)
				cache(1)(i + 1) = 100 * 1000 + 1
			else 
				cache(1)(i + 1) = price(i)
		}

		def minimum(i: Int, j: Int) = {
			(0 to j ).map{l =>
				cache(i - 1)(j - l) + cache(1)(l)
			}.min
		}

		for (i <- 2 to n) {
			for (j <- 1 to k ){
				cache(i)(j) = minimum(i, j)
			}
		}

		if (cache(n)(k) >= 100 * 1000 + 1)
			-1
		else
			cache(n)(k)
	}

	val num = io.StdIn.readInt
	for (i <- 1 to num) {
		val Array(n, k) = io.StdIn.readLine.split(" ").map(_.toInt)
		val price = io.StdIn.readLine.split(" ").map(_.toInt)
		println(minCost(n, k, price))
	}
}