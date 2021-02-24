package hash;

import java.lang.reflect.Array;


public class ClosedHashTable<T> extends AbstractHash<T>{

	protected HashNode<T>tabla[];
	private HashNode<T>aux[];
	protected int numElementos;
	private int tipoExploracion; // 1-lineal 2-cuadratica 3-dispersion doble
	private double minlf; //factor de carga minimo
	private double maxlf; //factor de carga maximo
	
	public static final double MINIMUN_LF = 0.16;
	public static final double MAXIMUN_LF = 0.5;
	public static final int INTENTOS = 10; //suficiente con las tablas que tratamos
	//para evitar recorrer toda la tabla asignamos 10 intentos
	
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo) {
		this.numElementos=0;
		this.tipoExploracion= tipo;
		this.minlf= MINIMUN_LF;
		this.maxlf= MAXIMUN_LF;

		if(!isPositivePrime(tam)) { //si no es primo 
			tam = nextPrimeNumber(tam); //pasa a valer el siguiente primo superior
		}
		this.tabla= (HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		for(int i=0; i<tam; i++) {
			tabla[i]= new HashNode<T>(); //para no tener tabla con nulls
			//para tener tabla con info y estado
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo, double minlf, double maxlf) {
		this.numElementos=0;
		this.tipoExploracion= tipo;
		this.minlf= minlf;
		this.maxlf= maxlf;

		if(!isPositivePrime(tam)) { //si no es primo 
			tam = nextPrimeNumber(tam); //pasa a valer el siguiente primo superior
		}
		this.tabla= (HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		for(int i=0; i<tam; i++) {
			tabla[i]= new HashNode<T>(); 
		}
	}
	
	/**
	 * Metodo que devuelve el numero de elementos de la tabla
	 */
	@Override
	public int getNumOfElements() {
		return this.numElementos;
	}

	/**
	 * Metodo que devuelve el tama�o total de la tabla
	 */
	@Override
	public int getSize() {
		return tabla.length;
	}

	/**
	 * Inserta el elemento que se le pasa como parametro
	 * @param element Elemento a insertar
	 * @return 	0 Si inserta el elemento correctamente
	 * 			-2 Si el elemento a insertar es null	
	 */
	@Override
	public int add(T element) {
		if(element==null) {
			return -2;
		}else{
			int pos= fHash(element); //se calcula la primera posicion que pertenece
			if(tabla[pos].getStatus()==HashNode.LLENO) { 
				int numIntentos= 1;
				while(numIntentos < INTENTOS && tabla[pos].getStatus()==HashNode.LLENO){
					pos = fHashClose(element,numIntentos);
					numIntentos++;
				}	
			}
			//si !lleno se inserta sin mas
			tabla[pos].setInfo(element); 
			++numElementos;
			if(getLF()>maxlf)
				reDispersion();
		}
		return 0;
	}

	/**
	 * Busca un elemento en la tabla que coincida con el que se pasa como parametro.
	 * @param element Elemento a buscar
	 * @return	elemento Si lo encuentra devuelve el propio elemento
	 * 			null Si no encuentra el elemento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T find(T element) {
		int pos= fHash(element);
		for(int i=1; i<INTENTOS;i++) {
			if(tabla[pos].getStatus()==HashNode.LLENO && tabla[pos].getInfo().equals(element))
				return(T) tabla[pos];	
			else
				pos = fHashClose(element,i);		
		}
		return null;
	}
		

	/**
	 * Elimina un elemento de la tabla hash
	 * @param element Elemento a borrar
	 * @return 0 Si borra el elemento
	 * 			-1 Si no existe
	 * 			-2 En cualquier otro caso
	 */
	@Override
	public int remove(T element) {
		//como admite duplicados, se borra el primero que se encuentra
		if(element==null) {
			return -2;
		}else if(findPosition(element)==-1) {
			return -1;
		}
		tabla[findPosition(element)].remove();
		--numElementos;
		if(getLF()<minlf) 
			inverseRedispersion();
		return 0;
}

	@Override
	public String toString() {
		StringBuilder cadena= new StringBuilder();
		for(int i=0; i<getSize();i++) {
			cadena.append(tabla[i].toString());
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElements());
		cadena.append("]");
		return cadena.toString();
	}
	
	/**
	 * Metodo que busca la posicion en la tabla en la que pueda ubicarse el nodo
	 * @param info
	 * 		Informacion del nodo a insertar
	 * @param intentos
	 * 		Numero de veces que se intenta insertar
	 * @return
	 * 		(pos + intentos) % getSize() si el tipo de exploracion es lineal
	 * 		(pos + intentos * intentos) % getSize() si el tipo de exploracion es cuadratica
	 * 		(pos + intentos *(R -pos % R)) % getSize() si el tipo de exploracion es dispersion doble
	 */
	public int fHashClose(T info, int intentos) {
		int pos=fHash(info);
		if(tipoExploracion==1) //lineal
			return (pos + intentos)% getSize();
		else if(tipoExploracion ==2) //cuadratica
			return (pos + intentos * intentos) %getSize();
		else if(tipoExploracion ==3) { //dispersion doble
			int R = previousPrimeNumber(getSize());
			return (pos + intentos *(R - pos % R)) % getSize();
		}
	return 0;
	}
	
	/**
	 * Metodo que aplica redispersion a la tabla cuando su factor de carga es superior al indicado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void reDispersion() {
		int num = isPositivePrime(getSize()*2)? getSize()*2 : nextPrimeNumber(getSize()*2);
		aux = tabla;
		tabla = (HashNode<T>[]) Array.newInstance(HashNode.class, num); 
		for(int i=0; i<tabla.length; i++) {
			tabla[i]= new HashNode<T>();
		}
		this.numElementos=0;
		for(int i=0;i<aux.length;i++) {
			if(aux[i].getStatus()==HashNode.LLENO) {
				add(aux[i].getInfo());
			}
		}
	}
	
	/**
	 * Metodo que aplica la redispersion inversa a la tabla cuando su factor de carga es inferior al indicado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void inverseRedispersion() {
		int num = isPositivePrime(getSize()/2)? getSize()/2 : previousPrimeNumber(getSize()/2);
		aux= tabla.clone();
			tabla= (HashNode<T>[]) Array.newInstance(HashNode.class, num);
			for(int i=0; i<num; i++) {
				tabla[i]= new HashNode<T>(); 
			}
			this.numElementos=0;
		for(int i=0;i<aux.length;i++) {
			if(aux[i].getStatus()==HashNode.LLENO) {
				add(aux[i].getInfo());
			}
		}	
	}

	/**
	 * Metodo auxiliar que devuelve el factor de carga de la tabla
	 * @return factor de carga
	 */
	public double getLF() {
		return (double)getNumOfElements() / (double)getSize();
	}
	
	/**
	 * Metodo auxiliar que encuentra la posicion en la que se encuentra un determinado elemento 
	 * que se le pasa como parametro.
	 * @param element Elemento a buscar
	 * @return pos Posicion donde se encuentra el elemento
	 * 			-1 si no encuentra el elemento en la tabla
	 */
	public int findPosition(T element){
		int pos= fHash(element);
		for(int i=1; i<INTENTOS;i++) {
			if(tabla[pos].getStatus()==HashNode.LLENO && tabla[pos].getInfo().equals(element))
				return pos;	
			else
				pos = fHashClose(element,i);		
		}
		return -1;
	}
	
	/**
	 * Metodo auxiliar que muestra por pantalla una cadena de caracteres con el nombre
	 * del estado
	 * @param i elemento a buscar
	 * @return BORRADO si su estado es -1
	 * 			VACIO si su estado es 0
	 * 			LLENO si su estado es 1
	 * 			null en cualquier otra ocasion
	 */
	public String printStatus(int i) {
		int estado= tabla[i].getStatus();
		if(estado == -1) {
			return "BORRADO";
		}else if(estado== 0) {
			return "VACIO";
		}else if(estado== 1) {
			return "LLENO";
		}
		return null;
	}
}
