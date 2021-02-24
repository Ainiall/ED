package graphs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graphs.Graph;


public class GraphDijkstraTest {

	@Test
	public void test() {
		System.out.println("Pruebas evaluaci�n Dijkstra");
		Graph<String> G=new Graph<String>(8);
		// Insertar nodos
		for (int i=0;i<8;i++){
			assertEquals(0,G.addNode("Nodo "+(char)('A'+i)));			
		}
		
		assertEquals(G.addEdge("Nodo A","Nodo C", 5),0);
		assertEquals(G.addEdge("Nodo A","Nodo E", 5),0);
		
		assertEquals(G.addEdge("Nodo B","Nodo A", 4),0);
		assertEquals(G.addEdge("Nodo B","Nodo F", 4),0);
		assertEquals(G.addEdge("Nodo B","Nodo G", 2),0);

		assertEquals(G.addEdge("Nodo C","Nodo B", 4),0);
		assertEquals(G.addEdge("Nodo C","Nodo E", 1),0);
		assertEquals(G.addEdge("Nodo C","Nodo G", 7),0);
		
		assertEquals(G.addEdge("Nodo D","Nodo F", 6),0);
		
		assertEquals(G.addEdge("Nodo F","Nodo H", 9),0);
		System.out.println(G.toString());
		
		// Dijkstra
		double w[] = G.dijkstra("Nodo A");
		System.out.print("Dijkstra - Nodo A ->  [");
		for (int i=0; i<w.length-1; i++) System.out.print(w[i]+",");
		System.out.println(w[w.length-1]+"]");
		assertEquals(w[0],Float.POSITIVE_INFINITY,0.0001);
		assertEquals(9.0,w[1],0.0001);
		assertEquals(5.0,w[2],0.0001);
		assertEquals(w[3],Float.POSITIVE_INFINITY,0.0001);
		assertEquals(5.0,w[4],0.0001);
		assertEquals(13.0,w[5],0.0001);
		assertEquals(11.0,w[6],0.0001);
		assertEquals(22.0,w[7],0.0001);
	}

}
