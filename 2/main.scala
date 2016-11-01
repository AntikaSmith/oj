object Main extends App{
	// http://www.spoj.com/problems/ONP/
	def transform(s: String) = {
		val sb = new StringBuilder
		val opStack = new collection.mutable.Stack[Char]()
		val bracketStack = new collection.mutable.Stack[Char]()
		val arr = s.toArray
		var bracketCount = 0

		for (i <- 0 until arr.size){
			arr(i) match {
				case '(' =>
					if(i >= 1 && arr(i-1) == '(')
						bracketStack.push(' ')
				case ')' => 
					bracketStack.headOption.foreach(op => sb.append(bracketStack.pop))
				case '+'|'-'|'*'|'/'|'^' => 
					if (i + 1 < arr.size && arr(i+1) == '(')
						bracketStack.push(arr(i))
					else
						opStack.push(arr(i))
					()
				case _ =>
					sb.append(arr(i))
					opStack.lastOption.foreach(char => sb.append(opStack.pop))
			}
		}
		sb.toString.replace(" ", "")
	}

	lazy val nums = io.StdIn.readInt
	for (i <- 1 to nums){
		val postformExpr = transform(io.StdIn.readLine)
		println(postformExpr)
	}
}
