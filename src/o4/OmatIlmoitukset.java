package o4;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OmatIlmoitukset implements ItemListener{
	
	
	/*		KOMPONENTIT JA NIIDEN ASETTELUT 
	 
	 panel_1
	 _______________________________________________________________
	 
	 label_11("Tuotteen nimi:")	<---> 	box_1
	 
	 label_12("Sijainti:") 		<---> 	label_15("tuotteen sijainti")
	 
	 label_13("Hinta:")			<---> 	label_16("tuotteen hinta")
	 
	 label_14("Kuvaus:")		<--->	textArea_1("tuotteen kuvaus")
	 
	 ________________________________________________________________
	 
	 panel_2
	 ________________________________________________________________
	 
	 label_21("Vastaukset:") 	<--->	box_2
	 
	 label_22("Vastaus:")		<--->	textArea_2("jätetty vastaus")
	 
	 label_23("Yhteystieto")	<--->	label_24("jätetty yhteystieto")
	 
	 ________________________________________________________________
	 
	 panel_3
	 ________________________________________________________________
	 
	 
	 button_1("Muokkaa")	button_2("Poista")		button_3("Peruuta")
	 ________________________________________________________________
	 */
	
	
	//Määritellään muuttuvat elementit
	//luodaan muuttumattomat elementit
	
	//panel_1
	JLabel label_11 = new JLabel("Tuotteen nimi:  ");
	JLabel label_12 = new JLabel("Sijainti:");
	JLabel label_13 = new JLabel("Hinta:");
	JLabel label_14 = new JLabel("Kuvaus:");
	
	JComboBox box_1;
	
	JLabel label_15;								//label: --> TUOTTEEN SIJAINTI
	JLabel label_16;								//label: --> TUOTTEEN HINTA
	JTextArea textArea_1;							//textarea: --> TUOTTEEN KUVAUS
	
	//panel_2
	JLabel label_21 = new JLabel("Vastaukset");		//label: VASTAUKSET -->
	JLabel label_22 = new JLabel("Vastaus");		//label: VASTAUS -->
	JLabel label_23 = new JLabel("Yhteystiedot");	//label: YHTEYSTIEDOT -->
													
	JComboBox box_2;								//combobox: --> VASTAUKSET
	
	JTextArea textArea_2;							//textarea: --> VASTAUS
	JLabel label_24;								//label_ --> YHTEYSTIEDOT
	
	//panel_3
	JButton button_1 = new JButton("Muokkaa");		//
	JButton button_2 = new JButton("Poista");		//	NAPIT
	JButton button_3 = new JButton("Peruuta");		//
	
	//frame
	JFrame frame = new JFrame("Omat Ilmoitusket");

	
	public OmatIlmoitukset(String kayttaja) {
		button_1.setEnabled(false);		//Asetetaan muokkaa- ja poista-nappi aluksi pois käytöstä
		button_2.setEnabled(false);		//ja laitetaan ne käyttöön, mikäli käyttäjällä on ilmoituksia
		
		
		//Tehdään ilmoituksista [], jotta combobox ei suutu
		List<Message> ilmoitukset = null;
		try {
			ilmoitukset = MessageDatabase.getInstance().getUsersMessages(kayttaja);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Message[] ilmoitukset_2;
		if (ilmoitukset != null && !ilmoitukset.isEmpty()) {
			ilmoitukset_2 = new Message[ilmoitukset.size()];
		} else {
			ilmoitukset_2 = new Message[1];
		}
		ilmoitukset_2[0] = new Message("","","",0,"");	//Tehdään listan alkuun tyhjä alkio, jotta combobox toimii varmasti
		if (ilmoitukset != null && !ilmoitukset.isEmpty()) {
			for (int i = 0; i < ilmoitukset.size(); i++) {
				ilmoitukset_2[i] = (ilmoitukset.get(i));
			}
		}
		
		
		//yleinen toiminnallisuus panel_1
		box_1 = new JComboBox(ilmoitukset_2); 
		box_1.setSelectedIndex(0);
		box_1.addItemListener(this);
		
		Message valittuIlmoitus = (Message) box_1.getSelectedItem();
		String sijainti = valittuIlmoitus.getLocation();
		String hinta = String.valueOf(valittuIlmoitus.getPrice());
		String kuvaus = valittuIlmoitus.getMessage();
		//Jos ilmoituksia on olemassa, asetetaan muokkaa- ja poista-nappi käytettäväksi
		if (!valittuIlmoitus.getName().isBlank()) {
			button_1.setEnabled(true);
			button_2.setEnabled(true);
		}
		
		//Tehdään vastauksista [], jotta combobox ei suutu
		List<Answer> vastaukset = null;
		try {
			vastaukset = MessageDatabase.getInstance().getAnswers(valittuIlmoitus.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Answer[] vastaukset_2;
		if (vastaukset != null && !vastaukset.isEmpty()) {
			vastaukset_2 = new Answer[vastaukset.size()];
		} else {
			vastaukset_2 = new Answer[1];
		}
		vastaukset_2[0] = new Answer("","","","");  //Tehdään listan alkuun tyhjä alkio, jotta combobox toimii varmasti
		for (int i = 0; i < vastaukset.size(); i++) {
			vastaukset_2[i] = (vastaukset.get(i));
		}
		
		label_15 = new JLabel(sijainti);
		label_16 = new JLabel(hinta);
		
		textArea_1 = new JTextArea(6, 30);
		textArea_1.setText(kuvaus);
		textArea_1.setEditable(false); textArea_1.setOpaque(false); textArea_1.setLineWrap(true);
		JScrollPane scroll_1 = new JScrollPane(textArea_1);
		
		
		//yleinen toiminnallisuus panel_2
		box_2 = new JComboBox(vastaukset_2);
		box_2.setSelectedIndex(0);
		box_2.addItemListener(this);
		
		Answer valittuVastaus = (Answer) box_2.getSelectedItem();
		String vastaus = valittuVastaus.getAnswerText();
		String yhteystieto = valittuVastaus.getContact();
		
		textArea_2 = new JTextArea(6, 30);
		textArea_2.setText(vastaus);
		textArea_2.setEditable(false); textArea_2.setOpaque(false); textArea_2.setLineWrap(true);
		JScrollPane scroll_2 = new JScrollPane(textArea_2);
		
		label_24 = new JLabel(yhteystieto);
		
		
		
		//yleinen toiminnallisuus panel_3
		napit nappikuuntelija = new napit(this);
		button_1.addActionListener(nappikuuntelija);
		button_2.addActionListener(nappikuuntelija);
		button_3.addActionListener(nappikuuntelija);
		
		//ALKULIMA	---	Komponenttien ja marginaalien koot
		int x = 120;
		int x_2 = 250;
		int y = 30;
		
		int mar_x = 10;
		int mar_x_2 = 5;
		
		//panel_1
		//tehdään tyylittelyt ja listään elementit paneeliin 1
		
		
		//Minimit ja maksimit
		label_11.setMinimumSize(new Dimension(x, y));
		label_11.setMaximumSize(new Dimension(x, y));
		box_1.setMinimumSize(new Dimension(x_2, y));
		box_1.setMaximumSize(new Dimension(x_2, y));
		
		label_12.setMinimumSize(new Dimension(x, y));
		label_12.setMaximumSize(new Dimension(x, y));
		label_15.setMinimumSize(new Dimension(x_2, y));
		label_15.setMaximumSize(new Dimension(x_2, y));
		
		label_13.setMinimumSize(new Dimension(x, y));
		label_13.setMaximumSize(new Dimension(x, y));
		label_16.setMinimumSize(new Dimension(x_2, y));
		label_16.setMaximumSize(new Dimension(x_2, y));
		
		label_14.setMinimumSize(new Dimension(x, y));
		label_14.setMaximumSize(new Dimension(x, y));
		textArea_1.setMinimumSize(new Dimension(x_2, 4*y));
		textArea_1.setMaximumSize(new Dimension(x_2, 4*y));
		scroll_1.setMinimumSize(new Dimension(x_2, 4*y));
		scroll_1.setMaximumSize(new Dimension(x_2, 4*y));
		
		
		
		JPanel panel_11 = new JPanel(); 
		BoxLayout boxlayout_panel_11 = new BoxLayout(panel_11, BoxLayout.X_AXIS);
		panel_11.setLayout(boxlayout_panel_11);
		panel_11.add(Box.createRigidArea(new Dimension(mar_x,0)));
		panel_11.add(label_11); panel_11.add(box_1);
		panel_11.add(Box.createRigidArea(new Dimension(mar_x,0)));
		
		JPanel panel_12 = new JPanel(); 
		BoxLayout boxlayout_panel_12 = new BoxLayout(panel_12, BoxLayout.X_AXIS);
		panel_12.setLayout(boxlayout_panel_12);
		panel_12.add(Box.createRigidArea(new Dimension(mar_x,0)));
		panel_12.add(label_12); panel_12.add(label_15);
		panel_12.add(Box.createRigidArea(new Dimension(mar_x,0)));
		
		JPanel panel_13 = new JPanel(); 
		BoxLayout boxlayout_panel_13 = new BoxLayout(panel_13, BoxLayout.X_AXIS);
		panel_13.setLayout(boxlayout_panel_13);
		panel_13.add(Box.createRigidArea(new Dimension(mar_x,0)));
		panel_13.add(label_13); panel_13.add(label_16);
		panel_13.add(Box.createRigidArea(new Dimension(mar_x,0)));
		
		JPanel panel_14 = new JPanel();
		BoxLayout boxlayout_panel_14 = new BoxLayout(panel_14, BoxLayout.X_AXIS);
		panel_14.setLayout(boxlayout_panel_14);
		panel_14.add(Box.createRigidArea(new Dimension(mar_x,0)));
		panel_14.add(label_14); panel_14.add(scroll_1);
		panel_14.add(Box.createRigidArea(new Dimension(mar_x,0)));
		
		JPanel panel_1 = new JPanel();
		BoxLayout boxlayout_panel_1 = new BoxLayout(panel_1, BoxLayout.Y_AXIS);
		panel_1.setLayout(boxlayout_panel_1);
		
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_11);
		panel_1.add(Box.createRigidArea(new Dimension(1,15)));
		panel_1.add(panel_12);
		panel_1.add(Box.createRigidArea(new Dimension(1,5)));
		panel_1.add(panel_13);
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_14);
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		
		panel_1.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		//panel_2
		//Tehdään tyylittely keskipaneeliin
		//Luodaan panel_2 ja lisätään siihen elementit
		
		//Minimit ja maksimit
		label_21.setMinimumSize(new Dimension(x, y));
		label_21.setMaximumSize(new Dimension(x, y));
		box_2.setMinimumSize(new Dimension(x_2, y));
		box_2.setMaximumSize(new Dimension(x_2, y));
		
		label_22.setMinimumSize(new Dimension(x, y));
		label_22.setMaximumSize(new Dimension(x, y));
		textArea_2.setMinimumSize(new Dimension(x_2, 4*y));
		textArea_2.setMaximumSize(new Dimension(x_2, 4*y));
		scroll_2.setMinimumSize(new Dimension(x_2, 4*y));
		scroll_2.setMaximumSize(new Dimension(x_2, 4*y));
		
		label_23.setMinimumSize(new Dimension(x, y));
		label_23.setMaximumSize(new Dimension(x, y));
		label_24.setMinimumSize(new Dimension(x_2, y));
		label_24.setMaximumSize(new Dimension(x_2, y));
				
				
		JPanel panel_21 = new JPanel(); 
		BoxLayout boxlayout_panel_21 = new BoxLayout(panel_21, BoxLayout.X_AXIS);
		panel_21.setLayout(boxlayout_panel_21);
		panel_21.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
		panel_21.add(label_21); panel_21.add(box_2);
		panel_21.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
		
		JPanel panel_22 = new JPanel(); 
		BoxLayout boxlayout_panel_22 = new BoxLayout(panel_22, BoxLayout.X_AXIS);
		panel_22.setLayout(boxlayout_panel_22);
		panel_22.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
		panel_22.add(label_22); panel_22.add(scroll_2);
		panel_22.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
		
		JPanel panel_23 = new JPanel(); 
		BoxLayout boxlayout_panel_23 = new BoxLayout(panel_23, BoxLayout.X_AXIS);
		panel_23.setLayout(boxlayout_panel_23);
		panel_23.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
		panel_23.add(label_23); panel_23.add(label_24);
		panel_23.add(Box.createRigidArea(new Dimension(mar_x_2,0)));
				
		JPanel panel_2 = new JPanel();
				
		BoxLayout boxlayout_panel_2 = new BoxLayout(panel_2, BoxLayout.Y_AXIS);
		panel_2.setLayout(boxlayout_panel_2);
				
		panel_2.add(Box.createRigidArea(new Dimension(1,10)));
		panel_2.add(panel_21);
		panel_2.add(Box.createRigidArea(new Dimension(1,15)));
		panel_2.add(panel_22);
		panel_2.add(Box.createRigidArea(new Dimension(1,10)));
		panel_2.add(panel_23);
		panel_2.add(Box.createRigidArea(new Dimension(1,10)));
				
		panel_2.setBorder(BorderFactory.createRaisedBevelBorder());
				
				
		//Panel_3
				
		button_1.setMinimumSize(button_1.getPreferredSize());
		button_1.setPreferredSize(button_1.getPreferredSize());
		button_1.setMaximumSize(button_1.getPreferredSize());
		button_2.setMinimumSize(button_1.getPreferredSize());
		button_2.setPreferredSize(button_1.getPreferredSize());
		button_2.setMaximumSize(button_1.getPreferredSize());
		button_3.setMinimumSize(button_1.getPreferredSize());
		button_3.setPreferredSize(button_1.getPreferredSize());
		button_3.setMaximumSize(button_1.getPreferredSize());
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_3_sub = new JPanel();
		BoxLayout boxlayout_panel_3_sub = new BoxLayout(panel_3_sub, BoxLayout.X_AXIS);
		
		
		panel_3_sub.setLayout(boxlayout_panel_3_sub);
		
		panel_3_sub.add(button_1);
		panel_3_sub.add(Box.createRigidArea(new Dimension(68, 40)));
		panel_3_sub.add(Box.createHorizontalGlue()); 
		panel_3_sub.add(button_2);
		panel_3_sub.add(Box.createRigidArea(new Dimension(68, 40)));
		panel_3_sub.add(Box.createHorizontalGlue()); 
		panel_3_sub.add(button_3);
		
		panel_3.add(panel_3_sub);
		
		//mainPanel
		//Luodaan ja tyylitellään pääpaneeli
		//lisätään alapaneelit 1, 2, ja 3 pääpaneeliin
		
		JPanel mainPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxlayout);
				
		mainPanel.add(Box.createRigidArea(new Dimension(1,10))); 
		panel_1.add(Box.createVerticalGlue()); 
		panel_1.add(panel_2);
		panel_1.add(Box.createRigidArea(new Dimension(1,5)));
		mainPanel.add(panel_1);
		mainPanel.add(Box.createRigidArea(new Dimension(1,10)));
		mainPanel.add(Box.createVerticalGlue()); 
		mainPanel.add(panel_3);
		
		//frame
		frame.setSize(440, 535);
		frame.setMinimumSize(new Dimension(415, 515));
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		//Reagoidaan comboboxien valittujen itemien muutokseen
		if (e.getStateChange() == ItemEvent.SELECTED) {
			
			if((JComboBox)e.getSource() == box_1) {
				//Reagointi: box_1
				
	   			Message valittuIlmoitus = (Message) box_1.getSelectedItem();
	   			
	   			//Päivitetään panel_1
	   			label_15.setText(valittuIlmoitus.getLocation());
	   			label_16.setText(String.valueOf(valittuIlmoitus.getPrice()));
	   			textArea_1.setText(valittuIlmoitus.getMessage());
	   			
	   			//Päivitetään panel_2 kokonaisuudessaan
	   			List<Answer> vastaukset = null;
	   			try {
	   				vastaukset = MessageDatabase.getInstance().getAnswers(valittuIlmoitus.getName());
	   			} catch (SQLException ex) {
	   				ex.printStackTrace();
	   			}
	   			Answer[] vastaukset_2;
	   			if (vastaukset != null && !vastaukset.isEmpty()) {
	   				vastaukset_2 = new Answer[vastaukset.size()];
	   			} else {
	   				vastaukset_2 = new Answer[1];
	   			}
	   			vastaukset_2[0] = new Answer("","","","");
	   			for (int i = 0; i < vastaukset.size(); i++) {
	   				vastaukset_2[i] = (vastaukset.get(i));
	   			}
	   			
	   			DefaultComboBoxModel<Answer> model = new DefaultComboBoxModel<>(vastaukset_2);
	   			box_2.setModel(model); 
	   			Answer valittuVastaus = (Answer) box_2.getSelectedItem();
	   			textArea_2.setText(valittuVastaus.getAnswerText()); 
	   			label_24.setText(valittuVastaus.getContact());
	   		}
	   		if((JComboBox)e.getSource() == box_2) {
	   			//Reagointi: Box_2
	   			//päivitetään pelkästään panel_2 (box_2 ei päivity tässä)
	   			Answer valittuVastaus = (Answer) box_2.getSelectedItem();
	   			textArea_2.setText(valittuVastaus.getAnswerText()); 
	   			label_24.setText(valittuVastaus.getContact());
	   		}
	    }
		// asetetaan model comboboxin päivittämistä varten
		// https://moodle.oulu.fi/pluginfile.php/572575/mod_resource/content/4/swing/jTable/
		// oleellista on, että Listener reagoi comboboxin valitun itemin muunnokseen
		// https://stackoverflow.com/questions/58939/jcombobox-selection-change-listener
	}       
	
	
	
}

class napit implements ActionListener{
	//Luokka napeille, jotta voidaan luoda niille oma ActionListener
	OmatIlmoitukset oi;
	
	public napit(OmatIlmoitukset omatilmoitukset) {
		oi = omatilmoitukset;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if((JButton)e.getSource() == oi.button_1) {
			//Siirrytään muokkaamaan valittua ilmoitusta
			Message valittuIlmoitus = (Message) oi.box_1.getSelectedItem();
			oi.frame.dispose();
			EditMessage edit = new EditMessage(valittuIlmoitus);
            edit.setVisible(true);
		}
		if((JButton)e.getSource() == oi.button_2) {
			//Poistetaan valittu ilmoitus
			Message valittuIlmoitus = (Message) oi.box_1.getSelectedItem();
			oi.frame.dispose();
			new PoistaIlmoitus(valittuIlmoitus);
		}
		if((JButton)e.getSource() == oi.button_3) {
			//Suljetaan ikkuna
			oi.frame.dispose();
		}
	}

}