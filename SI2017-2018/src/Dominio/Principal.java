package Dominio;

import java.io.FileNotFoundException;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Principal {

	/*
	 * -- Metodo principal --
	 * 
	 * nos pide por teclado una serie de datos b�sicos para la creaci�n de nuestro
	 * tablero.
	 * 
	 * -- variables --
	 * 
	 * opcion = almacena el valor de la acci�n del men� principal que elijamos.
	 * filas y columnas = se recogen por teclado y en ellas se guardar� el valor de
	 * las filas y columnas que tendr� nuestro tablero. cas = es una matriz del tipo
	 * casilla que nos permite crear tantas casillas como dimension vaya a tener
	 * nuestro tablero. ter = es una variable tipo terreno a la que le daremos el
	 * valor de nuestro terreno cuando est� creado.
	 */

	public static void main(String[] args) throws FileNotFoundException {
		Stack pila = null;
		int opcion = 0;
		int filas = 0;
		int columnas = 0;
		int xt = 0;
		int yt = 0;
		int k = 0;
		int max = 0;
		Casilla[][] cas = null;
		Terreno ter = new Terreno();
		int[] tamFich;

		tamFich = ter.tama�oTerrenoFichero();
		cas = new Casilla[tamFich[0]][tamFich[1]];//
		ter = new Terreno(cas);
		ter.leerTerreno();// cargamos un fichero de prueba para que no de error.
		System.out.println("terreno actual:");
		ter.imprimirTerreno();

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(
					"--- MENU PRINCIPAL ---\n1. Crear un terreno.\n2. Lectura de un terreno.\n3. Escritura de un terreno.\n4. Generar acciones posibles.\n5. Realizar una accion. \n6. Salir"));
			switch (opcion) {
			case 1:
				xt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Xt"));
				yt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Yt"));
				k = Integer.parseInt(JOptionPane.showInputDialog("Introduzca K"));
				max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca MAX"));
				while (max < k) {
					max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca un valor mayor o igual que " + k));
				}
				filas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de filas:"));
				columnas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de columnas:"));

				// max=Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de
				// columnas:"));

				cas = new Casilla[filas][columnas];
				ter = new Terreno(cas, xt, yt, k, max, filas, columnas);
				ter.crearTerreno(max);
				ter.imprimirTerreno();
				break;
			case 2:
				tamFich = ter.tama�oTerrenoFichero();
				cas = new Casilla[tamFich[0]][tamFich[1]];
				ter = new Terreno(cas);
				ter.leerTerreno();
				System.out.println("terreno leido");

				ter.imprimirTerreno();
				break;
			case 3:
				ter.EscribirFichero();
				System.out.println("Terreno escrito");
				ter.imprimirTerreno();
				break;
			case 4:
				ter.MovimientosValidos();// aqui saca direcciones
				System.out.println("---------------");
				pila=ter.DistribuirCantidades();
				System.out.println("cantidad a distribuir: "+(ter.max()-ter.k())+"\n");
				while(!pila.isEmpty())
				{
					System.out.println(pila.pop());
				}
				/*ter.mostrarMovimientosPosibles();//aqui distribuye la cantidad*/
				break;
			case 5:
				ter.MovimientosValidos();
				pila=ter.DistribuirCantidades();
				
				int cont=0;
				while(cont <pila.size())
				{	
					System.out.println(pila.elementAt(cont));
					cont++; 
				}
				ter.generarAccion(pila);
				System.out.println("\nTerreno actual");
				ter.imprimirTerreno();
				break;
			}
		}

		while (opcion != 6);
	}
}