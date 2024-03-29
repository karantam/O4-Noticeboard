package o4;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class PoistaIlmoitus implements ActionListener {
	/*
	Tämä luokkaa avaa ikkunan, jossa kysytään käyttäjältä varmistusta 
	ilmoituksen poistosta. Jos käyttäjä painaa "Kyllä", ilmoitus poistuu databasesta,
	jos käyttäjä painaa "Ei" tai sulkee ikkunan rastista, toiminto peruuntuu.
	*/
	
	
	Message ilmoitus;		//Käsiteltävä ilmoitus
	
	//Luodaan komponentit
	JLabel label_1;
	JLabel label_2;
	JButton button_1 = new JButton("Kyllä");
	JButton button_2 = new JButton("Ei");
	JFrame frame;
	
	public PoistaIlmoitus(Message ilmoitus) {
		
		this.ilmoitus = ilmoitus;
		
		//Komponenttien toiminnallisuus
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
		
		
		//Pääpaneeli
		JPanel mainPanel = new JPanel();
		BoxLayout boxlayout_mainPanel = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxlayout_mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(panel_1);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(panel_2);
		

		
		//Luodaan freimi ja lisätään siihen paneeli
		frame = new JFrame("Poista ilmoitus");
		frame.setSize(300, 200);
		frame.setMinimumSize(new Dimension(285, 175));
		frame.setLocationRelativeTo(null);
		frame.add(mainPanel);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                frame.dispose();
                new OmatIlmoitukset(ilmoitus.getUser());
            }
        });
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_1) {		//Jos button_1-heräte, ilmoitus poistetaan databasesta
			boolean value = false;
			try {
				value = MessageDatabase.getInstance().deleteMessage(ilmoitus);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (value) {
				JOptionPane.showMessageDialog(frame, "Ilmoitus poistettu onnistuneesti", "Viesti" , JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
				new OmatIlmoitukset(ilmoitus.getUser());	//Palataan takaisin ikkunaan "Omat ilmoitukset"
			} else {
				JOptionPane.showMessageDialog(frame, "Ilmoituksen poistaminen epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == button_2) {		//Jos button_2-heräte, palataan takaisin ikkunaan "Omat ilmoitukset"
			frame.dispose();
			new OmatIlmoitukset(ilmoitus.getUser());
		}
	}
}