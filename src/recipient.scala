object recipient extends App {
	var vus:List[Array[Int]] = Nil;
	def printArray(t:Array[Int]){
		print("|")
		for ( x <- t ) {
			print(x.toString+"|")
		}
		println("")
	}
	def dupplique(src:Array[Int]):Array[Int]= {
	  var dst:Array[Int] = Array.fill(src.length)(0)
	  for (i <- 0 to src.length-1){
	    dst(i) = src(i)
	  }
	  return dst ;
	}
	def egaleT(src:Array[Int], dst:Array[Int]):Boolean = {
		for (i <- 0 to src.length-1) {
			if(dst(i) != src(i)){
			  return false
	      	}
	    }
		return true
	}
	def containsLT(list:List[Array[Int]],tab:Array[Int] ):Boolean={
	  for(i <- 0 to list.size -1 ){
	    if(egaleT(list(i),tab)){
	      return true
	    }
	  }
	  return false
	}
	def containsTI(tab:Array[Int], inteuh:Int):Boolean = {
	  for(i <- 0 to tab.size -1 ){
	    if(tab(i)==inteuh){
	      return true
	    }
	  }
	  return false
	}
	def printVu(){
		println("Vu : ");
		for(k <- 0 to vus.length -1){
		  printArray(vus(k));
		}
		println("/Vu");
		println("-------------------------------");
	}
	// ------------------------------------------------------------------------------------
	def solve(rg:Int, cap:Array[Int], ctn:Array[Int], cible:Int):Boolean= {
	  var found = false ;
	  if(containsTI(ctn,cible)){
	    printVu();
	    return true;
	    
	  }
	  if(rg == 0){
	    return false;
	  }
	  var len = ctn.length  ;
	  for(src <- 0 to len-2){
	    var tmp_src = ctn(src);
	    for(dst <- 1 to len-1){
	      // BT #1
	      var tmp_dst = ctn(dst);
	      // /BT #1
	      if (src!=dst) {
	      	if(!cas_nul(ctn,cap,src,dst)){ 
		      /*printArray(ctn);
		      println("Transevasement de "+src+ " à "+dst);
		      println("Rang : "+rg) ;
		      */
		      if(src==0)			{ remplir(dst, cap, ctn); }
		      else if(dst == len-1) { vider(src, ctn); }
		      else 					{ transvaser(src,dst,cap,ctn); }
		      //printArray(ctn);
		      
		      // Dyn 
		      if (!(containsLT(vus, ctn))){
			    vus = ctn.clone::vus
			    // Appel Rec
			    //println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			    var find = solve(rg-1, cap.clone, ctn.clone, cible);
			    //println("**********************************************");
			    found = find || found;
			    
			    // /Rec
			  }
		      else {
		        //println("Déjà vu");
		      }
	        // /Dyn
	        }
	      	else {
	      	  //println("Cas nul, src = "+src+" et dst = "+dst);
	      	}
	      }
	      // BT #2
	      ctn(dst) = tmp_dst;
	      // /BT #2
	    }
	    ctn(src) = tmp_src;
	  }
	  return found ;
	}
	def remplir(obj:Int, cap:Array[Int], ctn:Array[Int]){
	  ctn(obj) = cap(obj);
	}
	def vider(obj:Int, ctn:Array[Int]){
	  ctn(obj)=0;
	}
	def transvaser(src:Int, dst:Int, cap:Array[Int], ctn:Array[Int]){
	  var q = cap(dst) - ctn(dst); // La quantité à remplir
	  if(q >= ctn(src)){
	    ctn(dst)=ctn(dst)+ctn(src);
	    ctn(src)=0
	  }
	  else {
	    ctn(dst)=cap(dst);
	    ctn(src)=cap(src)-q;
	  }
	}
	def cas_nul(ctn:Array[Int], cap:Array[Int], src:Int, dst:Int):Boolean = {
		if(ctn(src)==0 && src != 0){
		  return true
		}
		else if (ctn(dst) == cap(dst) && dst != ctn.length -1){
		  return true
		}
		else {
		  return false
		}
	}
	println(solve(40, Array(0,5,3,0), Array(0,0,0,0), 4));
}