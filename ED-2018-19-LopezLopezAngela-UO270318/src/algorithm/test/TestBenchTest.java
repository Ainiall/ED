package algorithm.test;


import org.junit.jupiter.api.Test;

import algorithm.TestBench;

/**
 * Pruebas unitarias con tests.
 * @author angela
 *
 */
class TestBenchTest {

	TestBench t= new TestBench();
	
	@Test
	void test() {
		//Test1
		//t.test1("salida.csv", 1, 20);
		
		//Test2
			t.test2("linear1_test2.csv", 1, 50);  
			t.test2("linear2_test2.csv", 1, 50); 
		
			t.test2("cuadratica1_test2.csv",1,50); 
			t.test2("cuadratica2_test2.csv", 1, 50);
			t.test2("cubic1_test2.csv", 1, 50); 
			t.test2("cubic2_test2.csv", 1, 10); 
			t.test2("log1_test2.csv",1,50); 
			t.test2("log2_test2.csv",1,50);
		
			t.test2("fibonacci.csv",1,50);

		
		//Test3
			t.test3("linear1_test3.csv", 1, 50, 20); 
			t.test3("linear2_test3.csv", 1, 10, 20);
			t.test3("cuadratica1_test3.csv",1,50,20); 
			t.test3("cuadratica2_test3.csv", 1, 10, 20); 
			t.test3("cubic1_test3.csv", 1, 50, 20); 
			t.test3("cubic2_test3.csv", 1, 10, 20);
			t.test3("log1_test3.csv", 1, 50, 20); 
			t.test3("log2_test3.csv", 1, 10, 20);  
		
			t.test3("pow2Iter_test3.csv", 1, 50, 20);  
			t.test3("pow2RecV1_test3.csv", 1, 50, 20); 
			t.test3("pow2RecV2_test3.csv", 1, 30, 20); 
			t.test3("pow2RecV3_test3.csv", 1, 50, 20);
		
			t.test3("factorialIter_test3.csv", 1, 50, 20); 
			t.test3("factorialRec_test3.csv", 1, 50, 20); 
			t.test3("fibonacchiRec_test3.csv",1,50,20); 
		
	}

}
