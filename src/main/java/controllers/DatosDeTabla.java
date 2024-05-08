package controllers;

import java.util.List;

import entities.Provincia;

public class DatosDeTabla {

	
	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Code", "Label", "Parent_code"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla() {
		// Obtengo todas las personas
		List<Provincia> contratos = (List<Provincia>) ControladorProvincia.getInstance().getAllProvincias();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[contratos.size()][3];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < contratos.size(); i++) {
			Provincia contratos1 = contratos.get(i);
			datos[i][0] = contratos1.getCode();
			datos[i][1] = contratos1.getLabel();
			datos[i][2] = contratos1.getParent_code();

		}
		
		return datos;
	}
	
	
}
