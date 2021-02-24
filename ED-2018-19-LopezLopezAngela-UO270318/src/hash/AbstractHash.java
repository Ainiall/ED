package hash;


public abstract class AbstractHash <T>{
	//PERMITE CLAVES REPETIDAS
	/**
	 * Devuelve el numero de elementos insertados hasta el momento en la tabla hash
	 * @return numero de elementos
	 */
	abstract public int getNumOfElements();
	
	/**
	 * Devuelve el tamaño total de la tabla
	 * @return size
	 */
	abstract public int getSize();

	/**
	 * Inserta el elemento que se le pasa como parametro
	 * @param element Elemento a insertar
	 * @return 	0 Si inserta el elemento correctamente
	 * 			-1 
	 * 			-2 Si el elemento a insertar es null	
	 */
	abstract public int add(T element);
	
	/**
	 * Busca un elemento en la tabla que coincida con el que se pasa como parametro.
	 * @param element Elemento a buscar
	 * @return	elemento Si lo encuentra
	 * 			null Si no encuentra el elemento
	 */
	abstract public T find(T element);
	
	/**
	 * Elimina un elemento de la tabla hash
	 * @param element Elemento a borrar
	 * @return 0 Si borra el elemento
	 * 			-1 Si no existe
	 * 			-2 En cualquier otro caso
	 */
	abstract public int remove(T element);
	
	/**
	 * Muestra el contenido de la tabla hash
	 */
	abstract public String toString();
	
	/**
	 * Metodo que calcula la primera posicion posible para un elemento dentro de la 
	 * tabla hash.
	 * @return
	 */
	protected int fHash(T info) {
		int tam = getSize();
		//evita posiciones negativas
		return (info.hashCode() % tam + tam) % tam;
		//para una tabla hash abierta este metodo seria suficiente, con tablas cerradas
		//es necesario recalcular posiciones
	}
	
	/**
	 * Metodo que comprueba si el numero es positivo
	 * @param numero
	 * @return	true si es positivo
	 * 			false si no lo es 
	 * //los negativos no son primos y no se devuelve un resultado inferior a 3
	 */
	protected boolean isPositivePrime(int numero) {
		if(numero <0 || (numero >2 && numero %2==0)) 
			return false;
			for(int i=3;i<numero;i+=2) 
				if(numero % i ==0) 
					return false; //no es primo
			return true;
	}
			
	
	/**
	 * Metodo que devuelve el siguiente numero primo existente a partir del numero
	 * pasado como parametro
	 * @param numero
	 * @return
	 */
	protected int nextPrimeNumber(int numero) {
		int num= numero +1;
		while(!isPositivePrime(num)) {
			num++;
		}
		return num;
	}
	/**
	 * Metodo que devuelve el numero primo previo al numero pasado como parametro
	 * @param numero
	 * @return si no hay numero primo anterior devuelve 3 o si mismo
	 */
	protected int previousPrimeNumber(int numero) {
		if(numero <=3)
			return 3;
		int num= numero -1;
		while(!isPositivePrime(num)) {
			num--;
		}	
		return num;
	}
	
	/**
	 * Metodo que aumenta la tabla cuando asi lo indica su factor de carga
	 */
	abstract protected void reDispersion();
	
	/**
	 * Metodo que disminuye el la tabla cuando asi lo indica su factor de carga
	 */
	abstract protected void inverseRedispersion();
}
