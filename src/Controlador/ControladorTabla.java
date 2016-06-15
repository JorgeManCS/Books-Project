package Controlador;

import javax.swing.table.DefaultTableModel;

/**
 * @author JorgeManuel
 * 
 */
//Clase para no poder modificar los elementos desde la tabla
public class ControladorTabla extends DefaultTableModel{
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
