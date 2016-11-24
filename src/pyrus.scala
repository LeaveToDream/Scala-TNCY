object pyrus extends App {
	def printArray(t:Array[Int]){
		println();
		print("|");
		for ( x <- t ) {
			print(x.toString+"|");
		}
		println("");
	}
	def copy(src:Array[Int]):Array[Int]= {
			var dst = Array.fill(src.length)(0)	;
			var i = 0 ;
			for (elem <- src){
				dst(i) = elem;
				i += 1 ;
			}
			return dst ;
	}
	def copyAndPrint(src:Array[Int]):Array[Int]= {
			printArray(src);
			return copy(src) ;
	}
	var taille = 4
	var permutation : List[Array[Int]] = List() ;
	def genere(rg:Int,tab:Array[Int]) {
		if(rg >= tab.length){ 
			permutation = copyAndPrint(tab)::permutation ;
		} else {
			for (i <- 1 to tab.length){
				if (!(tab contains(i))){
					tab(rg)=i ;
					genere(rg+1, copy(tab)) ;
				}
			}
		}
	}
	genere(0, Array.fill(taille)(0))
}