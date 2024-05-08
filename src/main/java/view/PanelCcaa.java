package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import controllers.ControladorCcaa;
import entities.Ccaa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCcaa extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField jtfCode;
    private JTextField jtfLabel;

    /**
     * Create the panel.
     */
    public PanelCcaa(Ccaa ca) {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JLabel lblGestionDeCcaa = new JLabel("Gestion de CCAA");
        GridBagConstraints gbc_lblGestionDeCcaa = new GridBagConstraints();
        gbc_lblGestionDeCcaa.gridwidth = 2;
        gbc_lblGestionDeCcaa.insets = new Insets(0, 0, 5, 0);
        gbc_lblGestionDeCcaa.gridx = 0;
        gbc_lblGestionDeCcaa.gridy = 0;
        panel.add(lblGestionDeCcaa, gbc_lblGestionDeCcaa);
        
        JLabel lblCode = new JLabel("Code :");
        GridBagConstraints gbc_lblCode = new GridBagConstraints();
        gbc_lblCode.anchor = GridBagConstraints.EAST;
        gbc_lblCode.insets = new Insets(0, 0, 5, 5);
        gbc_lblCode.gridx = 0;
        gbc_lblCode.gridy = 1;
        panel.add(lblCode, gbc_lblCode);
        
        jtfCode = new JTextField(ca.getCode());
        GridBagConstraints gbc_jtfCode = new GridBagConstraints();
        gbc_jtfCode.insets = new Insets(0, 0, 5, 0);
        gbc_jtfCode.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtfCode.gridx = 1;
        gbc_jtfCode.gridy = 1;
        panel.add(jtfCode, gbc_jtfCode);
        jtfCode.setColumns(10);
        
        JLabel lblLabel = new JLabel("Label :");
        GridBagConstraints gbc_lblLabel = new GridBagConstraints();
        gbc_lblLabel.anchor = GridBagConstraints.EAST;
        gbc_lblLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblLabel.gridx = 0;
        gbc_lblLabel.gridy = 2;
        panel.add(lblLabel, gbc_lblLabel);
        
        jtfLabel = new JTextField(ca.getLabel());
        GridBagConstraints gbc_jtfLabel = new GridBagConstraints();
        gbc_jtfLabel.insets = new Insets(0, 0, 5, 0);
        gbc_jtfLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtfLabel.gridx = 1;
        gbc_jtfLabel.gridy = 2;
        panel.add(jtfLabel, gbc_jtfLabel);
        jtfLabel.setColumns(10);
        
        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar(ca ,jtfLabel.getText());
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 5;
        panel.add(btnNewButton, gbc_btnNewButton);

    }
        public void guardar(Ccaa ca,String provincia) {
            ca.setLabel(this.jtfLabel.getText());
            ca.setCode(this.jtfCode.getText());
            ControladorCcaa.updateDocument(ca.getCode(),provincia);
        }
        
        

}
