package binaryHeap;


public interface PriorityQueue <T extends Comparable<T>>{
	/**
	 * Metodo que inserta un elemento a la cola de prioridad.
	 * Se permite la existencia de claves repetidas.
	 * @param elemento
	 * 		Elemento a insertar en la cola de prioridad
	 * @return
	 * 		0 Si el elemento se inserta correctamente
	 * 		-1 Si intentamos insertar un elemento en una cola de prioridad llena
	 * 		-2 Si el elemento es null
	 */
	public int add(T elemento);
	/**
	 * Metodo que devuelve la raiz (elemento mas pequeño) del monticulo y la elimina
	 * (metodo sacar de los apuntes)
	 * @return
	 * 		T elemento a sacar
	 */
	public T getTop();
	/**
	 * Metodo que elimina un elemento de la cola de prioridad
	 * @param elemento
	 * @return
	 * 		0 Si borra el elemento
	 * 		-1 Si no existe el elemento a borrar
	 * 		-2 Si el elemento a borrar es null ???
	 */
	public int remove(T elemento);
	/**
	 * Metodo que devuelve true si el monticulo esta vacia o false si tiene algun elemento
	 * @return
	 * 		true Si el monticulo esta vacio
	 * 		false Si el monticulo tiene algun elemento
	 */
	public boolean isEmpty();
	/**
	 * Metodo que borra la cola de prioridad
	 */
	public void clear();
	
	/**
	 * Metodo que muestra la cola de prioridad
	 * @return
	 */
	public String toString();
}
