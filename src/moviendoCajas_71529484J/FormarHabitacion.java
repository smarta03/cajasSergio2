package moviendoCajas_71529484J;

import java.util.ArrayList;

public class FormarHabitacion {
	
	static ArrayList<ArrayList<String>> matrizTemp;
	static ArrayList<String> caminosFinalesComprobados;
	static ArrayList<ArrayList<ArrayList<String>>> solucionesParc;
	private static ArrayList<ArrayList<ArrayList<String>>> habitaciones;
	private static ArrayList<String> caminos;
	private static ArrayList<String> usada;
	private static ArrayList<ArrayList<Integer>> cajas;
	private static ArrayList<ArrayList<String>> habitacionTemp;
	private static ArrayList<Integer> robot;
	private static MoverRobot movRob;
	
	public FormarHabitacion(ArrayList<ArrayList<String>> matriz, ArrayList<String> caminosFinales,
			ArrayList<ArrayList<ArrayList<String>>> soluciones) {
		matrizTemp = matriz;
		caminosFinalesComprobados = caminosFinales;
		solucionesParc = soluciones;
		movRob = new MoverRobot();
	}


	public static void formarHabitacion() {
		
		caminos = new ArrayList<String>();
		habitaciones = new ArrayList<ArrayList<ArrayList<String>>>();
		usada = new ArrayList<String>();
		
		habitaciones.add(matrizTemp);
		caminos.add("");
		//0 es false y 1 true
		usada.add("0");
		lanzarCamino(habitaciones, caminos, usada);
		
		if(caminosFinalesComprobados.size()!=0) {
			System.out.println(caminosFinalesComprobados.get(0).length());
			System.out.println(caminosFinalesComprobados.get(0));
		}  else {
			System.out.println("No hay solución.");
		}

	}

	private static void lanzarCamino(ArrayList<ArrayList<ArrayList<String>>> habitaciones, ArrayList<String> caminos,
			ArrayList<String> usada) {

		int espacio = 0;
		for (int l = 0; l < habitaciones.size(); l++) {
			int tocaAhora = buscarHabitacionSiguiente(caminos, usada);
			ArrayList<ArrayList<String>> habitacionTemp = habitaciones.get(tocaAhora);
			String caminoActual = caminos.get(tocaAhora);
			usada.set(tocaAhora, "1");

			if (esSolucion(habitacionTemp)=='1') {
				solucionesParc.add(habitacionTemp);
				caminosFinalesComprobados.add(caminoActual);

				break;
			} else {
				if (espacio < caminoActual.length()) {
					espacio = caminoActual.length();
				}

				// Localizar cajas y caminos
				localizarCajasyCaminos(habitaciones, caminos, usada, habitacionTemp, caminoActual);
			}
		}
	}

	private static void localizarCajasyCaminos(ArrayList<ArrayList<ArrayList<String>>> habitaciones,
			ArrayList<String> caminos, ArrayList<String> usada, ArrayList<ArrayList<String>> habitacionTemp,
			String caminoActual) {
		cajas = buscarCajas(habitacionTemp);
		char cajaValida = cajaEnEsquina(habitacionTemp, cajas);
		if (cajaValida=='1') {
			
			ArrayList<ArrayList<ArrayList<String>>> habitacionesAux = new ArrayList<ArrayList<ArrayList<String>>>();
			ArrayList<String> caminosAux = new ArrayList<String>();
			for (int i = 0; i < cajas.size(); i++) {
				int filaC = cajas.get(i).get(0);
				int colC = cajas.get(i).get(1);

				
				int posicion = 0;
				habitacionesAux.add(habitacionTemp);
				caminosAux.add(caminoActual);
				if ((habitacionTemp.get(filaC).get(colC + 1).equals("-")
						|| habitacionTemp.get(filaC).get(colC + 1).equals("!")
						|| habitacionTemp.get(filaC).get(colC + 1).equals("@")
						|| habitacionTemp.get(filaC).get(colC + 1).equals("+"))
						&& (habitacionTemp.get(filaC).get(colC - 1).equals("-")
								|| habitacionTemp.get(filaC).get(colC - 1).equals("!")
								|| habitacionTemp.get(filaC).get(colC - 1).equals("@")
								|| habitacionTemp.get(filaC).get(colC - 1).equals("+"))) {
					
					libreDerecha(habitaciones, caminos, usada, habitacionesAux, caminosAux, filaC, colC);

				}
				habitacionesAux = new ArrayList<ArrayList<ArrayList<String>>>();
				caminosAux = new ArrayList<String>();
				habitacionesAux.add(habitacionTemp);
				caminosAux.add(caminoActual);
				posicion = 0;
				if ((habitacionTemp.get(filaC).get(colC - 1).equals("-")
						|| habitacionTemp.get(filaC).get(colC - 1).equals("!")
						|| habitacionTemp.get(filaC).get(colC - 1).equals("@")
						|| habitacionTemp.get(filaC).get(colC - 1).equals("+"))
						&& (habitacionTemp.get(filaC).get(colC + 1).equals("-")
								|| habitacionTemp.get(filaC).get(colC + 1).equals("!")
								|| habitacionTemp.get(filaC).get(colC + 1).equals("@")
								|| habitacionTemp.get(filaC).get(colC + 1).equals("+"))) {
					
					libreIzq(habitaciones, caminos, usada, habitacionesAux, caminosAux, filaC, colC);
				}
				habitacionesAux = new ArrayList<ArrayList<ArrayList<String>>>();
				caminosAux = new ArrayList<String>();
				habitacionesAux.add(habitacionTemp);
				caminosAux.add(caminoActual);
				posicion = 0;
				if ((habitacionTemp.get(filaC + 1).get(colC).equals("-")
						|| habitacionTemp.get(filaC + 1).get(colC).equals("!")
						|| habitacionTemp.get(filaC + 1).get(colC).equals("@")
						|| habitacionTemp.get(filaC + 1).get(colC).equals("+"))
						&& (habitacionTemp.get(filaC - 1).get(colC).equals("-")
								|| habitacionTemp.get(filaC - 1).get(colC).equals("!")
								|| habitacionTemp.get(filaC - 1).get(colC).equals("@")
								|| habitacionTemp.get(filaC - 1).get(colC).equals("+"))) {
					
					libreAbajo(habitaciones, caminos, usada, habitacionesAux, caminosAux, filaC, colC);
				}
				habitacionesAux = new ArrayList<ArrayList<ArrayList<String>>>();
				caminosAux = new ArrayList<String>();
				habitacionesAux.add(habitacionTemp);
				caminosAux.add(caminoActual);
				posicion = 0;
				if ((habitacionTemp.get(filaC - 1).get(colC).equals("-")
						|| habitacionTemp.get(filaC - 1).get(colC).equals("!")
						|| habitacionTemp.get(filaC - 1).get(colC).equals("@")
						|| habitacionTemp.get(filaC - 1).get(colC).equals("+"))
						&& (habitacionTemp.get(filaC + 1).get(colC).equals("-")
								|| habitacionTemp.get(filaC + 1).get(colC).equals("!")
								|| habitacionTemp.get(filaC + 1).get(colC).equals("@")
								|| habitacionTemp.get(filaC + 1).get(colC).equals("+"))) {
					
					libreArriba(habitaciones, caminos, usada, habitacionesAux, caminosAux, filaC, colC);
				}
				habitacionesAux = new ArrayList<ArrayList<ArrayList<String>>>();
				caminosAux = new ArrayList<String>();
			}
		}
	}

	private static void libreArriba(ArrayList<ArrayList<ArrayList<String>>> habitaciones, ArrayList<String> caminos,
			ArrayList<String> usada, ArrayList<ArrayList<ArrayList<String>>> habitacionesAux,
			ArrayList<String> caminosAux, int filaC, int colC) {
		int posicion;
		posicion = anchuraRecursivo(filaC, colC, -1, 0, habitacionesAux, caminosAux, 0);
		if (posicion != -1) {
			ArrayList<ArrayList<String>> habitacionPrincipal = empujaCaja(habitacionesAux.get(posicion),
					caminosAux.get(posicion), "b");

			if (habitacionIgual(habitaciones, habitacionPrincipal, caminosAux.get(posicion) + "B", caminos,
					usada)=='0') {
				habitaciones.add(habitacionPrincipal);
				caminos.add(caminosAux.get(posicion) + "B");
				usada.add("0");
			}
		}
	}

	private static void libreAbajo(ArrayList<ArrayList<ArrayList<String>>> habitaciones, ArrayList<String> caminos,
			ArrayList<String> usada, ArrayList<ArrayList<ArrayList<String>>> habitacionesAux,
			ArrayList<String> caminosAux, int filaC, int colC) {
		int posicion;
		posicion = anchuraRecursivo(filaC, colC, 1, 0, habitacionesAux, caminosAux, 0);
		if (posicion != -1) {
			ArrayList<ArrayList<String>> habitacionPrincipal = habitacionesAux.get(posicion);
			habitacionPrincipal = empujaCaja(habitacionPrincipal, caminosAux.get(posicion), "a");
			if (habitacionIgual(habitaciones, habitacionPrincipal, caminosAux.get(posicion) + "A", caminos,
					usada)=='0') {

				habitaciones.add(habitacionPrincipal);
				caminos.add(caminosAux.get(posicion) + "A");
				usada.add("0");
			}
		}
	}

	private static void libreIzq(ArrayList<ArrayList<ArrayList<String>>> habitaciones, ArrayList<String> caminos,
			ArrayList<String> usada, ArrayList<ArrayList<ArrayList<String>>> habitacionesAux,
			ArrayList<String> caminosAux, int filaC, int colC) {
		int posicion;
		posicion = anchuraRecursivo(filaC, colC, 0, -1, habitacionesAux, caminosAux, 0);
		if (posicion != -1) {
			ArrayList<ArrayList<String>> habitacionPrincipal = empujaCaja(habitacionesAux.get(posicion),
					caminosAux.get(posicion), "d");
			if (habitacionIgual(habitaciones, habitacionPrincipal, caminosAux.get(posicion) + "D", caminos,
					usada)=='0') {
				habitaciones.add(habitacionPrincipal);
				caminos.add(caminosAux.get(posicion) + "D");
				usada.add("0");
			}
		}
	}

	private static void libreDerecha(ArrayList<ArrayList<ArrayList<String>>> habitaciones, ArrayList<String> caminos,
			ArrayList<String> usada, ArrayList<ArrayList<ArrayList<String>>> habitacionesAux,
			ArrayList<String> caminosAux, int filaC, int colC) {
		int posicion;
		posicion = anchuraRecursivo(filaC, colC, 0, 1, habitacionesAux, caminosAux, 0);
		if (posicion != -1) {
			ArrayList<ArrayList<String>> habitacionPrincipal = empujaCaja(habitacionesAux.get(posicion),
					caminosAux.get(posicion), "i");
			if (habitacionIgual(habitaciones, habitacionPrincipal, caminosAux.get(posicion) + "I", caminos,
					usada)=='0') {
				habitaciones.add(habitacionPrincipal);
				caminos.add(caminosAux.get(posicion) + "I");
				usada.add("0");
			}
		}
	}

	private static char cajaEnEsquina(ArrayList<ArrayList<String>> caja, ArrayList<ArrayList<Integer>> cajas) {
	
		for (int i = 0; i < cajas.size(); i++) {
			int filaC = cajas.get(i).get(0);
			int colC = cajas.get(i).get(1);
			if (caja.get(filaC).get(colC).equals("#")) {
				if (caja.get(filaC + 1).get(colC).equals("1") || caja.get(filaC + 1).get(colC).equals("0")) {
					if (caja.get(filaC).get(colC + 1).equals("1") || caja.get(filaC).get(colC + 1).equals("0")) {
						return '0';
					} else if (caja.get(filaC).get(colC - 1).equals("1") || caja.get(filaC).get(colC - 1).equals("0")) {
						return '0';
					}
				}
				if (caja.get(filaC - 1).get(colC).equals("1") || caja.get(filaC - 1).get(colC).equals("0")) {
					if (caja.get(filaC).get(colC + 1).equals("1") || caja.get(filaC).get(colC + 1).equals("0")) {
						return '0';
					} else if (caja.get(filaC).get(colC - 1).equals("1") || caja.get(filaC + 1).get(colC - 1).equals("0")) {
						return '0';
					}
				}
			}
		}
		return '1';
	}

	private static char habitacionIgual(ArrayList<ArrayList<ArrayList<String>>> habitaciones,
			ArrayList<ArrayList<String>> habitacionPrincipal, String camino, ArrayList<String> caminos,
			ArrayList<String> usada) {
		for (int i = 0; i < habitaciones.size(); i++) {
			String resultado = "1";
			for (int j = 0; j < habitaciones.get(i).size(); j++) {
				for (int k = 0; k < habitaciones.get(i).get(j).size(); k++) {
					if (!habitaciones.get(i).get(j).get(k).equals(habitacionPrincipal.get(j).get(k))) {
						resultado = "0";
						break;
					}
				}
			}
			if (resultado=="1") {
				if (caminos.get(i).length() > camino.length()) {
					caminos.set(i, camino);
					usada.set(i, "0");
					// return false;�
					return '1';
				}
				return '1';
			}
		}
		return '0';
	}

	private static int buscarHabitacionSiguiente(ArrayList<String> caminos, ArrayList<String> usada) {
		int minimo = Integer.MAX_VALUE, posicion = -1;
		for (int i = 0; i < caminos.size(); i++) {
			if (minimo > caminos.get(i).length() && usada.get(i)=="0") {
				minimo = caminos.get(i).length();
				posicion = i;
			}
		}
		return posicion;
	}

	private static char esSolucion(ArrayList<ArrayList<String>> m) {
		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.get(i).size(); j++) {
				if (m.get(i).get(j).equals("#")) {
					return '0';
				}
			}
		}
		return '1';
	}

	private static ArrayList<ArrayList<Integer>> buscarCajas(ArrayList<ArrayList<String>> h) {
		ArrayList<ArrayList<Integer>> solucion = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < h.size(); i++) {
			for (int j = 0; j < h.get(i).size(); j++) {
				if (h.get(i).get(j).equals("#") || h.get(i).get(j).equals("*")) {
					ArrayList<Integer> s1 = new ArrayList<Integer>();
					s1.add(i);
					s1.add(j);
					solucion.add(s1);
				}
			}
		}
		return solucion;
	}

	private static int anchuraRecursivo(int filaC, int colC, int incF, int incC,
			ArrayList<ArrayList<ArrayList<String>>> habitacionesAux, ArrayList<String> caminosAux, int i) {
		if (habitacionesAux.size() == i) {
			return -1;
		} else if (habitacionesAux.get(i).get(filaC + incF).get(colC + incC).equals("@")
				|| habitacionesAux.get(i).get(filaC + incF).get(colC + incC).equals("+")) {
			return i;
		} else {
			ArrayList<ArrayList<String>> habitacionN = movRob.moverRobot(habitacionesAux.get(i), "d", false);
			if (habitacionN != null && habitacionesSonIguales(habitacionesAux, habitacionN)=='0') {
				habitacionesAux.add(habitacionN);
				caminosAux.add(caminosAux.get(i) + "d");
			}
			habitacionN = null;
			habitacionN = movRob.moverRobot(habitacionesAux.get(i), "i", false);
			if (habitacionN != null && habitacionesSonIguales(habitacionesAux, habitacionN)=='0') {
				habitacionesAux.add(habitacionN);
				caminosAux.add(caminosAux.get(i) + "i");
			}
			habitacionN = null;
			habitacionN = movRob.moverRobot(habitacionesAux.get(i), "a", false);
			if (habitacionN != null && habitacionesSonIguales(habitacionesAux, habitacionN)=='0') {
				habitacionesAux.add(habitacionN);
				caminosAux.add(caminosAux.get(i) + "a");
			}
			habitacionN = null;
			habitacionN = movRob.moverRobot(habitacionesAux.get(i), "b", false);
			if (habitacionN != null && habitacionesSonIguales(habitacionesAux, habitacionN)=='0') {
				habitacionesAux.add(habitacionN);
				caminosAux.add(caminosAux.get(i) + "b");
			}
			habitacionN = null;
			return anchuraRecursivo(filaC, colC, incF, incC, habitacionesAux, caminosAux, ++i);
		}
	}

	
	private static char habitacionesSonIguales(ArrayList<ArrayList<ArrayList<String>>> habitaciones,
			ArrayList<ArrayList<String>> habitacionNueva) {

		for (int i = 0; i < habitaciones.size(); i++) {
			String iguales = "1";
			for (int j = 0; j < habitaciones.get(i).size(); j++) {
				for (int k = 0; k < habitaciones.get(i).get(j).size(); k++) {
					if (!habitaciones.get(i).get(j).get(k).equals(habitacionNueva.get(j).get(k))) {
						iguales = "0";
					}
				}
			}
			if (iguales=="1") {

				return '1';
			}
		}
		return '0';
	}

	private static ArrayList<ArrayList<String>> empujaCaja(ArrayList<ArrayList<String>> habitacion, String camino,
			String mov) {
		ArrayList<Integer> posRobot = movRob.encuentraRobot(habitacion);
		// DCHA
		if (mov.equals("d") && (habitacion.get(posRobot.get(0)).get(posRobot.get(1) + 1).equals("#")
				|| habitacion.get(posRobot.get(0)).get(posRobot.get(1) + 1).equals("*"))) {
			habitacion = movRob.moverRobot(habitacion, "d", true);
			return habitacion;
		}
		if (mov.equals("i") && (habitacion.get(posRobot.get(0)).get(posRobot.get(1) - 1).equals("#")
				|| habitacion.get(posRobot.get(0)).get(posRobot.get(1) - 1).equals("*"))) {
			habitacion = movRob.moverRobot(habitacion, "i", true);
			return habitacion;
		}
		if (mov.equals("b") && (habitacion.get(posRobot.get(0) + 1).get(posRobot.get(1)).equals("#")
				|| habitacion.get(posRobot.get(0) + 1).get(posRobot.get(1)).equals("*"))) {
			habitacion = movRob.moverRobot(habitacion, "b", true);
			return habitacion;
		}
		if (mov.equals("a") && (habitacion.get(posRobot.get(0) - 1).get(posRobot.get(1)).equals("#")
				|| habitacion.get(posRobot.get(0) - 1).get(posRobot.get(1)).equals("*"))) {
			habitacion = movRob.moverRobot(habitacion, "a", true);
			return habitacion;
		}
		return null;
	}
}
