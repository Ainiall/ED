package hash.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hash.ClosedHashTable;

class HashTableTest {

	@Test
	void test() {
		//Ejercicio seminario
		ClosedHashTable<Integer> t= new ClosedHashTable<Integer>(23,1); 
		//insertar
		assertEquals(0,t.add(1));
		assertEquals(0,t.add(2));
		assertEquals(0,t.add(10));
		assertEquals(0,t.add(11));
		assertEquals(0,t.add(12));
		assertEquals(0,t.add(13));
		assertEquals(0,t.add(15));
		assertEquals(0,t.add(16));
		assertEquals(0,t.add(17));
		assertEquals(0,t.add(19));
		
		System.out.println(t.toString());
		System.out.println(t.printStatus(2));
		//assertEquals(0,t.add(2));
		//buscar
		
		//borrar
		assertEquals(0,t.remove(2));
		assertEquals(0,t.remove(13));
		assertEquals(0,t.remove(19));
		assertEquals(0,t.remove(16));
		assertEquals(0,t.remove(10));
		
		System.out.println("TRAS BORRAR 2,13,19,16,10\n"+t.toString());
		
		assertEquals(0,t.add(21));
		assertEquals(0,t.add(9));
		assertEquals(0,t.add(33));
		System.out.println("TRAS INSERTAR 21,9,33\n"+t.toString());
		
		assertEquals(0,t.remove(1));
		assertEquals(0,t.remove(33));
		assertEquals(0,t.remove(21));
		assertEquals(0,t.remove(9));
		assertEquals(0,t.remove(11));
		System.out.println("TRAS BORRAR 1,33,21,9,11\n"+t.toString());

		assertEquals(0,t.add(3));
		assertEquals(0,t.add(9));
		assertEquals(0,t.add(4));
		System.out.println("TRAS INSERTAR 3,9,4\n"+t.toString());
		
		assertEquals(0,t.remove(12));
		assertEquals(0,t.remove(17));
		assertEquals(0,t.remove(15));
		System.out.println("TRAS BORRAR 12,17,15\n"+t.toString()+"\n");
		
	}
	
	@Test
	void test2() {
		//Comprobacion ejercicio seminario
		ClosedHashTable<Integer> t= new ClosedHashTable<Integer>(5,2); 
		
		assertEquals(0,t.add(20));
		System.out.println(t.toString());
		assertEquals(0,t.add(40));
		System.out.println(t.toString());
		assertEquals(0,t.add(12));
		System.out.println(t.toString());
		assertEquals(0,t.add(4));
		System.out.println(t.toString());
		assertEquals(0,t.add(11));
		System.out.println(t.toString());
		assertEquals(0,t.add(22));
		System.out.println(t.toString());
		assertEquals(0,t.add(44));
		System.out.println(t.toString());
		assertEquals(0,t.add(34));
		System.out.println(t.toString() +"\n");
		
		assertEquals(0,t.remove(4));
		System.out.println(t.toString());
		assertEquals(0,t.remove(34));
		System.out.println(t.toString());
		assertEquals(0,t.remove(44));
		System.out.println(t.toString());
		assertEquals(0,t.remove(20));
		System.out.println(t.toString());
		assertEquals(0,t.remove(22));
		System.out.println(t.toString());
	}
}
