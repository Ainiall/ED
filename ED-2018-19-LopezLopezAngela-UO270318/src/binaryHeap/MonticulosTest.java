package binaryHeap;

import static org.junit.Assert.*;

import org.junit.Test;


public class MonticulosTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		BinaryHeap<Integer> m= new BinaryHeap<Integer>(7);
	
		//Intenta borrar en un monticulo vacio
		assertEquals(-2,m.remove(5));
		assertEquals(0,m.add(2));
		assertEquals(0,m.add(4));
		assertEquals(0,m.add(12));
		assertEquals(0,m.add(5));
		assertEquals(0,m.add(6));
		assertEquals("2\t4\t12\t5\t6",m.toString());
		System.out.println(m.toString());
		
		//Borra un elemento que no existe
		assertEquals(-1,m.remove(40));
		
		
		assertEquals(0,m.add(8));
		assertEquals("2\t4\t8\t5\t6\t12",m.toString());
		System.out.println(m.toString());
		
		assertEquals(0,m.remove(4));
		assertEquals("2\t5\t8\t12\t6",m.toString());
		System.out.println(m.toString());
		
		//Inserto elementos
		assertEquals(0,m.add(1));
		assertEquals("1\t5\t2\t12\t6\t8",m.toString());
		System.out.println(m.toString());
		
		//Inserto elementos
		assertEquals(0,m.add(10));
		assertEquals("1\t5\t2\t12\t6\t8\t10",m.toString());
		System.out.println(m.toString());
		
		//Inserto y no cabe
		assertEquals(-1,m.add(20));
		assertEquals("1\t5\t2\t12\t6\t8\t10",m.toString());
		System.out.println(m.toString());
		
		//Borra la raiz
		assertEquals(new Integer(1),m.getTop());
		assertEquals("2\t5\t8\t12\t6\t10",m.toString());
		System.out.println(m.toString());
		
		
		//****************************************	EXTRA **************************************************/
		assertEquals(new Integer(12),m.getMaxElement());
		
		m.changePriority(12, 4);
		assertEquals(m.findPosition(4), 1);
		System.out.println("Monticulo tras cambiar la prioridad\n"+ m.toString());
		
		m.clear();
		System.out.println("Monticulo tras clear:\n"+m.toString());
		assertTrue(m.isEmpty());
		assertEquals(null,m.getMaxElement());
		
		
		
		
		
	}
	
}