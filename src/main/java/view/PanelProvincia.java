package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JTextField;

import controllers.ControladorCcaa;
import controllers.ControladorProvincia;
import entities.Ccaa;
import entities.Provincia;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelProvincia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCode;
	private JTextField jtfLabel;
	private JComboBox <Ccaa>jcbCcaa;
	private String codePro;
	
	/**
	 * Create the panel.
	 */
	public PanelProvincia(Provincia p) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblGestionDeProvincia = new JLabel("Gestion de Provincia");
		GridBagConstraints gbc_lblGestionDeProvincia = new GridBagConstraints();
		gbc_lblGestionDeProvincia.gridwidth = 3;
		gbc_lblGestionDeProvincia.insets = new Insets(0, 0, 5, 0);
		gbc_lblGestionDeProvincia.gridx = 0;
		gbc_lblGestionDeProvincia.gridy = 0;
		panel.add(lblGestionDeProvincia, gbc_lblGestionDeProvincia);
		
		JLabel lblCode = new JLabel("Code:");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.EAST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 0;
		gbc_lblCode.gridy = 1;
		panel.add(lblCode, gbc_lblCode);
		
		jtfCode = new JTextField(p.getCode());
		GridBagConstraints gbc_jtfCode = new GridBagConstraints();
		gbc_jtfCode.gridwidth = 2;
		gbc_jtfCode.insets = new Insets(0, 0, 5, 0);
		gbc_jtfCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCode.gridx = 1;
		gbc_jtfCode.gridy = 1;
		panel.add(jtfCode, gbc_jtfCode);
		jtfCode.setColumns(10);
		
		JLabel lblLabel = new JLabel("Label:");
		GridBagConstraints gbc_lblLabel = new GridBagConstraints();
		gbc_lblLabel.anchor = GridBagConstraints.EAST;
		gbc_lblLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLabel.gridx = 0;
		gbc_lblLabel.gridy = 2;
		panel.add(lblLabel, gbc_lblLabel);
		
		jtfLabel = new JTextField(p.getLabel());
		GridBagConstraints gbc_jtfLabel = new GridBagConstraints();
		gbc_jtfLabel.gridwidth = 2;
		gbc_jtfLabel.insets = new Insets(0, 0, 5, 0);
		gbc_jtfLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfLabel.gridx = 1;
		gbc_jtfLabel.gridy = 2;
		panel.add(jtfLabel, gbc_jtfLabel);
		jtfLabel.setColumns(10);
		
		JLabel lblCcaa = new JLabel("ccaa:");
		GridBagConstraints gbc_lblCcaa = new GridBagConstraints();
		gbc_lblCcaa.anchor = GridBagConstraints.EAST;
		gbc_lblCcaa.insets = new Insets(0, 0, 5, 5);
		gbc_lblCcaa.gridx = 0;
		gbc_lblCcaa.gridy = 3;
		panel.add(lblCcaa, gbc_lblCcaa);
		
		jcbCcaa = new JComboBox<Ccaa>();
		GridBagConstraints gbc_jcbCcaa = new GridBagConstraints();
		gbc_jcbCcaa.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCcaa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCcaa.gridx = 1;
		gbc_jcbCcaa.gridy = 3;
		panel.add(jcbCcaa, gbc_jcbCcaa);
		
		JButton btnCcaa = new JButton("Ver ccaa");
		btnCcaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirNuevoDialogo(panel);
			}
		});
		GridBagConstraints gbc_btnCcaa = new GridBagConstraints();
		gbc_btnCcaa.insets = new Insets(0, 0, 5, 0);
		gbc_btnCcaa.gridx = 2;
		gbc_btnCcaa.gridy = 3;
		panel.add(btnCcaa, gbc_btnCcaa);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			guardar(p);
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 4;
		panel.add(btnGuardar, gbc_btnGuardar);
		codePro = p.getParent_code();
		cargatodasCcaa();
		seleccionaProvincia();

	}
	
	public void seleccionaProvincia() {
        for (int i = 0; i < this.jcbCcaa.getItemCount(); i++) {
            Ccaa ca = (Ccaa) this.jcbCcaa.getItemAt(i);
            if (ca.getCode().equals(ControladorCcaa.findById(Integer.valueOf(codePro)).getCode())) {
                this.jcbCcaa.setSelectedIndex(i);
                return;
            }
        }
    }

	public void cargatodasCcaa() {
		List<Ccaa> lista = ControladorCcaa.getAllCcaas();
		for (Ccaa ccaa : lista) {
			this.jcbCcaa.addItem(ccaa);
		}
	}
	
	public void abrirNuevoDialogo(JPanel panel) {
        JDialog dialogo = new JDialog();
        // El usuario no puede redimensionar el di�logo
        dialogo.setResizable(true);
        // t�tulo del d�alogo
        dialogo.setTitle("Gestión de empresas");
        // Introducimos el panel creado sobre el di�logo
        dialogo.setContentPane(panel);
        // Empaquetar el di�logo hace que todos los componentes ocupen el espacio que deben y el lugar adecuado
        dialogo.pack();
        // El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
        dialogo.setModal(true);
        // Centro el di�logo en pantalla
        dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
        // Muestro el di�logo en pantalla
        dialogo.setVisible(true);
    }
	
	public void guardar(Provincia p) {
        p.setCode(jtfCode.getText());
        p.setLabel(jtfLabel.getText());
        Ccaa ca = (Ccaa) this.jcbCcaa.getSelectedItem();
        p.setParent_code(ca.getCode());
        ControladorProvincia.updateDocument(p.getCode(),p.getLabel(),p.getParent_code());
    }
}
