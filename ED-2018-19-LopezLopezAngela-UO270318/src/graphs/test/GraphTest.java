package graphs.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graphs.Graph;

class GraphTest {
	//@Test(timeout=100) //el metodo falla si tarda mas de 100ms
	//@Before Metodo que se ejecuta antes de cada test
	//@After Metodo que se ejecuta despues de cada test
	//@BeforeClass Metodo que se ejecuta una sola vez al principio de todos los tests
	//@AfterClass Metodo que se ejecuta una sola vez al final de todos los tests
	
	//TEST CLASS GRAFOS LAB 03
	@Test
	void test1() {
		System.out.println("TEST GRAFOS LAB 03"+"\n"+"-------------------");
		Graph<String> G=new Graph<String>(4); //Oviedo, Gijon, Aviles, Mieres
		//crea los nodos
		assertEquals(0,G.addNode("OVIEDO")); 
		assertEquals(0,G.addNode("GIJON")); 
		assertEquals(0,G.addNode("AVILES")); 
		assertEquals(0,G.addNode("MIERES")); 
		
		//ya creados, no deja crearlos de nuevo
		assertEquals(-1,G.addNode("OVIEDO"));  
		assertEquals(-1,G.addNode("GIJON"));  
		
		//no hay sitio para nodo nuevo
		assertEquals(-1,G.addNode("NAVIA"));
		
		//getNodes existentes
		assertEquals(G.getNode("GIJON"),1);
		assertEquals(G.getNode("MIERES"),3);
		
		//getNodes que no existen
		assertEquals(G.getNode("NAVIA"),-1);
		assertEquals(G.getNode("LUARCA"),-1);
		
		//comprobacion sobre la existencia de nodos
		assertFalse(G.existNode("NAVIA"));
		assertTrue(G.existNode("OVIEDO"));
		
		//crea los ejes/aristas
		assertEquals(G.addEdge("OVIEDO", "GIJON", 4.0),0);
		assertEquals(G.addEdge("OVIEDO", "MIERES",1.0),0);
		assertEquals(G.addEdge("MIERES", "GIJON",2.0),0);
		assertEquals(G.addEdge("GIJON", "AVILES",1.0),0);
		assertEquals(G.addEdge("MIERES", "AVILES",4.0),0);
		
		//aristas que no se pueden crear
		assertEquals(G.addEdge("OVIEDO", "NAVIA",1.0),-1);
		assertEquals(G.addEdge("MIERES", "GIJON",5.0),-1);
		
		//comprobacion sobre la existencia de ejes
		assertFalse(G.existEdge("NAVIA","OVIEDO"));
		assertTrue(G.existEdge("OVIEDO","GIJON"));
		
		//getEdge existentes
		assertEquals(4,0,G.getEdge("OVIEDO", "GIJON"));
		assertEquals(1,0,G.getEdge("GIJON", "AVILES"));
		
		//getEdges que no existen
		assertEquals(-1,G.getEdge("GIJON", "NAVIA"),7.0);
		assertEquals(-1,G.getEdge("GIJON", "AVILES"),5);
		
		//borrar aristas
		assertEquals(0, G.removeEdge("MIERES", "GIJON"));
		
		//error borrar aristas que no existen
		assertEquals(-1, G.removeEdge("MIERES", "GIJON"));
		assertEquals(-1, G.removeEdge("OVIEDO", "AVILES"));
		assertEquals(-1, G.removeEdge("MIERES", "NAVIA"));
		
		//borrar nodo
		assertEquals(0, G.removeNode("AVILES"));
		
		//error borrar nodo que no existe
		assertEquals(-1, G.removeNode("AVILES"));
		assertEquals(-1, G.removeNode("LUARCA"));
		
	
		System.out.println(G.toString());

		//assertEquals(G.getNode(1),1.2,0.001); //da precision al metodo
	}
	// TEST CLASS GRAFOS LAB 04
	// TEST DEL METODO DIJKSTRA
	@Test
	void test2() {
		System.out.println("TEST GRAFOS LAB 04"+"\n"+"-------------------");
		Graph<String> G=new Graph<String>(4);
		//NODOS
		assertEquals(0,G.addNode("A"));
		assertEquals(0,G.addNode("B"));
		assertEquals(0,G.addNode("C"));
		assertEquals(0,G.addNode("D"));
		//ARISTAS
		assertEquals(G.addEdge("A", "B", 4.0),0);
		assertEquals(G.addEdge("A", "D", 1.0),0);
		assertEquals(G.addEdge("D", "B", 2.0),0);
		assertEquals(G.addEdge("D", "C", 4.0),0);
		assertEquals(G.addEdge("B", "C", 1.0),0);
		
		// Dijkstra
		double w[] = G.dijkstra("A");
		System.out.print("Dijkstra - Nodo A ->  [");
		for (int i=0; i<w.length-1; i++) System.out.print(w[i]+",");
		System.out.println(w[w.length-1]+"]");		
		assertEquals(Float.POSITIVE_INFINITY,w[0],0.0001);
		assertEquals(3.0,w[1],0.0001);
		assertEquals(4.0,w[2],0.0001);
		assertEquals(1.0,w[3],0.0001);
		
		// Dijkstra2 
				double w2[] = G.dijkstra2("A");
				System.out.print("Dijkstra2 (longitud minima) - Nodo A ->  [");
				for (int i=0; i<w2.length-1; i++) System.out.print(w2[i]+",");
				System.out.println(w2[w2.length-1]+"]");
			
		System.out.println(G.toString());
	}
	// TEST CLASS GRAFOS LAB 05
	// TEST DEL METODO FLOYD
	@Test
	void test3() {
		System.out.println("TEST GRAFOS LAB 05"+"\n"+"-------------------");
		Graph<Integer> G=new Graph<Integer>(5);
		//NODOS
		assertEquals(0,G.addNode(1));
		assertEquals(0,G.addNode(2));
		assertEquals(0,G.addNode(3));
		assertEquals(0,G.addNode(4));
		assertEquals(0,G.addNode(5));
		//ARISTAS
		assertEquals(G.addEdge(1, 2, 1.0),0);
		assertEquals(G.addEdge(1, 4, 3.0),0);
		assertEquals(G.addEdge(1, 5, 10.0),0);
		assertEquals(G.addEdge(2, 3, 5.0),0);
		assertEquals(G.addEdge(3, 5, 1.0),0);
		assertEquals(G.addEdge(4, 3, 2.0),0);
		assertEquals(G.addEdge(4, 5, 6.0),0);
		
		G.floyd();
		System.out.println("\nFLOYD - MATRIZ A-> ");
		final double[][] matrizA= G.getFloydA();
		for (int i = 0; i < matrizA.length; i++) {
		    for (int j = 0; j < matrizA[i].length; j++) {
		        System.out.print(matrizA[i][j] + "\t");
		    }
		    System.out.println();
		}
		System.out.println("\nFLOYD - MATRIZ P-> ");
		final int[][] matrizP= G.getFloydPF();
		for (int i = 0; i < matrizP.length; i++) {
		    for (int j = 0; j < matrizP[i].length; j++) {
		        System.out.print(matrizP[i][j] + "\t");
		    }
		    System.out.println();
		}
		
		System.out.println("\n"+ G.toString());

		assertEquals(1.0,G.minCostPath(3, 5),0);
		assertEquals(-1,G.minCostPath(6, 5),0);
		
		System.out.println(G.path(4, 3));
		System.out.println(G.path(1, 5));
		
		System.out.println("EXCENTRICIDAD");
		System.out.println(G.excentricidad(3));
		G.getNodoMasExcentrico();
		
				
	}
	// TEST CLASS GRAFOS LAB 05
	// TEST RECORRIDO EN PROFUNDIDAD
	@Test
	void test4() {
		System.out.println("\nTEST GRAFOS RECORRIDO EN PROFUNDIDAD 05"+"\n"+"-------------------");
		Graph<String> G=new Graph<String>(4);
		
		assertEquals(0,G.addNode("V1"));
		assertEquals(0,G.addNode("V2"));
		assertEquals(0,G.addNode("V3"));
		assertEquals(0,G.addNode("V4"));
		//ARISTAS
		assertEquals(G.addEdge("V1", "V2", 2.0),0);
		assertEquals(G.addEdge("V1", "V3", 1.0),0);
		assertEquals(G.addEdge("V2", "V4", 3.0),0);
		assertEquals(G.addEdge("V3", "V2", 4.0),0);
		assertEquals(G.addEdge("V4", "V3", 6.0),0);
		assertEquals(G.addEdge("V4", "V4", 5.0),0);
		
		System.out.println(G.recorridoProfundidad("V1"));
		System.out.println(G.recorridoProfundidad("V2"));
		
		G.gradoSalida("V1");
		System.out.println("GRADO ENTRADA "+G.gradoEntrada("V1"));
		System.out.println("GRADO SALIDA " +G.gradoSalida("V1"));
		assertTrue(G.esNodoFuente("V1"));
		assertFalse(G.esNodoSumidero("V1"));
		System.out.println("NODO INACCESIBLE v2");
		
		System.out.print("CAPACIDAD GRAFO ");
		G.getCapacidadGrafo(); 
		}
	//test seminario ej 7
	@Test
	void test7() {
		System.out.println("TEST SEMINARIO 3"+"\n"+"-------------------");
		Graph<String> G=new Graph<String>(8);
		//NODOS
		assertEquals(0,G.addNode("A"));
		assertEquals(0,G.addNode("B"));
		assertEquals(0,G.addNode("C"));
		assertEquals(0,G.addNode("D"));
		assertEquals(0,G.addNode("E"));
		assertEquals(0,G.addNode("F"));
		assertEquals(0,G.addNode("G"));
		assertEquals(0,G.addNode("H"));
		//ARISTAS
		assertEquals(G.addEdge("A", "D", 4.0),0);
		assertEquals(G.addEdge("B", "A", 7.0),0);
		assertEquals(G.addEdge("B", "C", 2.0),0);
		assertEquals(G.addEdge("B", "G", 4.0),0);
		assertEquals(G.addEdge("C", "B", 8.0),0);
		assertEquals(G.addEdge("C", "E", 2.0),0);
		assertEquals(G.addEdge("D", "F", 3.0),0);
		assertEquals(G.addEdge("E", "C", 7.0),0);
		assertEquals(G.addEdge("E", "G", 5.0),0);
		assertEquals(G.addEdge("E", "H", 4.0),0);
		assertEquals(G.addEdge("F", "D", 6.0),0);
		assertEquals(G.addEdge("F", "G", 4.0),0);
		assertEquals(G.addEdge("G", "E", 9.0),0);
		assertEquals(G.addEdge("G", "H", 2.0),0);
		assertEquals(G.addEdge("H", "F", 2.0),0);
		
		G.floyd();
		System.out.println("\nFLOYD - MATRIZ A-> ");
		final double[][] matrizA= G.getFloydA();
		for (int i = 0; i < matrizA.length; i++) {
		    for (int j = 0; j < matrizA[i].length; j++) {
		        System.out.print(matrizA[i][j] + "\t");
		    }
		    System.out.println();
		}
		System.out.println("\nFLOYD - MATRIZ P-> ");
		final int[][] matrizP= G.getFloydPF();
		for (int i = 0; i < matrizP.length; i++) {
		    for (int j = 0; j < matrizP[i].length; j++) {
		        System.out.print(matrizP[i][j] + "\t");
		    }
		    System.out.println();
		}
		
		System.out.println("\n"+ G.toString());
				
	}

		
}
