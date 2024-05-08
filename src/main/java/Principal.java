import javax.swing.JFrame;

import view.PanelProvincia;
import view.PanelTabla;

public class Principal extends JFrame {
		
		
		static Principal instance = null;
		public static Principal getInstance() {
			if (instance == null) {
				instance = new Principal();
			}
			return instance;
		}
			
		public Principal() {
			super("JPA");
			this.setBounds(0,0,800,600);
			PanelTabla panel1 = PanelTabla.getInstance();
			this.getContentPane().add(panel1);
		}

		public static void main(String[] args) {

			Principal.getInstance().setVisible(true);
		}
	}