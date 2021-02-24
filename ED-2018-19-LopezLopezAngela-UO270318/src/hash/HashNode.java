package hash;


public class HashNode <T>{
	private T info; //contenido del elemento de la tabla
	private int estado; //borrado,vacio o lleno
	
	public static final int BORRADO = -1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;
	
	/**
	 * Constructor que inicializa a null la informacion y asigna el estado por defecto
	 * a la constante VACIO.
	 */
	public HashNode() {
		this.info = null;
		this.estado = VACIO;
	}
	/**
	 * Metodo que obtiene la informacion del nodo
	 * @return info Informacion del nodo
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Metodo que asigna un valor nuevo a la informacion del nodo
	 * @param elemento
	 * 		Nuevo valor a asignar
	 */
	public void setInfo(T elemento) {
		//si le asigno un valor se cambia el estado a lleno
		this.info = elemento;
		this.estado = LLENO;
	}
	
	/**
	 * Metodo que cambia el estado del nodo a la constante BORRADO
	 */
	public void remove() {
		this.estado = BORRADO;
	}
	/**
	 * Metodo que devuelve el estado del nodo
	 * @return estado Estado del nodo
	 */
	public int getStatus() {
		return this.estado;
	}
	public String toString() {
		StringBuilder cadena= new StringBuilder("{");
		switch(getStatus()) {
			case LLENO:
				cadena.append(info.toString());
				break;
			case VACIO:
				cadena.append("_E_");
				break;
			case BORRADO:
				cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}
}
