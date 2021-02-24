package avl.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import avl.AVLTree;


class ArbolesAVLAddTest {

	static AVLTree<Integer> b = new AVLTree<Integer>();
	
	@Test
	public void test1() {
		//Borra en un árbol vacío
		assertEquals(-2,b.removeNode(2));
		
		//Añade elementos
		assertEquals(0,b.addNode(10));
		assertEquals("10:FB=0\t",b.inOrder());
		assertEquals(0,b.addNode(100));
		assertEquals("10:FB=1\t100:FB=0\t",b.inOrder());
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

}
