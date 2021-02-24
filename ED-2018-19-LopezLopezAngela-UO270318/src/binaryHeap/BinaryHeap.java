package binaryHeap;

public class BinaryHeap <T extends Comparable <T>> implements PriorityQueue<T> {
	protected T[] monticulo; //vector referencia del monticulo de minimos
	private int numElementos;

	
	/**
	 * Constructor que inicializa a 0 el numero de elementos
	 * @param n
	 * 		Numero de elementos
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n) {
		monticulo =(T[]) new Comparable[n];
		numElementos=0;
	}
	/*
	public BinaryHeap(int n,int tipo) {
		monticulo =(T[]) new Comparable[n];
		this.numElementos=0;
		this.tipo=tipo; //valor 1 si es de minimos y 2 si es de maximos
	}
	*/
	/**
	Metodo que inserta un elemento a la cola de prioridad.
	 * Se permite la existencia de claves repetidas.
	 * @param elemento
	 * 		Elemento a insertar en la cola de prioridad
	 * @return
	 * 		0 Si el elemento se inserta correctamente
	 * 		-1 Si intentamos insertar un elemento en una cola de prioridad llena
	 * 		-2 Si el elemento es null
	 */
	@Override
	public int add(T elemento) {
		if(elemento==null) {
			return -2;
		}else if(numElementos>=monticulo.length) {
			return -1;
		}else {
			monticulo[numElementos]=elemento;
			++numElementos;
			filtradoAscendente(findPosition(elemento));
		}
		return 0;
	}
	
	/**
	 * Metodo auxiliar que encuentra la posicion donde se encuentra un elemento
	 * pasado como parametro.
	 * @param elemento
	 * 		Elemento a encontrar
	 * @return
	 * 		i Posicion del vector donde se encuentra el elemento
	 * 		-1 Si el elemento no se encuentra
	 */
	public int findPosition(T elemento){
		for(int i=0; i<numElementos;i++) {
			if(monticulo[i].equals(elemento)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Metodo que devuelve la raiz (elemento mas peque�o) del monticulo y la elimina
	 * (metodo sacar de los apuntes)
	 * @return
	 * 		T elemento a sacar
	 */
	@Override
	public T getTop() {
		T elementoSacar= monticulo[0];
		int ultimaPos=0;
		for(int i=0;i<numElementos;i++) {
			ultimaPos=i;
		}	
		monticulo[0]=monticulo[ultimaPos];
		--numElementos;
		filtradoDescendente(0);
		return elementoSacar;
	}

	/**
	 * Metodo que elimina un elemento de la cola de prioridad
	 * @param elemento
	 * @return
	 * 		0 Si borra el elemento
	 * 		-1 Si intenta borrar algo que no existe no existe el elemento a borrar
	 * 		-2 Si el elemento a borrar es null ???
	 */
	@Override
	public int remove(T elemento) {
		if(this.numElementos==0) {
			return -2;
		}else if(elemento==null || findPosition(elemento)==-1 ) {
			return -1;
		}else {
			T aux= monticulo[findPosition(elemento)];
			monticulo[findPosition(elemento)]= monticulo[0];
			monticulo[0]= aux;
			getTop();
			
			return 0;
		}	
	}

	/**
	 * Metodo que devuelve true si el monticulo esta vacia o false si tiene algun elemento
	 * @return
	 * 		true Si el monticulo esta vacio
	 * 		false Si el monticulo tiene algun elemento
	 */
	@Override
	public boolean isEmpty() {
		for(int i=0;i<numElementos;i++) {
			if (monticulo[i]==null)  
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo que borra la cola de prioridad
	 */
	@Override
	public void clear() {
		for(int i=0;i<numElementos; i++) {
			monticulo[i]= null;
		}	
	}
	
	/**
	 * Metodo que implementa el filtrado ascendente
	 * @param pos
	 * 		Posicion del vector sobre la que se aplica el filtrado
	 */
	protected void filtradoAscendente(int pos) {
		T elemento= monticulo[pos];
		while(findPosition(elemento)!=0) {	//mientras no sea raiz
			int nuevaPos= findPosition(elemento);
			int padreDesdeHijoIzq = (nuevaPos-1)/2 <0 ? 0 : (nuevaPos-1)/2;
			int padreDesdeHijoDer = (nuevaPos-2)/2 <0 ? 0 : (nuevaPos-2)/2;
			//si monticulo es impar -> se compara con padre usando pos -1/2	
			if(nuevaPos%2!=0 && monticulo[nuevaPos].compareTo(monticulo[padreDesdeHijoIzq])<0) {
				T aux= monticulo[padreDesdeHijoIzq];
				monticulo[padreDesdeHijoIzq]= monticulo[nuevaPos];
				monticulo[nuevaPos]= aux;
				
				//si el elemento insertado es par -> se compara con padre usando (pos -2)/2
			}else if(nuevaPos%2==0 && monticulo[nuevaPos].compareTo(monticulo[padreDesdeHijoDer])<0) {
				T aux= monticulo[padreDesdeHijoDer];
				monticulo[padreDesdeHijoDer]= monticulo[nuevaPos];
				monticulo[nuevaPos]= aux;
			}else
				break;
		}
	}
	/**
	 * Metodo que implementa el filtrado descendente
	 * @param pos
	 * 		Posicion sobre la que se aplica inicialmente el filtrado descendente
	 */
	protected void filtradoDescendente(int pos) {
		T elemento= monticulo[0];
		while(numElementos/2>findPosition(elemento)) {//mientras no sea hoja
			int nuevaPos= findPosition(elemento);
			T hijoIzq = monticulo[2*nuevaPos +1];
			T hijoDer = monticulo[2*nuevaPos +2];
			int posHijo;
			if(2*nuevaPos +2>=numElementos) {
				posHijo= 2*nuevaPos +1; //si solo tiene hijo izq
			}else if(hijoIzq.compareTo(hijoDer)>0) {
				posHijo= 2*nuevaPos+2;
			}else
				posHijo= 2*nuevaPos +1;
			
			if(monticulo[nuevaPos].compareTo(monticulo[posHijo])>0) {
				T aux= monticulo[nuevaPos];
				monticulo[nuevaPos]= monticulo[posHijo];
				monticulo[posHijo]= aux;
			}
		}
	}
	
	/**
	 * Metodo que muestra la cola de prioridad
	 * @return str
	 * 			Cadena que contiene los distintos elementos del monticulo
	 */
	@Override
	public String toString() {
		String str="";
		for(int i=0; i<numElementos; i++) {
			if(monticulo[i]!=null && i==numElementos-1) {
				str+= monticulo[i].toString();
			}else if(monticulo[i]!=null){
				str+= monticulo[i].toString()+"\t";
			}
		}
		return str;	
	}
	
	//****************************************	EXTRA **************************************************/
	
	public T getMaxElement () {
		T maxValue = monticulo[0];
		for (int i = numElementos/2; i < numElementos; i++) {
			if(monticulo[i]==null) {
				return null;
			}else if (monticulo[i].compareTo(maxValue)>0) {
				maxValue = monticulo[i];
			}
		}
		return maxValue;		
	}
	
	//sustituir nodo por uno nuevo y cambiar filtrados segun nuevo valor
	public void changePriority(T element, T valor) {
		if(element!=null && valor!=null) {
			monticulo[findPosition(element)]=valor;
			if(valor.compareTo(element)<0){
				filtradoAscendente(findPosition(valor));
			}else
				filtradoDescendente(findPosition(valor));
		}
	}
}
