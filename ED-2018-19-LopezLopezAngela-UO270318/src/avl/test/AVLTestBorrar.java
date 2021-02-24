package avl.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import avl.AVLTree;

class AVLTestBorrar {
	static AVLTree<Integer> b = new AVLTree<Integer>();
	@Test
	void test() {
		//Borra en un árbol vacío
		assertEquals(-2,b.removeNode(2));
		
		//Añade elementos
		assertEquals(0,b.addNode(10));
		assertEquals(0,b.addNode(100));
		//RDD
		assertEquals(0,b.addNode(60));
		assertEquals(0,b.addNode(30));
		assertEquals(0,b.addNode(2));
		//RSI
		assertEquals(0,b.addNode(1));
		assertEquals(0,b.addNode(70));
		//RDI
		assertEquals(0,b.addNode(90));
		assertEquals(0,b.addNode(23));
		assertEquals(0,b.addNode(43));
		//RSD
		assertEquals(0,b.addNode(65));
		assertEquals(0,b.addNode(13));
		assertEquals(0,b.addNode(230));
		//RDD
		assertEquals(0,b.addNode(110));
		assertEquals(0,b.addNode(49));
		assertEquals(0,b.addNode(7));
		assertEquals(0,b.addNode(40));
		//RSD
		assertEquals(0,b.addNode(50));
		//RDI
		assertEquals(0,b.addNode(20));
		//RSI
		assertEquals(0,b.addNode(15));
		assertEquals(0,b.addNode(3));
		
		//Añade un elemento que ya existe
		assertEquals(-1,b.addNode(3));
	}
	
	@Test
	public void test2() {
		//Borra un elemento que no existe
		assertEquals(-1,b.removeNode(500));
		
		//Borro una hoja
		assertEquals(0,b.removeNode(3));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());

		//Borra un elemento con un hijo
		assertEquals(0,b.removeNode(110));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=1\t230:FB=0\t",b.inOrder());
		//Borra un elemento con dos hijos
		assertEquals(0,b.removeNode(90));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",b.inOrder());	
		
		//Borra la raiz (30)
		assertEquals(0,b.removeNode(30));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=0\t13:FB=0\t15:FB=0\t20:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",b.inOrder());
		
		//Borro 1\t7\t13
		assertEquals(0,b.removeNode(1));
		assertEquals(0,b.removeNode(7));
		assertEquals(0,b.removeNode(13));
		assertEquals("2:FB=0\t10:FB=1\t15:FB=1\t20:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",b.inOrder());
		
		//Borro 20. RSD 2,0
		assertEquals(0,b.removeNode(20));
		
		
		
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t23:FB=1\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=-1\t65:FB=0\t70:FB=1\t100:FB=1\t230:FB=0\t",b.inOrder());
		
		//Borro el 230. RDI  -2, 1
		assertEquals(0,b.removeNode(230));
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t23:FB=-1\t40:FB=0\t43:FB=0\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",b.inOrder());
		
		//Borro 23
		assertEquals(0,b.removeNode(23));
		assertEquals("2:FB=0\t10:FB=-1\t15:FB=-1\t40:FB=0\t43:FB=0\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",b.inOrder());
		
		//Borro 43 (raiz) y RSI sobre el nodo 15
		assertEquals(0,b.removeNode(43));
		assertEquals("2:FB=0\t10:FB=0\t15:FB=0\t40:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=0\t100:FB=0\t",b.inOrder());
		
	}

}
