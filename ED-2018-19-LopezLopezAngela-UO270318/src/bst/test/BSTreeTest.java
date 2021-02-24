package bst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bst.BSTree;

class BSTreeTest {


	@Test
	void test() {
		BSTree<Integer> tree = new BSTree<Integer>();
		//Arbol vacio
		assertNull(tree.findNode(3));
		assertEquals(-2,tree.removeNode(3));
		assertEquals(0, tree.getAltura());
		
		//Insercion nodos
		assertEquals(0,tree.addNode(6));
		assertEquals(0,tree.addNode(4));
		assertEquals(0,tree.addNode(5));
		assertEquals(0,tree.addNode(12));
		assertEquals(0,tree.addNode(3));
		assertEquals(0,tree.addNode(9));
		assertEquals(0,tree.addNode(11));
		assertEquals(0,tree.addNode(8));
		assertEquals(0,tree.addNode(15));
		assertEquals(0,tree.addNode(30));
		//Error al insertar nodos
		assertEquals(-1,tree.addNode(6));
		assertEquals(-1,tree.addNode(8));
		//Buscar
		assertNull(tree.findNode(10));
		//Borrado nodos
		assertEquals(0,tree.removeNode(30));
		assertEquals(-1,tree.removeNode(30));
		
		//Recorridos
		System.out.println("PREORDER:\t" + tree.preOrder());
		assertEquals("6\t4\t3\t5\t12\t9\t8\t11\t15\t", tree.preOrder());
		System.out.println("INORDER:\t"+ tree.inOrder());
		assertEquals("3\t4\t5\t6\t8\t9\t11\t12\t15\t", tree.inOrder());
		System.out.println("POSTORDER:\t" + tree.postOrder());
		assertEquals("3\t5\t4\t8\t11\t9\t15\t12\t6\t", tree.postOrder());
		
		//Extra
		assertFalse(tree.arbolVacio());
		assertEquals(9, tree.getNumNodos());
		assertEquals(9, tree.getNumNodos());
		assertEquals(5, tree.numNodosHoja());
		assertEquals(tree.findNode(15).getInfo(), tree.getMax(tree.findNode(6)));
		assertEquals(tree.findNode(3).getInfo(),tree.getMin(tree.findNode(6)));
		
		assertEquals(4, tree.getAltura());
		
		assertFalse(tree.isNodoHoja(tree.findNode(4)));
		assertTrue(tree.isNodoHoja(tree.findNode(8)));
		
		assertTrue(tree.isNodoIntermedio(tree.findNode(12)));
		assertTrue(tree.isNodoIntermedio(tree.findNode(9)));
		assertFalse(tree.isNodoIntermedio(tree.findNode(6))); //raiz
		assertFalse(tree.isNodoIntermedio(tree.findNode(15))); //hoja
		
		assertEquals(3,tree.numNodosIntermedio());
		
		assertEquals("RAIZ",tree.estadoNodo(6));
		assertEquals("NO EXISTE EL NODO",tree.estadoNodo(78));
		assertEquals("INTERMEDIO",tree.estadoNodo(12));
	}

	@Test
	void test2() {
		BSTree<Integer> a = new BSTree<Integer>();
		assertEquals(0,a.addNode(49));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(27));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(40));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(28));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(12));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(5));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(90));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(65));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(53));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(74));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(62));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(88));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.addNode(30));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(13, a.getNumNodos());

		assertEquals(0,a.removeNode(40));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.removeNode(65));
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.removeNode(49));
		assertEquals(5, a.getAltura());
		System.out.println("INORDER:\t"+ a.inOrder());
		assertEquals(0,a.removeNode(74));
		
		assertEquals(4, a.numNodosHoja());
		assertEquals(4, a.getAltura());
		assertEquals(4,a.numNodosIntermedio());
		
	}

}
