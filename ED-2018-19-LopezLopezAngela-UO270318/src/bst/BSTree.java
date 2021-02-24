package bst;


public class BSTree <T extends Comparable<T>> {
	private static final String VACIO= "";
	private BSTNode<T> raiz; //nodo raiz del arbol
	
	public int cont;
	public int altura;
	 
	public BSTree() {
		raiz=null;
	}
	
	/**
	 * Inserta un nodo al arbol vacio o a un arbol que ya tenga nodos insertados
	 * @param info
	 * 			Informacion del nuevo nodo
	 * @return 0 si se inserta correctamente la informacion que se pasa como parametro
	 * 			-1 si ya existe un nodo que contiene esa informacion
	 */
	public int addNode(T info) {
		if(findNode(info)!=null) {
			return -1;
		}else {
			if(raiz==null) { //no existe el arbol, se crea el nodo sin mas
				this.raiz= new BSTNode<T>(info); //se crea 
				return 0;
			}else{
				addNodeR(this.raiz, info);
				return 0;
			}
		}
	}
	
	/**
	 * Metodo auxiliar recursivo que añade un nuevo nodo en la posicion adecuada
	 * @param node
	 * 		Nodo a tratar
	 * @param info
	 * 		Informacion del nodo
	 * @return
	 * 		0 si el nodo se ha insertado correctamente
	 * 		-1 si el nodo ya existe
	 */
	private int addNodeR(BSTNode<T> node, T info) {
		if(info.compareTo(node.getInfo())==0) { //si el nodo ya existe 
			return -1;
		}else if(info.compareTo(node.getInfo())<0) { //si el nodo nuevo es menor que la raiz (se inserta como hijo por la izquierda)
			if(node.getLeft()==null) { //si el nodo raiz no tiene hijos por la izquierda
				node.setLeft(new BSTNode<T>(info)); //se crea el nodo
			}else{
				addNodeR(node.getLeft(), info); //no se puede crear sin mas, hay que buscar un hueco null 
			}
		}else {//si el nodo nuevo es mayor que la raiz (se inserta como hijo por la derecha)
			if(node.getRight()==null) { //si el nodo raiz no tiene hijos por la derecha
				node.setRight(new BSTNode<T>(info));
			}else {
				addNodeR(node.getRight(), info); //no se puede crear sin mas, hay que buscar un hueco null
			}
		}	
		return 0;
	}
	
	/**
	 * Buscar o intentar buscar un nodo que contenga la informacion que pasamos como parametro
	 * @param node
	 * 		Informacion del nodo a buscar
	 * @param info
	 * @return nodo Nodo completo si encuentra esa informacion
	 * 			null Si no lo encuentra
	 * 
	 */
	public BSTNode<T> findNode(T info){
		if(raiz!=null) {
			return findNodeR(this.raiz,info);
		}
		return null;
	}
	
	/**
	 * Metodo auxiliar que busca de manera recursiva entre los hijos del arbol padre.
	 * @param node
	 * 		Nodo pivote
	 * @param info
	 * 		Informacion con la que comparar
	 * @return
	 * 		node Nodo completo
	 * 		null Si el nodo no existe 
	 * 
	 */
	 private BSTNode<T> findNodeR(BSTNode<T> node, T info) {
		 if(node==null) {
			return null;
		 }else if(info.compareTo(node.getInfo())==0) { //ya existe
			return node;
		 }else if(info.compareTo(node.getInfo())<0) {  
			 return findNodeR(node.getLeft(),info);
		 }else
			 return findNodeR(node.getRight(),info);
	 }
	
	/**
	 * Metodo que muestra por pantalla el recorrido preOrder del arbol
	 * @return preOrdenR(raiz) Si el arbol existe
	 * 			VACIO Constante vacia si el arbol no existe
	 */
	public String preOrder() {
		return preOrderR(raiz);
	}
	/**
	 * Metodo auxiliar recursivo que genera el recorrido preOrder
	 * @param node
	 * 		Nodo sobre el que se aplica el recorrido
	 * @return VACIO Constante si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	private String preOrderR(BSTNode<T> node) {
		if(node!=null) {
			return node.toString()+preOrderR(node.getLeft())+preOrderR(node.getRight());
		}else
			return VACIO;
		
	}
	/**
	 * Metodo que muestra por pantalla el recorrido inOrder del arbol
	 * @return inOrdenR(raiz) si el arbol existe
	 * 			VACIO constante vacia si el arbol no existe
	 */
	public String inOrder() {
		if(raiz!=null) {
			return inOrderR(raiz);
		}
		return VACIO;
	}
	/**
	 * Metodo auxiliar recursivo que genera el recorrido inOrder
	 * @param node
	 * 		Nodo sobre el que se aplica el recorrido
	 * @return VACIO Constante si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	private String inOrderR(BSTNode<T> node) {
		if(node!=null) {
			return inOrderR(node.getLeft())+node.toString()+inOrderR(node.getRight());
		}else
			return VACIO;
	 }
	/**
	 * Metodo que muestra por pantalla el recorrido postOrder del arbol
	 * @return preOrdenR(raiz) Si el arbol existe
	 * 			VACIO Constante vacia si el arbol no existe
	 */
	public String postOrder() {
		if(raiz!=null) {
			return postOrderR(raiz);
		}
		return VACIO;
	}
	/**
	 * Metodo auxiliar recursivo que genera el recorrido postOrder
	 * @param node
	 * 		Nodo sobre el que se aplica el recorrido
	 * @return VACIO Constante si el recorrido no existe
	 * 			cadena con el recorrido si existe
	 */
	private String postOrderR(BSTNode<T> node) {
		 if(node!=null) {
				return postOrderR(node.getLeft())+postOrderR(node.getRight())+ node.toString();
			}else
				return VACIO;
	}
	
	/**
	 * Metodo que elimina el nodo cuya informacion se pase por parametro.
	 * @param info
	 * 			Informacion del nodo a borrar
	 * @return	0 Si borro correctamente
	 * 			-1 Si intenta borrar un nodo con una informacion que no existe
	 * 			-2 Si el arbol esta vacio
	 */
	public int removeNode(T info) {
		if(raiz==null) {
			return -2;
		}if(findNode(info)==null){
			return -1;
		}
		removeNodeR(this.raiz,info);
		return 0;
		
	}
	
	/**
	 * Metodo auxiliar recursivo para realizar el borrado de un nodo.
	 * @param node
	 * 		Nodo a borrar
	 * @param info
	 * 		Informacion del nodo a borrar
	 * @return
	 * 		node Nodo actualizado
	 * 		null Si el nodo a borrar no existe
	 */
	private BSTNode<T> removeNodeR(BSTNode<T> node,T info){
		if(node==null) {
			return null;
		}else if(info.compareTo(node.getInfo())==0) {
			if(node.getLeft()==null && node.getRight()==null) { //si es hoja
				return null;
			//solo tiene un hijo, se reasigna la referencia
			}else if(node.getLeft()==null) {
				return node.getRight();
			}else if(node.getRight()==null) {
				return node.getLeft();
			}else{
				//dos hijos
				node.setInfo(getMax(node.getLeft()));
				node.setLeft(removeNodeR(node.getLeft(),node.getInfo()));
			}
		}else if(info.compareTo(node.getInfo())<0) {
			node.setLeft(removeNodeR(node.getLeft(),info));
		}else
			node.setRight(removeNodeR(node.getRight(), info));
		return node;
	}
	
	/**
	 * Metodo auxiliar que obtiene el mayor nodo hijo existente en el subarbol izquierdo.
	 * @param node
	 * 		Nodo sobre el que aplicar la busqueda
	 * @return 
	 * 		node.getInfo() Informacion del mayor nodo del subarbol izquierdo
	 */
	public T getMax(BSTNode<T> node) {
		if(node==null) {
			return null;
		}else if(node.getRight()==null) {
			return node.getInfo();
		}else {
			return getMax(node.getRight());
		}
	}
	
	
	
	 //****************************************	EXTRA **************************************************/
	
	public T getMin(BSTNode<T> node) {
		if(node==null) {
			return null;
		}else if(node.getLeft()==null) {
			return node.getInfo();
		}else {
			return getMin(node.getLeft());
		}
	}
	
	public boolean arbolVacio() {
		return (raiz==null)? true: false;
	}
	
    public boolean isNodoHoja(BSTNode<T> node) {
    	if(node.getLeft()==null && node.getRight()==null) {
    		return true;
    	}
    	return false;
    }
    
    public boolean isNodoIntermedio(BSTNode<T> node){
    	if(!isNodoHoja(node) && node!=raiz) {
    		return true;
    	}
    	return false;
    }
    
    public String estadoNodo(T info) {
    	if(findNode(info)!=null) {
    		if(isNodoHoja(findNode(info))) {
        		return "HOJA";
        	}else if(isNodoIntermedio(findNode(info))) {
        		return "INTERMEDIO";
        	}else if(findNode(info)==raiz) {
        		return "RAIZ";
        	}
    	}
    	return "NO EXISTE EL NODO";
    	
    }
	
    public int getNumNodos() {
    	cont=0;
    	if(raiz==null) {
    		return 0;
    	}else {
    		return getNumNodosR(raiz);
    	}
    }

    private int getNumNodosR(BSTNode<T> node) {
        if (node != null) {
            cont++;
            getNumNodosR(node.getLeft());
            getNumNodosR(node.getRight());
            return cont;
        }
        return 0;
    }
  
    public int numNodosHoja() {
        cont = 0;
        if(raiz==null) {
    		return 0;
    	}else {
    		return numNodosHojaR(raiz);
    	}
    }
    
    private int numNodosHojaR(BSTNode<T> node) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                cont++;
            }
            numNodosHojaR(node.getLeft());
            numNodosHojaR(node.getRight());
            return cont;
        }
        return 0;
    }
    
    public int numNodosIntermedio() {
    	return getNumNodos() - numNodosHoja() -1;
    }
    
    public int getAltura() {
        altura = 0;
        if(raiz==null) {
        	return 0;
        }else {
        	return getAlturaR(raiz, 1);
        }
    }

    public int getAlturaR(BSTNode<T> node, int nivel) {
        if (node != null) {
        	getAlturaR(node.getLeft(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            getAlturaR(node.getRight(), nivel + 1);
            return altura;
        }
        return 0;
    }

      
}
