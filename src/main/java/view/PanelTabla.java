package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.ControladorProvincia;
import controllers.DatosDeTabla;
import entities.Ccaa;
import entities.Provincia;

import javax.swing.JLabel;

public class PanelTabla extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel dtm = null;
	private Object datosEnTabla[][] = DatosDeTabla.getDatosDeTabla();
	private String titulosEnTabla[] = DatosDeTabla.getTitulosColumnas();
	
	static PanelTabla instance = null;
	public static PanelTabla getInstance() {
		if (instance == null) {
			instance = new PanelTabla();
		}
		return instance;
	}
	/**
	 * Create the panel.
	 */
	public PanelTabla () {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		this.dtm = getDefaultTableModelNoEditable();
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int indiceFilaSel = table.getSelectedRow();
			}
		});
		
		JScrollPane scrollpane = new JScrollPane(table);
		splitPane.setLeftComponent(scrollpane);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			int indiceFila = table.getSelectedRow();
			Object value = datosEnTabla[indiceFila][0];
			Provincia p = ControladorProvincia.getInstance().findById(Integer.valueOf(String.valueOf(value)));
			if (p != null) {
			    PanelProvincia panelprovincia = new PanelProvincia(p);
			    JScrollPane scrollpane2 = new JScrollPane(panelprovincia);
			    splitPane.setRightComponent(scrollpane2);
			} else {
			    // Manejar el caso en el que 'p' sea nulo, quizás mostrar un mensaje de error o tomar la acción apropiada
			}

			
			
		}
		});
		
		this.setLayout(new BorderLayout());
		add(splitPane, BorderLayout.CENTER);
		
		
		
		
	}
	
	private DefaultTableModel getDefaultTableModelNoEditable () {
		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column != 1) {
					return false;
				}
				return true;
			}
		};
		return dtm;
	}

}
