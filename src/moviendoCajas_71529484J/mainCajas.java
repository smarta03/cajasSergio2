package moviendoCajas_71529484J;

import java.util.ArrayList;
import java.util.Scanner;

public class mainCajas {

	private static ArrayList<ArrayList<String>> matrizTemp;
	private static ArrayList<ArrayList<ArrayList<String>>> solucionesParc;
	private static ArrayList<String> caminosFinalesComprobados;

	
	public static void main(String[] args) {
	
		matrizTemp = new ArrayList<ArrayList<String>>();
		caminosFinalesComprobados = new ArrayList<String>();
		solucionesParc = new ArrayList<ArrayList<ArrayList<String>>>();

		LeerEntradaHabitacion entrada = new LeerEntradaHabitacion();

		matrizTemp = entrada.leerEntradaHabitacion();

		if (matrizTemp != null) {
			
			FormarHabitacion resolverParciales = new FormarHabitacion(matrizTemp, caminosFinalesComprobados, solucionesParc);
			resolverParciales.formarHabitacion();

		} else {
			System.out.println("Entrada mal formada.");
		}
	}
}