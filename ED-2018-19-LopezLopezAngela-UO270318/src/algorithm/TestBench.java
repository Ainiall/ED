package algorithm;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;


/**
 * Clase que contiene los metodos test que se van a ejecutar.
 * @author angela
 *
 */
public class TestBench {
	/**
	 * Metodo que escribe valores constantes en un fichero.
	 * @param output 
	 * 		Fichero de salida.
	 * @param startN 
	 * 		Valor de carga inicial.
	 * @param endN 
	 * 		Valor de carga final.
	 */
	public void test1(String output, int startN, int endN) {
		//Se crea el writer del archivo
		FileWriter file= null;
		PrintWriter pw;
		try {
			file = new FileWriter(output);
			pw= new PrintWriter(file);
			//Simulacion de una complejidad lineal
			for(int i=startN; i<endN; i++) {
				pw.println(i + ";" + i*10);
			}
			//Simulacion de una complejidad cuadratica
			for(int i=startN; i<endN; i++) {
				pw.println(i + ";" + i*i);
			}
			//Simulacion de una complejidad cubica
			for(int i=startN; i<endN; i++) {
				pw.println(i+";"+ i*i*i);
			}
			//Simulacion de una complejidad logaritmica 
			for(int i=startN; i<endN; i++) {
				pw.println(i + ";" + Math.log(i));
			}
			//Exponencial
			for(int i=startN; i<endN; i++) {
				pw.println(i + ";" + Math.exp(i));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(file!= null) {
				try {
					file.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	/**
	 *  Metodo que mide el tiempo que tarda un un algoritmo en ejecutarse para determinados valores de carga.
	 * @param output Fichero de salida.
	 * @param startN Valor de carga inicial.
	 * @param endN Valor de carga final.
	 */
	public void test2(String output, int startN, int endN) {
		FileWriter file= null;
		PrintWriter pw;
		double time_ini, time_fin, tiempo;
		Algorithms a= new Algorithms();
		try {
			file = new FileWriter(output);
			pw= new PrintWriter(file);
			
			for (int i=startN; i<= endN; i++) {
				time_ini= System.currentTimeMillis();
				//Se cambia el metodo a llamar segun lo que se quiera medir en ese instante
				a.log2(i);
				time_fin = System.currentTimeMillis();
				tiempo= time_fin - time_ini;
	
				pw.print(i + ";");
				pw.format(Locale.FRANCE, "%f%n", tiempo);
				}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(file!= null) {
				try {
					file.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 *  Metodo que mide el tiempo que tarda un algoritmo en ejecutarse para diferentes valores de carga.
	 *  Para cada valor de carga el algoritmo se repite times veces.
	 * @param output 
	 * 		Fichero de salida.
	 * @param startN 
	 * 		Valor de carga inicial.
	 * @param endN 
	 * 		Valor de carga final.
	 * @param times 
	 * 		Veces que se repite el algoritmo para un determinado valor de carga.
	 */
	public void test3(String output, int startN, int endN, int times) {
		FileWriter file= null;
		PrintWriter pw;
		double time_ini, time_fin, tiempo;
		Algorithms a= new Algorithms();
		try {
			file = new FileWriter(output);
			pw= new PrintWriter(file);
			//Repetir la ejecucion del metodo a probar times veces.
			for (int i=startN; i<= endN; i++) {
				time_ini= System.currentTimeMillis();
				for (int veces =1; veces<= times; veces++) {
					a.cubic2(i);
					time_fin = System.currentTimeMillis();
					tiempo= time_fin - time_ini;
					
					System.out.println("Ejecutando" + i);
					pw.print(i+";");
					pw.format(Locale.FRANCE, "%f%n", tiempo/ (double) times);
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(file!= null) {
				try {
					file.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Metodo que utiliza Java Reflection
	 * @param output
	 * 		Fichero de salida.
	 * @param startN
	 * 		Valor de carga inicial.
	 * @param endN
	 * 		Valor de carga final.
	 * @param times
	 * 		Veces que se repite el algoritmo para un determinado valor de carga.
	 * @param clase
	 * 		Nombre de la clase que contiene el metodo que va a ser invocado.
	 * @param metodo
	 * 		Nombre del  metodo que se va a invocar.
	 */
	public void test4(String output, int startN, int endN, int times, String clase, String metodo) {

		FileWriter file = null;
		PrintWriter pw;
		double time_ini, time_fin, tiempo;
		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			for (int i = startN; i <= endN; i++) {
				time_ini = System.currentTimeMillis();
				for (int veces = 1; veces <= times; veces++)
					testAlgorithm(clase, metodo,i);
				time_fin = System.currentTimeMillis();
				tiempo = time_fin - time_ini;
				System.out.println("Ejecutando " + i);
				pw.print(i + "; ");
				pw.format(Locale.FRANCE, "%f%n", tiempo / (double) times);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

	}
	/**
	 * @param className
	 *      Nombre de la clase que contiene un metodo que se quiere invocar.
	 * @param methodName 
	 * 		Nombre del metodo que se quiere invocar.
	 * @param n
	 *      Parametro que se le pasa al metodo invocado.
	 */
	@SuppressWarnings("deprecation")
	public void testAlgorithm(String className, String methodName, int n) {
		Class<?> clase;
		Object miclase;
		Method metodo;
		
		// Localiza la clase
		try {
			clase = Class.forName(className);
			// Crear una nueva instancia de la clase
			try {
				miclase = clase.newInstance();
				// Obtiene el metodo indicando y el tipo de parametro
				//param[0]=int.class;
				try {
					metodo = clase.getMethod(methodName, int.class);
					// Invocar al metodo
					try {
						metodo.invoke(miclase, new Integer(n));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestBench t = new TestBench();
		//t.test3("SalidaPow3.csv", 14, 30, 100);
		//t.testAlgorithm("Algorithms", "fsalida", 4);
		t.test4("Salida_test4.csv", 1, 20, 10, "Algorithms", "linear1");
	}

}
