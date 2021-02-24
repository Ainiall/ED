package avl;

import java.util.ArrayList;
import java.util.Arrays;

import bst.BSTree;

public class AVLTree <T extends Comparable <T>>  extends BSTree<T>{
	
	private static final String VACIO= "";
	
	private AVLNode<T> raiz;
	int numAristas=0;
	String str = "";
	
	public AVLTree() {
		raiz=null;
	}
	
	/**
	 * Metodo que busca la existencia de un nodo a traves de su informacion
	 * @param info
	 * 		Informacion del nodo a buscar
	 * @return
	 * 		nodo completo si lo encuentra
	 * 		null si no lo encuentra
	 */
	public AVLNode<T> findNode(T info){
		if(raiz!=null) {
			return findNodeR(this.raiz,info);
		}
		return null;
	}
	
	/**
	 * Metodo recursivo auxiliar para encontrar el nodo entre los nodos hijos del arbol
	 * @param node
	 * 		Nodo privote
	 * @param info
	 * 		Informacion con la que comparar
	 * @return
	 * 		node Si se encuentra el nodo
	 * 		null Si no se encuentra el nodo
	 */
	public AVLNode<T> findNodeR(AVLNode<T> node, T info){
		 if(node==null) {
			return null;
		 }else if(info.compareTo(node.getInfo())==0) { //ya existe
			return node;
		 }else if(info.compareTo(node.getInfo())<0) { 
			 return findNodeR(node.getLeft(),info);
		 }else {
			 return findNodeR(node.getRight(),info);
		 }
	}
	
	/**
	 * Metodo para insertar un nuevo nodo al arbol AVL	
	 * @param info
	 * 		Informacion del nodo
	 * @return
	 * 		0 si el nodo se inserta correctamente
	 * 		-1 si el nodo no ha podido insertarse porque ya existe
	 */
	public int addNode(T info) {
		if(findNode(info)!=null) {
			return -1;
		}else {
			if(raiz==null) { //no existe el arbol, se crea el nodo sin mas
				this.raiz= new AVLNode<T>(info); //se crea 
				return 0;
			}else{
				this.raiz= addNodeR(this.raiz, info);
				return 0;
			}
		}
	}
	
	/**
	 * Metodo que actualiza el factor de balance una vez se añada el nodo y se tienen en cuenta todas
	 * las rotaciones
	 * @param node
	 * 		Nodo a tratar
	 * @param info
	 * 		Informacion del nodo
	 * @return
	 * 		updateTree(node) equilibra el arbol una vez añadido el nuevo nodo
	 */
	public AVLNode<T> addNodeR(AVLNode<T>node,T info){
		if(info.compareTo(node.getInfo())==0) { //si es igual a la raiz ya existe
			node.setInfo(info);
		}else if(info.compareTo(node.getInfo())<0) { //si el nodo nuevo es menor que la raiz (se inserta como hijo por la izquierda)
			if(node.getLeft()==null) { //si el nodo raiz no tiene hijos por la izquierda
				node.setLeft(new AVLNode<T>(info)); //se crea el nodo
			}else{
				node.setLeft(addNodeR(node.getLeft(), info)); //no se puede crear sin mas, hay que buscar un hueco null 
			}
		}else {//si el nodo nuevo es mayor que la raiz (se inserta como hijo por la derecha)
			if(node.getRight()==null) { //si el nodo raiz no tiene hijos por la derecha
				node.setRight(new AVLNode<T>(info));
			}else {
				node.setRight(addNodeR(node.getRight(), info)); //no se puede crear sin mas, hay que buscar un hueco null
			}
		}	
		return updateTree(node);
	}
	
	/**
	 * Metodo que actualiza el arbol cada vez que su factor de balance se desequilibre (es decir, alzance -2 o 2) 
	 * y aplica las rotaciones correspondientes (RDI, RSI, RDD, RSD) segun el caso para volver a equilibrar el arbol
	 * de nuevo.
	 * @param node
	 * 		Nodo sobre el que se aplica la rotacion
	 * @return
	 * 		Nodo actualizado
	 */
	
	private AVLNode<T> updateTree(AVLNode<T> node){
		//node.setFactorBalanceAltura();
		if(node.getFB()<=-2) { //valor negativo rotacion por la izquierda
			if(node.getLeft().getFB()==1) {
				node=doubleLeftRotation(node);
			}else
				node=singleLeftRotation(node);
		}else if(node.getFB()>=2) { //valor positivo rotacion por la derecha
			if(node.getRight().getFB()==-1) {
				node=doubleRightRotation(node);
			}else
				node=singleRightRotation(node);
		}
		return node;
	}
	
	/**
	 * Metodo que realiza la rotacion simple izquierda (RSI) sobre un nodo
	 * @param node
	 * 		Nodo al que se le aplica la rotacion
	 * @return
	 * 		Nodo balanceado
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T>node){
		AVLNode<T> aux = node.getLeft();	//se selecciona el hijo izquierdo
		node.setLeft(aux.getRight()); 		//asignamos el hijo derecho de aux al nodo padre 
		aux.setRight(node);					//aux pasa a ser el padre y el nodo anterior su ramal derecho
		//updateTree(node);
		//updateTree(aux);
		return aux;
	}
	
	/**
	 * Metodo que realiza la rotacion simple derecha (RSD) sobre un nodo
	 * @param node
	 * 		Nodo al que se le aplica la rotacion
	 * @return
	 * 		Nodo balanceado
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T>node){
		AVLNode<T> aux = node.getRight();	//se selecciona el hijo derecho
		node.setRight(aux.getLeft());		//asignamos el hijo izquierdo de aux al nodo padre 
		aux.setLeft(node);					//aux pasa a ser el padre y el nodo anterior su ramal izquierdo
		//updateTree(node);
		//updateTree(aux);
		return aux;
		
	}
	
	/**
	 * Metodo que realiza la rotacion doble izquierda (RDI) sobre un nodo
	 * Para ello primero se realiza una rotacion simple derecha (RSD) sobre el subarbol
	 * izquierdo y despues se aplica una rotacion simple izquierda (RSI) sobre el nodo
	 * modificado con la rotación anterior.
	 * @param node
	 * 			Nodo sobre el que se aplica la rotacion
	 * @return
	 * 			Nodo equilibrado
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T>node){ //RSD -> RSI
		AVLNode<T> aux = node.getLeft();			//se selecciona el hijo izquierdo
		node.setLeft(singleRightRotation(aux));		//se asigna el resultado de RSD sobre el hijo
		return singleLeftRotation(node);			//se aplica RSI sobre el nodo
		
	} 
	
	/**
	 * Metodo que realiza la rotacion doble derecha (RDD) sobre un nodo
	 * Para ello primero se realiza una rotacion simple izquierda (RSI) sobre el subarbol
	 * derecho y despues se aplica una rotacion simple derecha (RSD) sobre el nodo modificado
	 * con la rotacion anterior.
	 * @param node
	 * 			Nodo al que se le aplica la rotacion
	 * @return
	 * 			Nodo equilibrado
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T>node){	//RSI -> RSD
		AVLNode<T> aux = node.getRight();			//se selecciona el hijo derecho
		node.setRight(singleLeftRotation(aux));		//se asigna el resultado de RSI sobre el hijo
		return singleRightRotation(node);			//RDS sobre el nodo
	}
	
	/**
	 * Metodo que realiza el recorrido preOrder
	 * @return constante VACIO si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	public String preOrder() {
		return preOrderR(raiz);
	}
	
	/**
	 * Metodo auxiliar recursivo para realizar el recorrido preOrder
	 * @param node
	 * 			Nodo sobre el que se aplica el recorrido
	 * @return constante VACIO si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 * 		
	 */
	private String preOrderR(AVLNode<T> node) {
		if(node!=null) {
			return node.toString()+preOrderR(node.getLeft())+preOrderR(node.getRight());
		}else
			return VACIO;	
	}
	
	/**
	 * Metodo que realiza el recorrido inOrder
	 * @return constante VACIO si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	public String inOrder() {
		return inOrderR(raiz);
	}
	
	/**
	 * Metodo auxiliar recursivo para realizar el recorrido inOrder
	 * @param node
	 * 			Nodo sobre el que se aplica el recorrido
	 * @return constante VACIO si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	private String inOrderR(AVLNode<T> node) {
		if(node!=null) {
			return inOrderR(node.getLeft())+node.toString()+inOrderR(node.getRight());
		}else
			return VACIO;
	 }
	
	/**
	 * Metodo que realiza el recorrido postOrder
	 * @return constante VACIO si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	public String postOrder() {
		return postOrderR(raiz);
	}
	
	/**
	 * Metodo auxiliar recursivo para realizar el recorrido postOrder
	 * @param node
	 * 			Nodo sobre el que se aplica el recorrido
	 * @return  VACIO Constante si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	private String postOrderR(AVLNode<T> node) {
		 if(node!=null) {
			return postOrderR(node.getLeft())+postOrderR(node.getRight())+ node.toString();
		}else
			return VACIO;
	}
	
	/**
	 * Metodo que elimina el nodo cuya informacion se pase por parametro.
	 * @param info
	 * 		Informacion del nodo a borrar
	 * @return -2 si el arbol no existe
	 * 			-1 si no encuentra el nodo a borrar
	 * 			0 si logra borrarlo correctamente
	 */
	public int removeNode(T info) {
		if(raiz==null) {
			return -2;
		}if(findNode(info)==null){
			return -1;
		}
		this.raiz= removeNodeR(this.raiz,info);
		return 0;
	}
	
	/**
	 * Metodo auxiliar recursivo que itera hasta encontrar el nodo a borrar.
	 * Si el nodo a borrar es una hoja se asigna a null su referencia.
	 * Si el nodo tiene un hijo, se reasigna la referencia de su hijo al nodo padre.
	 * Si el nodo tiene dos hijos, se obtiene el mayor hijo izquierdo y se le asigna
	 * como nuevo padre, al que se le asignan las referencias del resto de hijos.
	 * 
	 * Al final del borrado es necesario volver a reequilibrar el arbol.
	 * @param nodo
	 * 		Nodo a borrar
	 * @param x
	 * 		Informacion del nodo a borrar
	 * @return
	 * 		updateTree(node) Arbol equilibrado tras efectual el borrado del nodo
	 * 		
	 */
	private AVLNode<T> removeNodeR(AVLNode<T> nodo, T x){
		if(x.compareTo(nodo.getInfo())<0) { //nodo menor, se recorre el subarbol izq
			nodo.setLeft(removeNodeR(nodo.getLeft(),x));
		}else if(x.compareTo(nodo.getInfo())>0) { //nodo mayor, se recorre el subarbol der
			nodo.setRight(removeNodeR(nodo.getRight(),x));
		}else { //si la informacion coincide se borra
			if(nodo.getLeft()==null && nodo.getRight()==null) { //si es hoja
				return null;
			}else if(nodo.getLeft()==null) {
				return nodo.getRight();
			}else if(nodo.getRight()==null) {
				return nodo.getLeft();
			}else { //dos hijos
				nodo.setInfo(getMax(nodo.getLeft())); //reemplazo por mayor nodo subarbol izq
				nodo.setLeft(removeNodeR(nodo.getLeft(), nodo.getInfo()));
			}
		}	
		return updateTree(nodo);
	}
	
	/**
	 * Metodo booleano que comprueba si dos nodos cuya informacion se pasa como parametros
	 * tienen relacion de padre e hijo entre si. 
	 * @param padre
	 * 		Informacion del nodo padre
	 * @param hijo
	 * 		Informacion del nodo hijo
	 * @return true Si el nodo padre contiene como hijo al nodo cuya informacion es hijo
	 * 			false Si el nodo padre no contiene como hijo al nodo cuya informacion es hijo
	 */
	public boolean esPadreDe(T padre, T hijo) {
		AVLNode<T> izq = findNode(padre).getLeft();
		AVLNode<T> der = findNode(padre).getRight();
		if(izq !=null && der==null) {
			if(hijo.compareTo(izq.getInfo())==0) 
				return true;
		}else if(der !=null && izq==null) {
			if(hijo.compareTo(der.getInfo())==0)
				return true;
		}else {
			if(hijo.compareTo(izq.getInfo())==0) 
				return true;
			else if(hijo.compareTo(der.getInfo())==0)
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Metodo que devuelve el numero de aristas que existen desde un nodo padre (a) a un
	 * nodo hijo (b)
	 * @param a
	 * 		Nodo padre
	 * @param b
	 * 		Nodo hijo
	 * @return
	 * 		numAristas Numero total de aristas existentes entre el nodo padre y el nodo hijo
	 */
	public int numAristas(T a, T b) {
		AVLNode<T> padre=findNode(a);
		numAristas=0;
			datos(padre,b);
		return numAristas;
	}
	
	/**
	 * Metodo que devuelve una cadena de caracteres que muestra el camino existente entre
	 * dos nodos cuya informacion se pasa como parametro, siendo el primero de ellos padre(a)
	 * y el segundo hijo(b) 
	 * @param a
	 * 		Nodo padre
	 * @param b
	 * 		Nodo hijo
	 * @return
	 * 		str Cadena que contiene el camino entre ambos nodos
	 */
	public String camino(T a, T b) {
		AVLNode<T> padre=findNode(a);
		str = "";
			datos(padre,b);
		return str;
	}
	
	/**
	 * Metodo auxiliar que realiza la misma funcion que findNodeR, pero que ademas
	 * rellena las variables str y numAristas en cada iteracion.
	 * Se crea este metodo en vez de rellenar las variables en el propio findNodeR
	 * para no sobrecargar ese metodo pues se usa muy recurrentemente a lo largo del 
	 * proyecto y estas operaciones son necesarias puntualmente.
	 * @param node
	 * 		Nodo pivote
	 * @param info
	 * 		Informacion a comparar
	 * @return
	 * 		node Nodo si encuentra el nodo
	 * 		null Si no encuentra el nodo
	 */
	public AVLNode<T> datos(AVLNode<T> node, T info){
		 if(node==null) {
			return null;
		 }else if(info.compareTo(node.getInfo())==0) { //ya existe
			 str+=node.toString();
			return node;
		 }else if(info.compareTo(node.getInfo())<0) { 
			 numAristas++;
			 str+=node.toString();
			 return datos(node.getLeft(),info);
		 }else {
			 numAristas++;
		 	 str+=node.toString();
			 return datos(node.getRight(),info);
		 }
	}
	
	
	/**
	 * Metodo auxiliar que obtiene el mayor nodo hijo existente en el subarbol izquierdo.
	 * @param node
	 * 		Nodo sobre el que aplicar la busqueda
	 * @return 
	 * 		node.getInfo() Informacion del mayor nodo del subarbol izquierdo
	 */
	public T getMax(AVLNode<T> node) {
		if(node==null) {
			return null;
		}else if(node.getRight()==null) {
			return node.getInfo();
		}else {
			return getMax(node.getRight());
		}
	}	
	
	 //****************************************	EXTRA **************************************************/
	
	public AVLNode<T> getRaiz() {
		return raiz;
	}
	
	public AVLTree<T> copiarArbol(AVLTree<T> copia, AVLNode<T> original){
		T node = original.getInfo();
		copia.addNode(node);

		if (original.getLeft() != null) {
			copiarArbol(copia, original.getLeft());
		}else if (original.getRight() != null) {
			copiarArbol(copia, original.getRight());
		}
		return copia;
	}
	
	
	public AVLTree<T> join(AVLTree<T> arbol) throws Exception{
		AVLTree<T> resultado = new AVLTree<T>();
		resultado = copiarArbol(resultado, this.getRaiz());
		joinR(resultado, arbol);
		return resultado;
	}
	
	
	private void joinR(AVLTree<T> arbol1, AVLTree<T> arbol2) throws Exception {
		AVLNode<T> node = arbol2.getRaiz();
		if (node != null) {
			if (arbol1.findNode(node.getInfo()) != arbol2.findNode(node.getInfo())) {
				arbol1.addNode(node.getInfo());
			}
			AVLTree<T> arbolLeft = new AVLTree<T>();
			arbolLeft.raiz = node.getLeft();
			arbol1.joinR(arbol1, arbolLeft);

			AVLTree<T> arbolRight = new AVLTree<T>();
			arbolRight.raiz = node.getRight();
			arbol1.joinR(arbol1, arbolRight);
		}
	}

	public AVLTree<T> interseccion(AVLTree<T> arbol) throws Exception{
		AVLTree<T> resultado = new AVLTree<T>();
		interseccionR(resultado, arbol);
		return resultado;
	}
	
	private void interseccionR(AVLTree<T> arbol1, AVLTree<T> arbol2) throws Exception {
	AVLNode<T> node = this.getRaiz();
		if (node != null) {
			if (arbol2.findNode(node.getInfo())==node.getInfo()) {
				arbol1.addNode(node.getInfo());
			}
			AVLTree<T> arbolLeft = new AVLTree<T>();
			arbolLeft.raiz = node.getLeft();
			arbolLeft.interseccionR(arbol1, arbol2);

			AVLTree<T> arbolRight = new AVLTree<T>();
			arbolRight.raiz = node.getRight();
			arbolRight.interseccionR(arbol1, arbol2);
		}
	}
	
	
	
	// suma las infos de todos los nodos
	public double datosArbol() {
        String str = recorrido();
        double totalDatos=0;
        ArrayList<String> aList= new ArrayList<String>(Arrays.asList(str.split("\t")));
        for(int i=0;i<aList.size();i++){
            totalDatos= totalDatos + Double.parseDouble(aList.get(i));
        }
        return totalDatos;
    }
    
	public double getMediaDatos() {
		return datosArbol()/getNumNodos();
	}
	
	//inOrder que devuelve exclusivamente la info del nodo
	public String recorrido() {
		return recorridoR(raiz);
	}
	
	private String recorridoR(AVLNode<T> node) {
		if(node!=null) {
			return recorridoR(node.getLeft())+node.print()+recorridoR(node.getRight());
		}else
			return VACIO;
	 }
    
	//devuelve la altura de la raiz
    public int getAlturaArbol() {
    	return raiz.getAltura();
    }
	
    //recorre el arbol y cuenta los nodos existentes
    public int getNumNodos() {
    	cont=0;
    	if(raiz==null) {
    		return 0;
    	}else {
    		return getNumNodosR(raiz);
    	}
    }

    private int getNumNodosR(AVLNode<T> node) {
        if (node != null) {
            cont++;
            getNumNodosR(node.getLeft());
            getNumNodosR(node.getRight());
            return cont;
        }
        return 0;
    }
    
    //num nodos del subarbol (raiz subarbol y nodos hijos)
    public int getNumNodesSubarbol(AVLNode<T> node) {
    	return getNumNodosR(node) - getNumNodos();
    }
    
    
    //nodos en comun entre 2 arboles
    @SuppressWarnings("unchecked")
	public AVLTree<T> interseccion2(AVLTree<T> arbol1, AVLTree<T> arbol2){
    	AVLTree<T> resultado = new AVLTree<T>();
    	String str1 = arbol1.recorrido();
    	String str2 = arbol2.recorrido();
    	ArrayList<String> aList1= new ArrayList<String>(Arrays.asList(str1.split("\t")));
    	ArrayList<String> aList2= new ArrayList<String>(Arrays.asList(str2.split("\t")));
        for(int i=0;i<aList1.size();i++){
        	for(int j=0;j<aList2.size();j++){
        		if(aList1.get(i).equals(aList2.get(j))) {
        			 resultado.addNode((T) aList1.get(i));
        		}
        	}
        }
		return resultado; 
    }
    
    
    //union entre 2 arboles
    @SuppressWarnings("unchecked")
	public AVLTree<T> merge(AVLTree<T> arbol1, AVLTree<T> arbol2){
    	AVLTree<T> resultado = new AVLTree<T>();
    	AVLTree<T> comunes = interseccion2(arbol1, arbol2);
    	String str1 = arbol1.recorrido();
    	String str2 = arbol2.recorrido();
    	String comun = comunes.recorrido();
    	ArrayList<String> aList1= new ArrayList<String>(Arrays.asList(str1.split("\t")));
    	ArrayList<String> aList2= new ArrayList<String>(Arrays.asList(str2.split("\t")));
    	ArrayList<String> aListComunes= new ArrayList<String>(Arrays.asList(comun.split("\t")));
    	aList2.removeAll(aListComunes);
    	aList1.addAll(aList2);
    	for(int i=0;i<aList1.size();i++){
    		resultado.addNode((T) aList1.get(i));
    	}
    	return resultado;
    }
}