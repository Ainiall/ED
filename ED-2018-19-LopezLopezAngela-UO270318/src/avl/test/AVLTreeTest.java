package avl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import avl.AVLTree;

class AVLTreeTest {
	void test() {
	
		AVLTree<Integer> b = new AVLTree<Integer>();

		assertEquals(0,b.addNode(10));
		assertEquals("10:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(100));
		//RDD
		assertEquals(0,b.addNode(60));
		assertEquals("10:FB=0\t60:FB=0\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(30));
		assertEquals("10:FB=1\t30:FB=0\t60:FB=-1\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(2));
		assertEquals("2:FB=0\t10:FB=0\t30:FB=0\t60:FB=-1\t100:FB=0\t",b.inOrder());
		//RSI
		assertEquals(0,b.addNode(1));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=0\t30:FB=0\t60:FB=0\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(70));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t30:FB=0\t60:FB=1\t70:FB=0\t100:FB=-1\t",b.inOrder());
		//RDI
		assertEquals(0,b.addNode(90));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t30:FB=0\t60:FB=1\t70:FB=0\t90:FB=0\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(23));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t23:FB=0\t30:FB=-1\t60:FB=0\t70:FB=0\t90:FB=0\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(43));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t23:FB=0\t30:FB=0\t43:FB=0\t60:FB=0\t70:FB=0\t90:FB=0\t100:FB=0\t",b.inOrder());
		//RSD
		assertEquals(0,b.addNode(65));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=0\t23:FB=0\t30:FB=0\t43:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=-1\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(13));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=-1\t43:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=-1\t100:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(230));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=-1\t43:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=1\t230:FB=0\t",b.inOrder());
		//RDD
		assertEquals(0,b.addNode(110));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=-1\t43:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(49));
		assertEquals("1:FB=0\t2:FB=-1\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=0\t43:FB=1\t49:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(7));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=0\t43:FB=1\t49:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(40));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=0\t23:FB=-1\t30:FB=0\t40:FB=0\t43:FB=0\t49:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		//RSD
		assertEquals(0,b.addNode(50));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=0\t13:FB=0\t23:FB=-1\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		//RDI
		assertEquals(0,b.addNode(20));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=0\t13:FB=0\t20:FB=0\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=-1\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		//RSI
		assertEquals(0,b.addNode(15));
		assertEquals("1:FB=0\t2:FB=0\t7:FB=0\t10:FB=1\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(3));
		assertEquals("1:FB=0\t2:FB=1\t3:FB=0\t7:FB=-1\t10:FB=0\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());
		
		//Añade un elemento que ya existe
		assertEquals(-1,b.addNode(3));

		assertEquals("1:FB=0\t2:FB=1\t3:FB=0\t7:FB=-1\t10:FB=0\t13:FB=1\t15:FB=0\t20:FB=-1\t23:FB=0\t30:FB=0\t40:FB=0\t43:FB=1\t49:FB=1\t50:FB=0\t60:FB=0\t65:FB=0\t70:FB=-1\t90:FB=0\t100:FB=0\t110:FB=0\t230:FB=0\t",b.inOrder());

	}
	//Test recorridos y similares
	@org.junit.jupiter.api.Test
	void test2(){
	
		AVLTree<Integer> arbol = new AVLTree<Integer>();
	
		assertEquals(0,arbol.addNode(7));
		assertEquals(0,arbol.addNode(6));
		assertEquals(0,arbol.addNode(5));
		assertEquals(0,arbol.addNode(4));
		assertEquals(0,arbol.addNode(3));
		assertEquals(0,arbol.addNode(2));
		assertEquals(0,arbol.addNode(1));
		assertEquals(0,arbol.addNode(8));
		assertEquals(0,arbol.addNode(9));
		assertEquals(0,arbol.addNode(10));
		
		/*
		 * 					4
		 * 				  /	  \
		 * 				2	   8
		 * 			  /  \	  /	 \
		 * 			1	  3	 6	  9
		 * 					/ \	   \
		 * 				   5   7	10
		 */
		
		assertEquals("4:FB=1\t2:FB=0\t1:FB=0\t3:FB=0\t8:FB=0\t6:FB=0\t5:FB=0\t7:FB=0\t9:FB=1\t10:FB=0\t",arbol.preOrder());
		assertEquals("1:FB=0\t2:FB=0\t3:FB=0\t4:FB=1\t5:FB=0\t6:FB=0\t7:FB=0\t8:FB=0\t9:FB=1\t10:FB=0\t",arbol.inOrder());
		assertEquals("1:FB=0\t3:FB=0\t2:FB=0\t5:FB=0\t7:FB=0\t6:FB=0\t10:FB=0\t9:FB=1\t8:FB=0\t4:FB=1\t",arbol.postOrder());
		
		assertTrue(arbol.esPadreDe(4, 8));
		assertFalse(arbol.esPadreDe(4, 7));
		assertTrue(arbol.esPadreDe(4, 2));
		assertTrue(arbol.esPadreDe(2, 3));
		
		assertEquals(3,arbol.numAristas(4, 5));
		assertEquals(1,arbol.numAristas(6, 7));
		assertEquals(2,arbol.numAristas(8, 10));
		
		assertEquals("6:FB=0\t5:FB=0\t",arbol.camino(6, 5));
		assertEquals("4:FB=1\t8:FB=0\t9:FB=1\t10:FB=0\t",arbol.camino(4, 10) );
	}

	//Test borrar
	@org.junit.jupiter.api.Test
	void test3() {
		
		AVLTree<Integer> borrado = new AVLTree<Integer>();
		
		assertEquals(-2,borrado.removeNode(200));
		
		assertEquals(0,borrado.addNode(4));
		assertEquals(0,borrado.addNode(2));
		assertEquals(0,borrado.addNode(1));
		assertEquals(0,borrado.addNode(3));
		assertEquals(0,borrado.addNode(8));
		assertEquals(0,borrado.addNode(6));
		assertEquals(0,borrado.addNode(10));
		assertEquals(0,borrado.addNode(5));
		assertEquals(0,borrado.addNode(7));
		assertEquals(0,borrado.addNode(11));
		
		/*
		 * 					4
		 * 				  /	  \
		 * 				2	   8
		 * 			  /  \	  /	 \
		 * 			1	  3	 6	  10
		 * 					/ \	   \
		 * 				   5   7	11
		 */
		
		assertEquals(-1,borrado.removeNode(500));
	
		assertEquals(0,borrado.removeNode(1));
		assertEquals("2:FB=1\t3:FB=0\t4:FB=1\t5:FB=0\t6:FB=0\t7:FB=0\t8:FB=0\t10:FB=1\t11:FB=0\t",borrado.inOrder());
		//RSD
		assertEquals(0,borrado.removeNode(3));
		assertEquals("2:FB=0\t4:FB=1\t5:FB=0\t6:FB=0\t7:FB=0\t8:FB=-1\t10:FB=1\t11:FB=0\t",borrado.inOrder());
		assertEquals(0,borrado.removeNode(4));
		assertEquals("2:FB=1\t5:FB=0\t6:FB=-1\t7:FB=0\t8:FB=-1\t10:FB=1\t11:FB=0\t",borrado.inOrder());
		//RSI
		assertEquals(0,borrado.removeNode(7));
		assertEquals("2:FB=0\t5:FB=0\t6:FB=0\t8:FB=0\t10:FB=1\t11:FB=0\t",borrado.inOrder());
		
		assertEquals(0,borrado.removeNode(11));
		assertEquals("2:FB=0\t5:FB=0\t6:FB=0\t8:FB=-1\t10:FB=0\t",borrado.inOrder());
		
		//RSI
		assertEquals(0,borrado.removeNode(10));
		assertEquals("2:FB=0\t5:FB=1\t6:FB=0\t8:FB=-1\t",borrado.inOrder());
			
	}
	
	//Comprobacion ej seminario
	@org.junit.jupiter.api.Test
	void test4(){
		AVLTree<Integer> a= new AVLTree<Integer>();
		assertEquals(0,a.addNode(20));
		assertEquals(0,a.addNode(34));
		assertEquals(0,a.addNode(50));
			assertTrue(a.esPadreDe(34, 20));
		assertEquals(0,a.addNode(45));
			assertTrue(a.esPadreDe(34, 20));
			assertTrue(a.esPadreDe(50, 45));
		assertEquals(0,a.addNode(39));
			assertTrue(a.esPadreDe(34, 45));
			assertTrue(a.esPadreDe(45, 50));
		assertEquals(0,a.addNode(40));
			assertTrue(a.esPadreDe(39, 45));
			assertTrue(a.esPadreDe(34, 20));
		assertEquals(0,a.addNode(36));
		assertEquals(0,a.addNode(33));
		assertEquals(0,a.addNode(22));
			assertTrue(a.esPadreDe(22, 33));
		assertEquals(0,a.addNode(37));
		assertEquals(0,a.addNode(38));
		assertEquals(0,a.addNode(35));
		
		assertEquals(3, a.numAristas(37, 50));
		assertEquals(2, a.numAristas(34, 35));
		assertEquals("37:FB=0	39:FB=1	45:FB=0	40:FB=0	",a.camino(37, 40));
		
		assertEquals(4,a.getAlturaArbol());
		System.out.println(a.recorrido());
		System.out.println(a.datosArbol());
		System.out.println(a.getNumNodos());
		System.out.println(a.getMediaDatos());
		
		assertEquals(6,a.getNumNodesSubarbol(a.findNode(34)));
		assertEquals(5,a.getNumNodesSubarbol(a.findNode(39)));
		 
		AVLTree<Integer> b= new AVLTree<Integer>();
		b.addNode(35);
		b.addNode(45);
		b.addNode(22);
		b.addNode(50);
		b.addNode(39);
		b.addNode(33);
		
		b.addNode(82);
		b.addNode(4);
		
		System.out.println("NODOS EN COMUN");		
		System.out.println(a.interseccion2(a, b).recorrido());
		System.out.println("JUNTAR ARBOLES");
		System.out.println(a.merge(a, b).recorrido());;
		
	}
	
}
