package bst.test;

import static org.junit.Assert.*;

import org.junit.Test;

import bst.BSTree;

// Test de prueba si el método removeNode devuelve
// la raiz

public class ArbolesBinariosTest{

	@Test
	public void test() {
		BSTree<Integer> arbol = new BSTree<Integer>();
		
		// Intenta borrar de un árbol vacío
		assertEquals(-2,arbol.removeNode(50));
		
		// Busca en un árbol vácio
		assertNull(arbol.findNode(50));
		
		// Añade los siguiente valores
		// 50, 25, 75, 18, 28, 100, 32, 80, 30, 40, 29, 31, 90, 95 
		assertEquals(0,arbol.addNode(50));
		assertEquals(0,arbol.addNode(25));
		assertEquals(0,arbol.addNode(75));
		assertEquals(0,arbol.addNode(18));
		assertEquals(0,arbol.addNode(28));
		assertEquals(0,arbol.addNode(100));
		assertEquals(0,arbol.addNode(32));
		assertEquals(0,arbol.addNode(80));
		assertEquals(0,arbol.addNode(30));
		assertEquals(0,arbol.addNode(40));
		assertEquals(0,arbol.addNode(29));
		assertEquals(0,arbol.addNode(31));
		assertEquals(0,arbol.addNode(90));
		assertEquals(0,arbol.addNode(95));
		assertEquals(0,arbol.addNode(10));
		
		//Añade uno que ya existe y tiene sólo hijo derecho
		assertEquals(-1,arbol.addNode(80));
		
		//Añade uno que ya existe y tiene sólo hijo izquierdo
		assertEquals(-1,arbol.addNode(18));
		
		//Añade uno que ya existe y no tiene hijos
		assertEquals(-1,arbol.addNode(29));
		
		//Añade uno que ya existe y tiene dos hijos
		assertEquals(-1,arbol.addNode(32));
		
		//Añade uno que ya existe y es la raiz
		assertEquals(-1,arbol.addNode(50));
		
		
		// Busca un nodo que no existe
		assertNull(arbol.findNode(500));
		
		// Recorridos
		System.out.println("Arbol en recorrido InOrden\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t29\t30\t31\t32\t40\t50\t75\t80\t90\t95\t100\t",arbol.inOrder());

		System.out.println("Arbol en recorrido PreOrden\n"+arbol.preOrder()+"\n");
		assertEquals("50\t25\t18\t10\t28\t32\t30\t29\t31\t40\t75\t100\t80\t90\t95\t",arbol.preOrder());
		
		System.out.println("Arbol en recorrido PostOrden\n"+arbol.postOrder()+"\n");
		assertEquals("10\t18\t29\t31\t30\t40\t32\t28\t25\t95\t90\t80\t100\t75\t50\t",arbol.postOrder());
	
	
		// Borra un nodo con dos hijos: 32 tiene como hijos el 30 y el 40
		// Busca el mayor del subarbol izquierdo (de los menores) --> 31
		assertEquals(0,arbol.removeNode(32));
		System.out.println("Arbol en recorrido InOrden despues de borra el 32 con dos hijos\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t29\t30\t31\t40\t50\t75\t80\t90\t95\t100\t",arbol.inOrder());


		// Borra un nodo con un hijo: 100 tiene como hijo izquierdo al 80
		assertEquals(0,arbol.removeNode(100));
		System.out.println("Arbol en recorrido InOrden despues de borra el 100 con un hijo izquierdo\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t29\t30\t31\t40\t50\t75\t80\t90\t95\t",arbol.inOrder());
		
		// Borra un nodo con un hijo: 75 tiene como hijo izquierdo al 80
		assertEquals(0,arbol.removeNode(75));
		System.out.println("Arbol en recorrido InOrden despues de borra el 95 con un hijo derecho\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t29\t30\t31\t40\t50\t80\t90\t95\t",arbol.inOrder());
		
		// Borra una hoja: 29
		assertEquals(0,arbol.removeNode(29));
		System.out.println("Arbol en recorrido InOrden despues de borra el 29 que es una hoja\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t30\t31\t40\t50\t80\t90\t95\t",arbol.inOrder());
			
		// Borra la raiz: 50 con dos subarboles
		// Busca el mayor del subarbol izquierdo (de los menores) --> 40
		assertEquals(0,arbol.removeNode(50));
		System.out.println("Arbol en recorrido InOrden despues de borra ela raiz con dos subárboles\n"+arbol.inOrder()+"\n");
		assertEquals("10\t18\t25\t28\t30\t31\t40\t80\t90\t95\t",arbol.inOrder());
			
		//Borra un nodo con un hijo: 18 tiene como hijo izquierdo el 10
		assertEquals(0,arbol.removeNode(18));
		System.out.println("Arbol en recorrido InOrden despues de borra ela raiz con dos subárboles\n"+arbol.inOrder()+"\n");
		assertEquals("10\t25\t28\t30\t31\t40\t80\t90\t95\t",arbol.inOrder());
		
		//Borra un nodo que ya no existe
		assertEquals(-1,arbol.removeNode(50));
	}

}
