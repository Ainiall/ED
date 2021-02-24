package graphs;


import java.text.DecimalFormat;

/**
 * Clase grafo con los metodos necesarios para el manejo de estos.
 * 
 * @author uo270318 / angela
 *
 * @param <T>
 * 		Dato generico.
 */
public class Graph<T> {
	//constantes
	private static final double INFINITO = Double.POSITIVE_INFINITY; //variable infinito
	private static final int SIN_CONEXION = -1;
	private static final int VACIO= -1;

	private T [] nodes; //El contenido de cada nodo es de tipo generico
	private boolean [][] edges; //Filas:origen, columnas: destino
	private double[][] weights; //Pesos de los ejes (relaes positivos)
	int numNodes; //Numero de nodos almacenados en un momento dado
	
	String recorrido = " ";

	//Floyd
	private double[][] A; //Matriz de costes que genera Floyd (costes)
	private int [][] PF; //Matriz de predecesores que genera Floyd (int posicion)
	//private T[][] PF; //Matriz de predecesores que genera Floyd (T Objeto)

	/**
	 * Inicializa el numero de nodos a 0 y crea tanto el vector de nodos como las matrices de adyacencia y peso
	 *
	 * @param n
	 * 		Numero maximo de nodos que se pueden insertar
	 */
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		nodes =(T[]) new Object[n]; //.lenght indica size total de la estructura
		edges = new boolean[n][n];
		weights = new double [n][n];
		numNodes =0;
		
		//Floyd
		A = new double[n][n]; //matriz costes
		PF = new int[n][n]; //matriz ruta de costes
		//PF= (T[][]) new Object[n][n];
		
	}
	/**
	 * Metodo que devuelve el nodo de la posicion indicada
	 * 
	 * @param node
	 * 		Nodo a devolver
	 * @return
	 * 		 i Nodo que se encuentra en la posicion indicada
	 * 		-1 Si no encuentra un nodo en ese indice
	 * 		
	 */
	public int getNode(T node) {
		for (int i=0; i<numNodes; i++) {
			if(nodes[i].equals(node)) {
				return i;
			}
		}
		return -1;	
	}
	/**
	 * Metodo que inserta un nuevo nodo a la fila final y rellena la fila y columna de las
	 * matrices de aristas y pesos. 
	 * Para ello primero se comprueba que el nodo no exista y que haya espacio suficiente.
	 * 
	 * @param node
	 * 		Nodo a insertar
	 * @return
	 * 		0 si pudo insertar el nodo
	 * 		1 si no pudo insertar el nodo (ya existe, falta de espacio,etc)
	 */
	public int addNode(T node) {
		if(numNodes<nodes.length && !existNode(node)) {
			nodes[numNodes]=node; // add node
			//nuevas aristas
			for(int i=0; i<numNodes; i++) {
				edges[numNodes][i] = false; //inicializa ejes
				edges[i][numNodes] = false;
				weights[numNodes][i] = 0.0; //inicializa pesos
				weights[i][numNodes] = 0.0;
			}
			++numNodes;
			//Vuelve a inicializar A y PF (Floyd)   (opcional)
			inicializaA();
			inicializaPF();
			return 0;
		}else
			return -1;
	}
	/**
	  * Metodo para comprobar si existe o no un nodo 
	  *  
	  * @param node 
	  * 	Nodo que queremos comprobar 
	  * @return 
	  * 	true Si el nodo existe 
	  * 	false Si el nodo no existe 
	  */ 
	 public boolean existNode(T node) { 
		 if (getNode(node) >= 0) 
			 return true; 
	 	else 
	 		return false; 
	 } 
	/**
	 * Metodo para eliminar el nodo seleccionado.
	 * Si es el ultimo nodo se disminuye en 1 el numero de elementos de la matriz.
	 * Si no es el ultimo se traspasan los valores de la ultima posicion a la posicion donde
	 * estaba el nodo a borrar.
	 * 
	 * @param node
	 * 		Nodo a borrar.
	 * @return 
	 * 		0 si pudo borrar el nodo
	 * 		1 si no pudo borrar el nodo (no existe)
	 */
	public int removeNode(T node) {			
		int i = getNode(node);
		if (existNode(node)) { 
			--numNodes;
			if (i != numNodes + 1) { //si no es el ultimo
				nodes[i]=nodes[numNodes]; //se intercambia la ultima posicion a node
				//se reemplazan los vectores
				for (int j = 0; j < numNodes; j++) {
					edges[j][i] = edges[j][numNodes];
					edges[i][j] = edges[numNodes][j];
					weights[j][i] = weights[j][numNodes];
					weights[i][j] = weights[numNodes][j];
				}
				edges[i][i] = edges[numNodes][numNodes];
				weights[i][i] = weights[numNodes][numNodes];
				
				//Vuelve a inicializar A y PF (Floyd)	(opcional)
				inicializaA();
				inicializaPF();
			}
			
			return 0;
		}
		return -1;		
	}
	/**
	 * Metodo que comprueba si existe un eje entre dos nodos.
	 * 
	 * @param source
	 * 		Nodo origen.
	 * @param target
	 * 		Nodo destino.
	 * @return 
	 * 		true Si existen los nodos y hay conexion entre ellos.
	 * 		false Si no existe alguno de los nodos o no hay conexion entre ellos.
	 */
	public boolean existEdge(T source, T target) {
		if (existNode(source) && existNode(target) && (edges[getNode(source)][getNode(target)])) {
				return true;
		}
		return false;
	}
	/**
	 * Metodo que devuelve el peso asociado a un eje si este existe.
	 * 
	 * @param source 
	 * 		Nodo origen.
	 * @param target
	 * 		Nodo destino.
	 * @return
	 * 		weights Si existe la conexion entre ellos.
	 * 		-1 Si no hay conexion entre ellos, uno no existe o no hay peso asociado.
	 */
	public double getEdge(T source, T target) {
		if (existNode(source) && existNode(target) && (existEdge(source, target))) {
			return weights[getNode(source)][getNode(target)];
		}
		else
			return -1;	
	}
	/**
	 * Metodo que inserta una union entre 2 nodos si existen e inicializa su peso.
	 * 
	 * @param source 
	 * 		Nodo de origen.
	 * @param target
	 * 		Nodo destino.
	 * @param weight
	 * 		Peso asociado al arco.
	 * @return
	 * 		0 Si se inserta correctamente.
	 * 		-1 Si no existe alguno de los nodos o ya existe una union entre ellos.
	 */
	public int addEdge(T source, T target, double weight) {
		if(existNode(source) && existNode(target) && !existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			edges[i][j]=true;
			weights[i][j]= weight;
			return 0;
		}
		return -1;
	}
	/**
	 * Metodo que elimina el arco que une dos nodos.
	 * 
	 * @param source
	 * 		Nodo origen.
	 * @param target
	 * 		Nodo destino.
	 * @return
	 * 		0 Si se borra el eje con exito.
	 * 		-1 Si se intenta borrar un eje que no existe o no existe alguno de los nodos
	 */
	public int removeEdge(T source, T target) {
		if(existEdge(source,target)){
			int i = getNode(source);
			int j = getNode(target);
			edges[i][j]=false;
			weights[i][j]=0.0;
			return 0;
		}
		return -1;
	}
	
	
	public String toString() {
		//almacenar en una cadena los nodos seguido de la matriz de ejes seguido de la matriz de pesos
		DecimalFormat df=new DecimalFormat("#.##");
		String cadena="";
		
		cadena +="VECTOR NODOS\n";
		for(int i=0;i<numNodes;i++) {
			cadena+=nodes[i].toString() + "\t";
		}
		cadena+="\n\nMATRIZ ARISTAS\n";
		for(int i=0;i<numNodes;i++) {
			for(int j=0;j<numNodes;j++) {
				if(edges[i][j]) cadena +="T\t";
				else cadena+="F\t";
			}
			cadena+="\n";
		}
		cadena+="\nMATRIZ PESOS\n";
		for(int i=0;i<numNodes;i++) {
			for(int j=0;j<numNodes;j++) {
				cadena+=(edges[i][j]?df.format(weights[i][j]):"-")+"\t";
			}
			cadena+="\n";
		}
		return cadena;
	}


	//**************************************** METODOS DIJKSTRA **************************************************/


	/**
	  * Metodo que calcula el camino de coste minimo a todos los nodos del 
	  * grafo desde un nodo origen y devuelve los pesos asociados a este.
	  * Para ello se inicializan los vectores de nodos (bool[]),pesos (D[]) y 
	  * camino(P[]).
	  * El vector de nodos se inicializa con el nodoOrigen true y el resto false
	  * pues aun no han sido visitados.
	  * El vector pesos y el vector camino se inicializan segun exista un arco entre
	  * el nodoOrigen y ellos o no.
	  * En caso de existir, se actualiza el vector D con los pesos correspondientes 
	  * de la matriz weights y la matriz P se actualiza con el indice de nodoOrigen.
	  * En caso de no existir un arco entre ellos, se actualiza el peso a una constante
	  * INFINITO y el camino a una constante SIN_CONEXION.
	  * 
	  * En cada iteracion se va seleccionando un nuevo nodo intermedio (w), desde el que 
	  * continua el calculo. Este se obtiene a traves del metodo minCost, donde se comparan
	  * los costes y se obtiene el nodo de coste minimo que pasa a ser el pivote.
	  * 
	  * Siempre que el pivote tenga aristas con otros nodos sin visitar se actualizan los 
	  * vectores D[] y P[] hasta que al final muestran los costes minimos y el camino asociado 
	  * a ellos.
	  * 
	  * @param nodoOrigen
	  * 		nodo origen desde el que iniciamos el recorrido
	  * @return D
	  * 		vector D[] de doubles que recoge los pesos minimos de ese camino
	  * de coste minimo.
	  */ 
	public double[]dijkstra( T nodoOrigen){
		//inicializacion
		int index = getNode(nodoOrigen); //indice nodoOrigen
		double[] D = new double[numNodes]; //vector costes
		Integer[] P = new Integer[numNodes]; //vector camino
		boolean[] bool = new boolean[numNodes]; //vector nodos usados (true S false V)
		for (int i = 0; i < numNodes; i++) { 
			if (edges[index][i]) { //si hay arista de nodoOrigen a nodos
				D[i] = weights[index][i]; 
				P[i] = index;		      
			} else { //si no hay arista (false)
				D[i] = INFINITO;	//se inicializa a infinito el peso 
				P[i] = SIN_CONEXION; //se inicializa a -1 la conexion
			}
		}
		bool[index] = true; //indice nodoOrigen a true (S relleno con nodoOrigen)
		
		for (int j = 0; j < numNodes; j++) { //se recorre el grafo
			int w = minCost(D, bool); //pivote
			if (w != -1) { //mientras haya aristas
				bool[w] = true;
				for (int m = 0; m < numNodes; m++) {
					if(edges[w][m] && !bool[m]) { //si hay aristas a nodos sin visitar
						if (D[w] + weights[w][m] < D[m]) { 
							D[m] = weights[w][m] + D[w]; 
							P[m] = w; //path actualizado
						}
					}
				}
			}
		}
		return D;
	}
	
	/**
	  * Metodo que encuentra el nodo con coste minimo de un grafo.
	  * Desde este nodo se continua pivotado en dijkstra.
	  *  
	  * @param D 
	  * 	vector D[] de pesos 
	  * @param bool 
	  *	 	vector bool[] de booleans, true los nodos que ya han sido visitados (S)
	  * @return nextNode
	  * 	el nodo con coste minimo al que se puede acceder en la proxima iteracion
	  */ 
	 private int minCost(double[] D, boolean[] bool) { 
		double minCost = INFINITO;
		int nextNode = SIN_CONEXION; //nodo perteneciente a v
		
		for (int i = 0; i < numNodes; i++) { //se recorre graph
			if (D[i] < minCost && !bool[i]) { //menor y sin visitar
				minCost = D[i]; //actualiza menor peso
				nextNode = i; //indice de menor peso pasa a ser el siguiente pivote
			}
		}
		return nextNode;
	 } 	
	 
	 //****************************************	METODOS FLOYD **************************************************/

	 
	 /**
	  * Generara las matrices A(de costes minimos) y P de predecesores de Floyd.
	  * Para ello primero se inicializan las matrices a INFINITO Y VACIO y se van 
	  * actualizando siempre que el numero de nodos sea mayor o igual que 1.
	  * 
	  * En la diagonal de la matriz de pesos ponemos ceros y si hay aristas 
	  * copiamos los datos de la matriz pesos. Si hay aristas en la matriz PF 
	  * ponemos el indice que corresponda.
	  * 
	  * Vamos pivotando a lo largo de la matriz de forma que si el coste de ir
	  * a traves del pivote es menor, se actualizan los datos.
	  * 
	  * @return 0 Si genera ambas matrices
	  * 		-1 Si el numero de nodos del grafo es menor o igual de 1
	  * 
	  */
	public int floyd() {
		inicializaA();
		inicializaPF();
		if (numNodes >= 1) {	
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j <  numNodes; j++) {
					if (edges[i][j]) { //si hay camino
						A[i][j] = weights[i][j];
						PF[i][j] = i;
					} else if (i == j) { //diagonal a 0
						A[i][j] = 0;
						PF[i][j] = VACIO; 
					}else { //si no hay camino
						A[i][j] = INFINITO;	
						PF[i][j] = VACIO; //init a -1
					}
					
				}
			}
			
			for (int pivote = 0; pivote < numNodes; pivote++) {
				for (int origen = 0; origen < numNodes; origen++) {
					for (int destino = 0; destino < numNodes; destino++) {
						if (A[origen][pivote] + A[pivote][destino] < A[origen][destino]) {
							A[origen][destino] = A[origen][pivote] + A[pivote][destino];
							PF[origen][destino] = pivote; //ojo index pivote
						}
					}
				}
			}
			return 0;
		}
		return -1;
	 }

	 /**
	  * Metodo que devuelve la matriz A (Floyd)
	  * 
	  * @return A
	  * 	Matriz A de Costes Minimos de Floyd
	  */
	 public double[][] getFloydA() {
		 return A;
	 }
	 /**
	  * Metodo que devuelve la matriz PF (Floyd) 
	  * 
	  * @return PF
	  * 	Matriz PF de Rutas de Costes Minimos de Foyd
	  */
	 public int[][] getFloydPF() {
		 return PF;
	 }
	 /**
	  * Metodo que inicializa la matriz A (Floyd)
	  * Inicializa a infinitos.
	  */
	 private void inicializaA() {
		 for(int i=0;i<numNodes;i++) {
			 for(int j=0;j<numNodes;j++) {
				 A[i][j]= INFINITO;
			 }
		 }
	 }
	 /**
	  * Metodo que inicializa la matriz PF (Floyd)
	  * Inicializa a null si es una matriz de Objeto Generico
	  * Si es una matriz de enteros (porque guardo la posicion) se inicializa a -1
	  */
	 private void inicializaPF() {
		 for(int i=0;i<numNodes;i++) {
			 for(int j=0;j<numNodes;j++) {
				 PF[i][j]= VACIO;
			 }
		 }
	 }
	
	 
	 /**
	  * Devuelve el coste del camino de coste minimo entre dos nodos que se pasan como parametros
	  * usando la matriz Floyd
	  * 
	  * @param nodoOrigen
	  * 		Nodo desde el que se parte.
	  * @param nodoDestino
	  * 		Nodo destino.
	  * @return 
	  * 		coste del camino coste minino entre dos nodos
	  * 		-1 si no hay camino o no existen los nodos
	  * 
	  */
	 public double minCostPath(T nodoOrigen, T nodoDestino) {
		 if(existNode(nodoOrigen)&&(existNode(nodoDestino))) {
			 return A[getNode(nodoOrigen)][getNode(nodoDestino)];
		 }
		 return SIN_CONEXION;

	 }
	 
	 /**
	  * Devuelve el camino de coste minimo entre dos nodos que se pasan como parametros
	  * usando la matriz floyd
	  * @param nodoOrigen
	  * 		Nodo desde el que partimos
	  * @param nodoDestino
	  * 		Nodo de destino
	  * @return str
	  * 		si origen = destino se muestra uno de ellos
	  *			si no hay camino nodoOrigen\t(Infinifo)\tnodoDestino
	  *			si no existen null;
	  *			si hay camino entre ambos nodos
	  * 		nodoOrigen \t(costeO)\tIntermedio1\t(coste1)...IntermedioN\t(costeN)
	  */
	 public String path(T nodoOrigen, T nodoDestino) {
		 String str = "	";
			int indexOrigen = getNode(nodoOrigen);
			int indexDestino = getNode(nodoDestino);
			int indexOrigenAux= indexOrigen;
			//Uno o ambos nodos no existen
			if (!existNode(nodoOrigen) ||!existNode(nodoDestino)) {
				return null;
			}else if (nodoOrigen.equals(nodoDestino)) {//origen y destino son iguales
				return nodoOrigen.toString();
			}
			
			boolean[] s = new boolean[numNodes];
			str = nodoOrigen.toString() + "\t" ;
			int aux = PF[indexOrigen][indexDestino];
			if (PF[indexOrigen][indexDestino] == -1) { //si no hay camino
				str = str + "(Infinito)\t";
			} else { //si existe camino
				if (aux == indexOrigen) { //poco probable
					str = str + "(" + A[indexOrigen][indexDestino] + ")" + "\t";
				} else {  //si tiene intermedios
				for (int i = 0 ; i < numNodes ; i++) {
					if (aux != indexDestino && !s[aux]) { //si aux no es el destino y no hemos visitado aux
						str = str + "(" + A[indexOrigenAux][aux] + ")\t" + nodes[aux].toString() + "\t";
						indexOrigenAux=aux;
						s[getNode(nodes[aux])]=true;
						aux = PF[aux][indexDestino];
						
					}
					
				}
				str = str + "(" + A[aux][indexDestino] + ")\t";
				}
			}

			str = str + nodoDestino.toString();

			return str;
	 }
	 
	 
	 
	 //****************************************	RECORRIDO EN PROFUNDIDAD **************************************************/
	 
	 /**
	  * Se recorren los nodos del grafo a partir de un nodo inicial (nodoOrigen)
	  * siguiendo el camino indicado por sus arcos de manera recursiva utilizando
	  * el metodo auxiliar recProfRec. No recorre necesariamente 
	  * todos los nodos.
	  * @param nodoOrigen
	  * 	Nodo desde el que empieza el recorrido.
	  * @return recorrido
	  * 	Cadena de caracteres que muestra el recorrido en profundidad.
	  * 	null si no existe el nodoOrigen.
	  */

	public String recorridoProfundidad(T nodoOrigen){
		recorrido = "";
		boolean[] visitados = new boolean[numNodes];		
		int index = getNode(nodoOrigen);
		if(!existNode(nodoOrigen)) {
			return "null";		 
		}
		recProfRec(index, visitados);
		return recorrido;
	}

	/**
	 * Metodo auxiliar para recorridoProfundidad que calcula el resultado de 
	 * manera recursiva. Va actualizando el String en cada iteracion. 
	 */
	private void recProfRec(int index, boolean[] visitados){
		if(!visitados[index]){ //si aun no han sido visitados
			recorrido= recorrido + nodes[index].toString() + "\t"; //inserta nodo a string
			visitados[index] = true;

			for(int i = 0; i < numNodes; i++){
				if(edges[index][i]){ //aristas para buscar hijos
					recProfRec(i, visitados);
				}
			}
		}
	}
	
	 //****************************************	EXTRA **************************************************/
	
	public void getCapacidadGrafo() {
		System.out.println(numNodes);
	}
	
	//
	public boolean nodoFuertementeConexo(T nodo){
		if(!existNode(nodo))
			return false;
		if(recorridoProfundidad(nodo)!=recorrido)
				return true;
		return false;
		
	}
	public boolean grafoFuertementeConexo(){
		for(int i=0;i<numNodes;i++)
			if(nodoFuertementeConexo(nodes[i]))
				return true;
		
		return false;
	}

	//camino longitud minima
	public double[]dijkstra2( T nodoOrigen){
		//inicializacion
		int index = getNode(nodoOrigen); //indice nodoOrigen//Dijkstra 
		double[] D = new double[numNodes]; //vector costes
		Integer[] P = new Integer[numNodes]; //vector camino
		boolean[] bool = new boolean[numNodes]; //vector nodos usados (true S false V)
		for (int i = 0; i < numNodes; i++) { 
			if (edges[index][i]) { //si hay arista de nodoOrigen a nodos
				D[i] = 1; 
				P[i] = index;		      
			} else { //si no hay arista (false)
				D[i] = INFINITO;	//se inicializa a infinito el peso 
				P[i] = SIN_CONEXION; //se inicializa a -1 la conexion
			}
		}
		bool[index] = true; //indice nodoOrigen a true (S relleno con nodoOrigen)
		
		for (int j = 0; j < numNodes; j++) { //se recorre el grafo
			int w = minCost(D, bool); //pivote
			if (w != -1) { //mientras haya aristas
				bool[w] = true;
				for (int m = 0; m < numNodes; m++) {
					if(edges[w][m] && !bool[m]) { //si hay aristas a nodos sin visitar
						if (D[w] + weights[w][m] < D[m]) { 
							D[m] = weights[w][m] + D[w]; 
							P[m] = w; //path actualizado
						}
					}
				}
			}
		}
		return D;
	}
	
	 
	 /**
	  * Metodo que devuelve la excentricidad de un nodo, que es el mayor de los costes minimos 
	  * que tienen a ese nodo como destino. (aristas saliente del resto de nodos a nodo)
	  *  
	  * @param nodo 
	  * 		Nodo del que se quiere calcular la excentricidad 
	  * @return excentricidad_nodo
	  * 		Peso del coste mayor dentro de los costes minimo 
	  */ 
	public double excentricidad(T nodo) { 
	  floyd();
	  int index = getNode(nodo); 
	  double excentricidad_nodo = 0.0; 
	 
	  for (int i = 0; i < numNodes; i++) { 
		  if(A[i][index]!=INFINITO) {
			  if (A[i][index] > excentricidad_nodo) { 
				  excentricidad_nodo= A[i][index]; 
			  } 
		  }
	  } 
	  
	  return excentricidad_nodo; 
	 } 

	//revisar
	public T getNodoMasExcentrico(){
		   double max = 0.0;
		   T node = null;
		   
		   for(int i=numNodes-1; i>=0; i--){
			   if(excentricidad(nodes[i]) >= max){
				   //System.out.println(i);
				   max = excentricidad(nodes[i]);
			   	   node = nodes[i];
			   }
		   }
		   
		   return node;
	   }
	
	
	 /**
	  * Metodo para calcular el centro de un grafo,que es el nodo de minima
	  * excentricidad.
	  *  
	  * @return value, posicion que ocupa el nodo central dentro del grafo 
	  */ 
	 public int centroGrafo() { 
	  double[] aux = new double[numNodes]; //vector auxiliar con excentricidad
	  for (int i = 0; i < numNodes; i++) { 
		  aux[i] = excentricidad(nodes[i]); 
	  } 
	 
	  double min = INFINITO; 
	  int valor = 0; 
	  for (int i = 0; i < numNodes; i++) { 
		  if (aux[i] < min) { 
			  min = aux[i]; 
			  valor = i; 
		  } 
	  } 
	  return valor; 
	 }   
		   
	
	
	 //****************************************	EXAMEN **************************************************/
	
	
	 /**
	    * Metodo que calcula el grado de salida de un nodo
	    * 
	    * @param nodo
	    * 		Nodo a medir
	    * @return grado si tiene aristas salientes
	    * 		  -1 si no tiene aristas salientes
	    */
	   public int gradoSalida(T nodo){
		   int grado = 0;
		   if(existNode(nodo)) {
			   for(int i=0; i<numNodes; i++){
				   if(existEdge(nodo, nodes[i])){
					   grado +=1;
				   }
			   }
			   return grado;
		   }
		   return -1;
	   }
	   
	   /**
	    * Metodo que calcula el grado de entrada de un nodo
	    * 
	    * @param nodo
	    * 		Nodo a medir
	    * @return grado si tiene aristas entrantes
	    * 		  -1 si no tiene aristas entrantes
	    */
	   public int gradoEntrada(T nodo){
		   int grado = 0;
		   if(existNode(nodo)) {
			   for(int i=0; i<numNodes; i++){
				   if(existEdge(nodes[i], nodo)) {
					   grado+=1;
				   }
			   }
			   return grado;
		   }
		   return -1;   
	   }
	   
	
	//aristas salientes solamente
	public boolean esNodoFuente(T nodo){
		if(gradoSalida(nodo)>0 && gradoEntrada(nodo)==0) {
			return true;
		}
		return false;
	}
	
	//aristas entrantes solamente
	public boolean esNodoSumidero(T nodo){
		if(gradoSalida(nodo)==0 && gradoEntrada(nodo)>0) {
			return true;
		}
		return false;
	}
	
	//sin aristas con otros nodos
	public boolean esNodoAislado(T nodo){
		if(gradoSalida(nodo)==0 && gradoEntrada(nodo)==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que muestra un entero distinto segun sea nodo aislado, sumidero, fuente o ninguno 
	 * de ellos
	 * @param nodo
	 * 		Nodo a tratar
	 * @return
	 * 		0 si es un nodo aislado
	 * 		1 si es un nodo fuente
	 * 		2 si es un nodo semidero
	 * 		-1 si no es ninguno de esos tipos o el nodo no existe
	 */
	public int tipoNodo(T nodo) {
		if(existNode(nodo)) {
			if(esNodoAislado(nodo)) {
				return 0;
			}else if(esNodoFuente(nodo)) {
				return 1;
			}else if(esNodoSumidero(nodo)) {
				return 2;
			}else {
				return -1;
			}
		}
		return -1;
	}
	
	/**
	 * Metodo que muestra una serie de datos sobre los nodos en un vector de enteros
	 * @return vector
	 * 			Vector con los datos.
	 */
	public Integer[] informacionGrafo() {
		Integer[] vector = new Integer[numNodes]; //vector informacion
		int fuente= 0, sumidero = 0, aislado= 0, aristas= 0, completo=0;
		for(int i=0;i<numNodes;i++) {
			if(esNodoFuente(nodes[i])) {
				fuente+=1;
			}
			if(esNodoSumidero(nodes[i])) {
				sumidero+=1;
			}
			if(esNodoAislado(nodes[i])) {
				aislado+=1;
			}
			for(int j=0;j<numNodes;j++) {
				if(edges[i][j]) {
					aristas+=1;
				}	
			}
			if(aristas==numNodes*(numNodes-1)) {
				completo=1;
			}else {
				completo=0;
			}
			
		}
		vector[0] = numNodes;
		vector[1] = fuente;
		vector[2] = sumidero;
		vector[3] = aislado;
		vector[4] = aristas;
		vector[5] = completo;

		return vector;
		
	}
	
 } 