package bst;


public class BSTNode <T extends Comparable<T>>{
	//parametros
	private T info; //guarda la informacion del nodo
	private BSTNode<T> left;  //hijos izquierda
	private BSTNode<T> right; //hijos derecha
	
	public BSTNode(T info) {
		this.info = info;
		left = null;
		right = null;
	}
	
	public void setInfo(T info) {
		this.info=info;
	}
	
	public T getInfo() {
		return info;
	}

	public void setLeft(BSTNode<T> node){
		this.left=node;
	}
	
	public void setRight(BSTNode<T> node) {
		this.right=node;
	}
	
	public BSTNode<T> getLeft(){
		return left;
	}
	
	public BSTNode<T> getRight(){
		return right;
	}
	
	public String toString() {
		return info.toString()+"\t";
	}
}
