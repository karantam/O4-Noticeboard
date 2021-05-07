package o4;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.FlowLayout;


public class PoistaIlmoitus implements ActionListener {
	
	Message ilmoitus;		//K‰sitelt‰v‰ ilmoitus
	
	//Luodaan komponentit
	JLabel label_1;
	JLabel label_2;
	JButton button_1 = new JButton("Kyll‰");
	JButton button_2 = new JButton("Ei");
	JFrame frame;
	
	public PoistaIlmoitus(Message ilmoitus) {
		
		this.ilmoitus = ilmoitus;
		
		label_1 = new JLabel("Haluatko varmasti poistaa valitun ilmoituksen:");
		label_2 = new JLabel(ilmoitus.getName() + "?");
		
		button_1.addActionListener(this);
		button_1.setFocusable(false);
		
		button_2.addActionListener(this);
		button_2.setFocusable(false);
		
		//Panel_1
		JPanel panel_1 = new JPanel();
		panel_1.add(label_1);
		panel_1.add(label_2);
		
		//Panel_2
		button_1.setMinimumSize(button_1.getPreferredSize());
		button_1.setPreferredSize(button_1.getPreferredSize());
		button_1.setMaximumSize(button_1.getPreferredSize());
		button_2.setMinimumSize(button_1.getPreferredSize());
		button_2.setPreferredSize(button_1.getPreferredSize());
		button_2.setMaximumSize(button_1.getPreferredSize());
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_2_sub = new JPanel();
		BoxLayout boxlayout_panel_2_sub = new BoxLayout(panel_2_sub, BoxLayout.X_AXIS);
		panel_2_sub.setLayout(boxlayout_panel_2_sub);
		
		panel_2_sub.add(button_1);
		panel_2_sub.add(Box.createRigidArea(new Dimension(80, 30)));
		panel_2_sub.add(Box.createHorizontalGlue()); 
		panel_2_sub.add(button_2);
		
		panel_2.add(panel_2_sub);
		
		
		//P‰‰paneeli
		JPanel mainPanel = new JPanel();
		BoxLayout boxlayout_mainPanel = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxlayout_mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(panel_1);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(panel_2);
		

		
		//Luodaan freimi ja lis‰t‰‰n siihen paneeli
		frame = new JFrame("Poista ilmoitus");
		frame.setSize(300, 200);
		frame.setMinimumSize(new Dimension(285, 175));
		frame.setLocationRelativeTo(null);
		frame.add(mainPanel);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //System.exit(0);
                frame.dispose();
                new OmatIlmoitukset(ilmoitus.getUser());
            }
        });
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	
	
	/*public static void main(String[] args) {
		ilmoitus ilmoitus_1 = new ilmoitus("Trip stopper", "Amsterdam", "2.50", "toimii aina");
		new PoistaIlmoitus(ilmoitus_1);
	}*/



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_1) {
			System.out.println("Poistetaan ilmoitus nimelt‰‰n " + ilmoitus);	//K‰yd‰‰n sorkkimassa kayttajan ilmoituslistaa
			boolean value = false;
			try {
				value = MessageDatabase.getInstance().deleteMessage(ilmoitus);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (value) {
				JOptionPane.showMessageDialog(frame, "Ilmoitus poistettu onnistuneesti", "Viesti" , JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
				new OmatIlmoitukset(ilmoitus.getUser());
			} else {
				JOptionPane.showMessageDialog(frame, "Ilmoituksen poistaminen ep‰onnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == button_2) {
			frame.dispose();
			new OmatIlmoitukset(ilmoitus.getUser());
		}
	}
}