/** Probleme du sac a dos */

object knapsack extends App { 

	// Diverses fonctions d'aide
	////////////////////////////
	
	/** Calcule le gain d'un sac a dos donne' */
	def valeurTotale(objets:Array[Boolean], poids:Array[Int]):Int = {
	  var total = 0
	  for (i <- 0 to objets.length -1)
	    if (objets(i)) 
	      total += poids(i)
	  return total
	}
	
	/** Affiche si les objets sont pris ou non dans une solution partielle (en cours de construction)
	 *
	 * Le second argument est la taille deja construite.
	 */
	def affiche(objets:Array[Boolean], objetMax:Int) {
		      
	  // on utilise un min pour se proteger du cas (frequent) ou cette
	  // fonction est appellee avec un second argument superieur a la
	  // taille du tableau.
	  for (i <- 0 to math.min(objetMax, objets.length-1))
	    if (objets(i))
	      print(" O ")
	    else
	      print(" N ")
	  if (objetMax < objets.length-1)
	    for (i <- objetMax to objets.length-2)
	      print("...")
	  print(";")
	}
	
	
	/** Dupplique un sac dans un autre, pour se souvenir de la meilleure solution connue */
	def dupplique(src:Array[Boolean], dst:Array[Boolean]) {
	  for (i <- 0 to src.length-1) 
	    dst(i) = src(i)
	}
	
	/** indique dans le parametre que l'objet specifie est maintenant pris
	    (cette fonction est juste la pour se simplifier la vie) */
	def mettreDansSac(objets:Array[Boolean], obj:Int) {
	    if (objets(obj)) 
	        println("L'objet "+obj+" est deja pris; ignore la requete.");
	    objets(obj) = true
	}
	/** indique dans le parametre que l'objet specifie est maintenant pose' */
	def retireDuSac(objets:Array[Boolean], obj:Int) {
	    if (!objets(obj)) 
	        println("L'objet "+obj+" est deja pose'; ignore la requete.");
	     objets(obj) = false
	}
	
	// La fonction principale
	/////////////////////////
	
	/** La fonction publique, pour chercher la meilleure solution */
	def cherche(poids:Array[Int] , capacite:Int) {
	
	  // on va beaucoup utiliser cette valeur, alors on fait un alias 
	  // pour simplifier les ecritures suivantes
	  val len = poids.length
	  
	  // Affiche l'instance du probleme
	  print("Poids des objets: ")
	  for (i <- 0 to poids.length-1) 
	    print(" "+poids(i)+" ")
	  println("; Capacite: "+capacite)
	  
	  // variable locale pour sauvegarder la meilleure solution connue a tout moment
	  var meilleure:Array[Boolean] = Array.fill(len)(false)
	  var pas_meilleure:Array[Boolean] = Array.fill(len)(false);
	  	
	
	  /////////////////////////////////////////////////////////////////////////////////
	  // TODO: Placez ici l'appel recursif, avec les valeurs initiales des parametres
	  /////////////////////////////////////////////////////////////////////////////////
	  var recount = 0 ;
	  rec_aux(pas_meilleure, 0);
	  	
	  // Affiche la meilleure solution trouvee
	  println
	  print("Meilleure solution trouvee:")
	  for (i <- 0 to meilleure.length-1)
		print(" "+poids(i)+":"+(if (meilleure(i)) "O" else "N")+";")
	  println(" Valeur:"+valeurTotale(meilleure,poids)+" (la capacite etait "+capacite+")")
	  
	  println("\\\\\\ Rec Count = "+ recount);
	  ////////////////////////////////////////////////////////////////
	  // TODO: Definissez ici la fonction recursive a proprement parler
	  ////////////////////////////////////////////////////////////////
	  def rec_aux(current:Array[Boolean], prof:Int){
	    recount+=1
		  /*
		   * 1 si poid(current) > capacit�� => stop
		   * 2 si poid(current) > meilleur poid => on remplace meilleur
		   * 3 si prof > len cas terminal
		   */
	    print("(prof="+prof+") Explore ");
	    affiche(current,prof);
	    print(" Valeur: "+valeurTotale(current,poids)+" " ) ;
	    if(valeurTotale(current,poids)<=capacite){
	      
	      if(valeurTotale(current,poids)>valeurTotale(meilleure,poids)){
	        dupplique(current,meilleure);
	        print("Nouvelle meilleure solution");
	      }
	      if(prof == len){
	        println("(Cas terminal)");
	      } else {
	        println("(Cas general)");
	        mettreDansSac(current, prof);
	        rec_aux(current, prof+1);
	        retireDuSac(current, prof);
	        rec_aux(current, prof+1);
	      }
	      
	      
	    } else {
	      println("*** Oups, ca deborde (backtrack!) ***");
	    }
	  }
	} // Fin de la fonction cherche() principale



	// Le code de test, qui appelle la fonction publique
	////////////////////////////////////////////////////
	cherche(Array(5,4,3,2,7,13,6,5), 17)

}
