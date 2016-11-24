object test extends App {
	var t = Array(5,4,3,2,7,13,6,5) ;
	print(!(t contains (5)));
	def printArray(t:Array[Int]){
		println()
		print("|")
		for ( x <- t ) {
			print(x.toString+"|")
		}
		println("")
	}
	printArray(t);
	printArray(Array.fill(4)(0))

}
//https://laravel.com/docs/5.3/eloquent
//Materialize
//Graphikart
