package algorithm;


/**
 * Clase que contiene los algoritmos que se van a utilizar.
 * @author angela
 *
 */
public class Algorithms {
	/**
	 * Constante para el metodo doNothing()
	 */
	private static final long SLEEP_TIME= 300;
	/**
	 * Metodo con complejidad n
	 * @param n 
	 * 		Valor de carga.
	 */
	public void linear1(int n) {
		for (int i=0; i<=n; i++) {
			System.out.println("Ejecutando..." + i);
		}
	}
	/**
	 * Metodo con complejidad n que usa el metodo doNothing()
	 * @param n 
	 * 		Valor de carga.
	 */
	public void linear2(int n) {
		for (int i=0; i<=n; i++) {
			doNothing();
		}
	}
	/**
	 * Metodo con complejidad n cuadratica.
	 * @param n 
	 * 		Valor de carga.
	 */
	public void cuadratica1(int n) {
		for (int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				System.out.println("Ejecutando..."+ i + " "+ j);
			}
		}
	}
	/**
	 * Metodo con complejidad n cuadratica que usa el metodo doNothing()
	 * @param n 
	 * 		Valor de carga.
	 */
	public void cuadratica2(int n) {
		for (int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				doNothing();
			}
		}
	}
	/**
	 * Metodo con complejidad n cubica.
	 * @param n 
	 * 		Valor de carga.
	 */
	public void cubic1(int n) {
		for (int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				for(int k=0; k<=n; k++) {
				System.out.println("Ejecutando..."+ i + " "+ j +" " +k);
				}
			}
		}
	}
	/**
	 * Metodo con complejidad n cubica que usa el metodo doNothing().
	 * @param n 
	 * 		Valor de carga.
	 */
	public void cubic2(int n) {
		for (int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				for(int k=0; k<=n; k++) {
				doNothing();
				}
			}
		}
	}
	/**
	 * 	Metodo con complejidad logaritmica n.
	 * @param n Valor de carga
	 */
	public void log1(int n) {
		for (int i = n; i > 0; i = i / 2){
		    System.out.println("Ejecutando " + i);
		}
	}
	/**
	 * 	Metodo con complejidad logaritmica n que usa el metodo doNothing(),
	 * @param n Valor de carga
	 */
	public void log2(int n) {
		for (int i = n; i > 0; i = i / 2){
		    doNothing();
		}
	}
	
	/**
	 * Metodo que para el sistema durante SLEEP_TIME ms.
	 */
	public void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para calcular potencias.
	 * @param n 
	 * 		Exponente
	 * @return 
	 * 		El resultado de elevar 2 al exponente
	 */
	public int pow2Iter(int n) {
        int pow = 1;
        for(int i=0;i<n; i++) {
        	pow=pow*2;
        }
        return pow;
    }
	/**
	 * Metodo para calcular el factorial de forma iterativa.
	 * @param n 
	 * 		Valor de carga.
	 * @return 
	 * 		El resultado de calcular el factorial de n.
	 */
	public int factorialIter(int n) {
		int fact=1;
		for(int i=1;i<=n; i++) {
			fact=fact*i;
		}
		return fact;
	}
	/**
	 * Metodo para calcular el factorial de forma recursiva.
	 * @param n 
	 * 		Valor de carga
	 * @return 
	 * 		El resultado de calcular el factorial de n, siendo este 1 en n==0 y n==1
	 */
	public int factorialRec(int n) {
		if(n==0||n==1) return 1;
		else return n*factorialIter(n-1);
	}
	/**
	 * Metodo para calcular fabonacci de forma recursiva.
	 * @param termino 
	 * 		Valor de carga.
	 * @return 
	 * 		El resultado de la sucesion de fibonacci del termino introducido.
	 */
	public int fibonacchiRec(int termino) {
		if(termino==0||termino==1) return 1;
		else return termino= fibonacchiRec(termino-1)+fibonacchiRec(termino-2);
	}
	
	/**
	 * Metodo para calcular pow2RecV1 de manera recursiva multiplicando 2 por pow2RecV1(n-1).
	 * @param n 
	 * 		Valor de carga.
	 * @return 
	 * 		El resultado de la potencia en base al exponente n.
	 */
	public long pow2RecV1(int n) {
		if(n==0) return 1;
		else return 2*pow2RecV1(n-1);
	}
	/**
	 * Metodo para calcuar pow2RecV2 de manera recursiva sumando 2 veces pow2RecV2(n-1).
	 * @param n 
	 * 		Valor de carga.
	 * @return 
	 * 		El resultado de la potencia en base al exponente n.
	 */
	public long pow2RecV2(int n) {
		if(n==0) return 1;
		else return pow2RecV2(n-1)+pow2RecV2(n-1);
	}
	/**
	 * Metodo para calcular pow2RecV3 de manera recursiva segun sea par o  impar en el que
	 * se reduce el numero de llamadas en cada iteracion al dividir entre 2 el exponente.
	 * @param n 
	 * 		Valor de carga.
	 * @return 
	 * 		El resultado de la potencia en base al exponente n.
	 */
	public long pow2RecV3(int n) {
		if(n==0) return 1;
		else if(n%2==0) return pow2RecV3(n/2) * pow2RecV3(n/2);
		else return pow2RecV3(n/2)* pow2RecV3(n/2)* 2;
	}
	
}
