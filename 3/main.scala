object Main extends App{
//test cases
/*
1
9
23
899
81727
82719
*/
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

			var tmp = anchor//中心向左的第一个下表为k且不等于n-k位置的元素决定了是否可以直接使用前半段对称复制来作为答案
			while(tmp >= 0 && arr(tmp) == arr(size - tmp - 1)){
				tmp -= 1
			}
			if (tmp >= 0 && arr(tmp) > arr(size - tmp - 1)){
				for (i <- anchor + 1 to size -1)
					arr(i) = arr(size - i - 1)
				arr.mkString
			}
			else {
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
	}
	val num = io.StdIn.readInt
	for(i <- 1 to num){
		val integer = io.StdIn.readLine
		println(palindrome(integer))
	}
}