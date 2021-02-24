package algorithm.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import algorithm.Algorithms;


/**
 * Pruebas unitarias con algoritmos.
 * @author angela
 *
 */
class AlgorithmsTest {

	Algorithms a= new Algorithms();
	
	@Test
	void test() {
		//comprobacion metodo potencias pow2Iter
		assertEquals(a.pow2Iter(2),4);
		assertEquals(a.pow2Iter(0),1);
		assertEquals(a.pow2Iter(5),32);
		//comprobacion metodo factorial recursivo
		assertEquals(a.factorialRec(5),120);
		//comprobacion metodo fibonacci
		assertEquals(a.fibonacchiRec(5),8);
		//comprobacion pow2RecV1
		assertEquals(a.pow2RecV1(4),16);
		//comprobacion pow2RecV2
		assertEquals(a.pow2RecV1(3),8);
		//comprobacion pow2RecV3
		assertEquals(a.pow2RecV3(4),16);
	} 

}
