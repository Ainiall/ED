package avl;

import bst.BSTNode;

public class AVLNode <T extends Comparable<T>> extends BSTNode<T>{
	private T info;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int factorBalance;
	private int altura;
	
	public AVLNode(T info){
		super(info);
		this.info=info;
		left= null;
		right= null;
		factorBalance=0;
		altura=0;
	}
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info= info;
	}
	public void setLeft(AVLNode <T> node) {
		this.left=node;
	}
	public void setRight(AVLNode <T> node) {
		this.right=node;
	}
	public AVLNode<T> getLeft(){
		return left;
	}
	public AVLNode<T> getRight(){
		return right;
	}
	public void setAltura(int a) {
		this.altura=a;
	}
	public int getAltura(){
		updateHeight();
		return altura;
	}
	public void setFB(int bf) {
		this.factorBalance=bf;
	}
	public int getFB() {
		updateFB();
		return factorBalance;
	}
	
	/*
	public void setFactorBalanceAltura() {			
		//FUNCIONA LLAMANDOLO EN ROTACIONES
		int altL,altR; 
		if (this.getLeft()==null ) 
			altL=-1; 
		else altL=this.getLeft().getAltura();
		if (this.getRight()==null)
			altR=-1; 
		else 
			altR=this.getRight().getAltura(); 	
		altura= Math.max(altL, altR)+1;
		
		factorBalance= altR-altL;
	} */
	
	public String toString() {
		return info.toString() + ":FB=" + getFB()+"\t";
	}
	public String print() {
		return info.toString()+"\t";
	}
	
	public void updateHeight() {
		int left = 0;
		int right = 0;
		if(this.getLeft()!=null)
		{
			left = this.getLeft().getAltura();
		}
		if(this.getRight()!=null)
		{
			right = this.getRight().getAltura();
		}
		altura=Math.max(left, right)+1;
	}
	public void updateFB() {
		int left = 0;
		int right = 0;
		if(this.getLeft()!=null)
		{
			left = this.getLeft().getAltura();
		}
		if(this.getRight()!=null)
		{
			right = this.getRight().getAltura();
		}
		factorBalance= right-left;
	}
}