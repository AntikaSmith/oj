object Main extends App{
	// def palindrome(num: String):(String, Boolean) = {
	// 	if (num.distinct == "9") {
	// 		val zeroArray = Array.fill(num.size +1)(0)
	// 		zeroArray(0) = 1
	// 		zeroArray(num.size) = 1
	// 		(zeroArray.mkString, false)
	// 	}
	// 	else {
	// 		val numChars = num.toString.toArray
	// 		numChars.size match {
	// 			case 1 => 
	// 				((num.toInt + 1).toString, true)
	// 			case 2 => 
	// 				val tenDecimal = numChars(0) - '0'
	// 				val unitDecimal = numChars(1) - '0'
	// 				if (tenDecimal <= unitDecimal) (((tenDecimal + 1) * 11).toString, true)
	// 				else ((tenDecimal * 11).toString, true)
	// 			case _ =>
	// 				val highestDecimal = numChars(0) - '0'
	// 				val lowestDecimal = numChars(numChars.size - 1) - '0'
	// 				val mid = numChars.slice(1, numChars.size - 1).mkString
	// 				val (palinMid, flagMid) = palindrome(mid)
	// 				if (flagMid) ((highestDecimal.toString + palinMid + highestDecimal), true)
	// 					else if (highestDecimal > lowestDecimal) ((highestDecimal.toString + mid + highestDecimal), true)
	// 					else (((highestDecimal + 1).toString + mid + (highestDecimal+1).toString), true)
	// 		}
	// 	}
	// }

	def palindrome(num: String) = {
		if (num.distinct == "9"){
			val zeroArray = Array.fill(num.size +1)(0)
			zeroArray(0) = 1
			zeroArray(num.size) = 1
			zeroArray.mkString
		}
		else {
			val arr = num.toArray
			val size = num.size
			var anchor = (size + 1) / 2 - 1
			while(arr(anchor) == '9'){
				anchor -= 1
			}
			arr(anchor) = (arr(anchor) + 1).toChar
			arr(size - anchor - 1) = arr(anchor)
			for(j <- anchor + 1 until size - anchor - 1)
				arr(j) = '0' 
			for(k <- size -anchor to size - 1)
				arr(k) = arr(size - k - 1)
			arr.mkString
		}
	}
	val num = io.StdIn.readInt
	for(i <- 1 to num){
		val integer = io.StdIn.readLine
		println(palindrome(integer))
	}
}