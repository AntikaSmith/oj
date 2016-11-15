object Main extends App {
	def alphacode(s: String):Long = {
		val size = s.size
		if (size <= 2) {
			if (size == 1) 1l
			else {
				val num = s.toInt
				if (num < 10) 0
				else if (num == 10 || num == 20) 1
				else if (num <= 26) 2
				else if (num % 10 == 0) 0
				else 1
			}
		}
		else {
			val arr = s.toArray
			val result = Array.fill(size)(0l)
			if (arr(0) == '0')
				result(0) = 0l
			else {
				result(0) = 0l
				result(1) = alphacode(arr.slice(0, 2).mkString)
			}//initialization

			var i = 2
			while(i < size) {
				arr(i - 1) match {
					case '0' | '3' | '4' | '5' | '6' | '7' | '8' | '9' =>
						if (arr(i) == '0')
							result(i) = 0
						else
							result(i) = result(i - 1)
					case '1' =>
						if (arr(i) == '0') 
							result(i) = result(i - 2)
						else
							result(i) = result(i - 1) + result(i - 2)
					case '2' =>
						arr(i) match {
							case '0' =>
								result(i) = result(i - 2)
							case '7' | '8' | '9' =>
								result(i) = result(i - 1)
							case _ =>
								result(i) = result(i - 1) + result(i - 2)
						}
				}
				i += 1
			}
			result(i - 1)
		}
	}

	var str = io.StdIn.readLine
	while (str != "0"){
		println(alphacode(str))
		str = io.StdIn.readLine
	}

}