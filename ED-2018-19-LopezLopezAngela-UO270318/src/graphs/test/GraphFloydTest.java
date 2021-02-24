package graphs.test;
import static org.junit.Assert.*;

import org.junit.Test;

import graphs.Graph;

public class GraphFloydTest {

	@Test
	public void test() {
		System.out.println("Pruebas evaluacion Floyd");
		Graph<String> G = new Graph<String>(8);
		// Insertar nodos
		for (int i = 0; i < 8; i++) {
			assertEquals(0, G.addNode("Nodo " + (char) ('A' + i)));
		}

		assertEquals(G.addEdge("Nodo A", "Nodo B", 1), 0);
		assertEquals(G.addEdge("Nodo A", "Nodo E", 7), 0);
		assertEquals(G.addEdge("Nodo B", "Nodo A", 9), 0);
		assertEquals(G.addEdge("Nodo B", "Nodo C", 3), 0);
		assertEquals(G.addEdge("Nodo B", "Nodo F", 9), 0);
		assertEquals(G.addEdge("Nodo C", "Nodo G", 3), 0);
		assertEquals(G.addEdge("Nodo D", "Nodo F", 4), 0);
		assertEquals(G.addEdge("Nodo D", "Nodo H", 9), 0);
		assertEquals(G.addEdge("Nodo E", "Nodo G", 6), 0);
		assertEquals(G.addEdge("Nodo E", "Nodo H", 9), 0);
		assertEquals(G.addEdge("Nodo G", "Nodo E", 4), 0);
		assertEquals(G.addEdge("Nodo G", "Nodo H", 8), 0);
		assertEquals(G.addEdge("Nodo H", "Nodo F", 8), 0);
		System.out.println(G.toString());

		// Floyd
		G.floyd();
		System.out.println("FLOYD\n");
		double A[][] = G.getFloydA();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (A[i][j] == Float.POSITIVE_INFINITY)
					System.out.print("--\t");
				else
					System.out.print(A[i][j] + "\t");
			}
			System.out.println();
		}

		double salida[][] = {
				{ 0.0, 1.0, 4.0, Float.POSITIVE_INFINITY, 7.0, 10.0, 7.0, 15.0 },
				{ 9.0, 0.0, 3.0, Float.POSITIVE_INFINITY, 10.0, 9.0, 6.0, 14.0 },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0,
						Float.POSITIVE_INFINITY, 7.0, 19.0, 3.0, 11.0 },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, 0.0, Float.POSITIVE_INFINITY,
						4.0, Float.POSITIVE_INFINITY, 9.0 },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, 0.0, 17.0, 6.0, 9.0 },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, 0.0, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 4.0,
						16.0, 0.0, 8.0 },
				{ Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
						Float.POSITIVE_INFINITY, 8.0, Float.POSITIVE_INFINITY,
						0.0 } };
		assertArrayEquals(salida,A);
	}
}
	

