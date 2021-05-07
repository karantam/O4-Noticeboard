package o4;

import java.awt.Color;
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
	
	
	//testi
	/*
	vastaus[] vastaukset_1 = {new vastaus("nimimerkki_11", "vastaus_11", "yhetystieto_11"), 
							new vastaus("nimimerkki_12", "vastaus_12", "yhetystieto_12"), 
							new vastaus("nimimerkki_13", "vastaus_13", "yhetystieto_13")};
	
	vastaus[] vastaukset_2 = {new vastaus("nimimerkki_21", "vastaus_21", "yhetystieto_21"), 
							new vastaus("nimimerkki_22", "vastaus_22", "yhetystieto_22"), 
							new vastaus("nimimerkki_23", "vastaus_23", "yhetystieto_23")};
	
	vastaus[] vastaukset_3 = {new vastaus("nimimerkki_31", "vastaus_31", "yhetystieto_31"), 
							new vastaus("nimimerkki_32", "vastaus_32", "yhetystieto_32"), 
							new vastaus("nimimerkki_33", "vastaus_33", "yhetystieto_33")};
	
	ilmoitus[] ilmoitukset = {new ilmoitus("nimi_1", "sijainti_1", "hinta_1", "kuvaus_1"),
								new ilmoitus("nimi_2", "sijainti_2", "hinta_2", "kuvaus_2"),
								new ilmoitus("nimi_3", "sijainti_3", "hinta_3", "kuvaus_3")};
								*/
	
	
	
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
	 
	 label_22("Vastaus:")		<--->	textArea_2("j‰tetty vastaus")
	 
	 label_23("Yhteystieto")	<--->	label_24("j‰tetty yhteystieto")
	 
	 ________________________________________________________________
	 
	 panel_3
	 ________________________________________________________________
	 
	 
	 button_1("Muokkaa")	button_2("Poista")		button_3("Peruuta")
	 ________________________________________________________________
	 */
	
	
	//M‰‰ritell‰‰n muuttuvat elementit
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
		
		List<Message> ilmoitukset = null;
		try {
			ilmoitukset = MessageDatabase.getInstance().getUsersMessages(kayttaja);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Message[] ilmoitukset_2;
		if (ilmoitukset != null && !ilmoitukset.isEmpty()) {
			ilmoitukset_2 = new Message[ilmoitukset.size()];
		} else {
			ilmoitukset_2 = new Message[1];
		}
		ilmoitukset_2[0] = new Message("","","",0,"");
		if (ilmoitukset != null && !ilmoitukset.isEmpty()) {
			for (int i = 0; i < ilmoitukset.size(); i++) {
				ilmoitukset_2[i] = (ilmoitukset.get(i));
			}
		}
		
		//testi
		//ilmoitus ilmoitus_1 = new ilmoitus("nimi_1", "sijainti_1", "hinta_1", "kuvaus_1kuvaus_1kuvaus_1kuvaus_1kuvaus_1kuvaus_1");
		//ilmoitus ilmoitus_2 = new ilmoitus("nimi_2", "sijainti_2", "hinta_2", "kuvaus_2");
		//ilmoitus[] ilmoitukset = {ilmoitus_1, ilmoitus_2};
		
		//ilmoitus_1.vastaanotaVastaus(new vastaus("nimimerkki_11", "vastaus_11vastaus_11vastaus_11vastaus_11vastaus_11", "yhetystieto_11"));
		//ilmoitus_1.vastaanotaVastaus(new vastaus("nimimerkki_12", "vastaus_12vastaus_12vastaus_12vastaus_12vastaus_12", "yhetystieto_12"));
		//ilmoitus_2.vastaanotaVastaus(new vastaus("nimimerkki_21", "vastaus_21vastaus_21vastaus_21vastaus_21vastaus_21", "yhetystieto_21"));
		//ilmoitus_2.vastaanotaVastaus(new vastaus("nimimerkki_22", "vastaus_22vastaus_22vastaus_22vastaus_22vastaus_22", "yhetystieto_22"));
		
		
		
		//yleinen toiminnallisuus panel_1
		box_1 = new JComboBox(ilmoitukset_2); 
		box_1.setSelectedIndex(0);
		box_1.addItemListener(this);
		
		Message valittuIlmoitus = (Message) box_1.getSelectedItem();
		String sijainti = valittuIlmoitus.getLocation();
		String hinta = String.valueOf(valittuIlmoitus.getPrice());
		String kuvaus = valittuIlmoitus.getMessage();
		//vastaus[] ilmoituksen_vastaukset = valittuIlmoitus.getVastaukset();
		
		List<Answer> vastaukset = null;
		try {
			vastaukset = MessageDatabase.getInstance().getAnswers(valittuIlmoitus.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		//ALKULIMA
		int x = 120;
		int x_2 = 250;
		int y = 30;
		
		int mar_x = 10;
		int mar_x_2 = 5;
		
		//panel_1
		//tehd‰‰n tyylittelyt ja list‰‰n elementit paneeliin 1
		
		
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
		//Tehd‰‰n tyylittely keskipaneeliin
		//Luodaan panel_2 ja lis‰t‰‰n siihen elementit
		
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
		//BoxLayout boxlayout_panel_3 = new BoxLayout(panel_3, BoxLayout.X_AXIS);
		
		JPanel panel_3_sub = new JPanel();
		BoxLayout boxlayout_panel_3_sub = new BoxLayout(panel_3_sub, BoxLayout.X_AXIS);
		
		
		//panel_3.setLayout(boxlayout_panel_3);
				
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
		//Luodaan ja tyylitell‰‰n p‰‰paneeli
		//lis‰t‰‰n alapaneelit 1, 2, ja 3 p‰‰paneeliin
		
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
	
	
	/*public static void main(String[] args) {
		new OmatIlmoitukset();	
	}*/
	


	public void itemStateChanged(ItemEvent e) {
		//Reagoidaan comboboxien valittujen itemien muutokseen
		if (e.getStateChange() == ItemEvent.SELECTED) {
			
			if((JComboBox)e.getSource() == box_1) {
				//Reagointi: box_1
				
	   			Message valittuIlmoitus = (Message) box_1.getSelectedItem();
	   			
	   			//P‰ivitet‰‰n panel_1
	   			label_15.setText(valittuIlmoitus.getLocation());
	   			label_16.setText(String.valueOf(valittuIlmoitus.getPrice()));
	   			textArea_1.setText(valittuIlmoitus.getMessage());
	   			
	   			//P‰ivitet‰‰n panel_2 kokonaisuudessaan
	   			List<Answer> vastaukset = null;
	   			try {
	   				vastaukset = MessageDatabase.getInstance().getAnswers(valittuIlmoitus.getName());
	   			} catch (SQLException ex) {
	   				// TODO Auto-generated catch block
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
	   			//p‰ivitet‰‰n pelk‰st‰‰n panel_2 (box_2 ei p‰ivity t‰ss‰)
	   			Answer valittuVastaus = (Answer) box_2.getSelectedItem();
	   			textArea_2.setText(valittuVastaus.getAnswerText()); 
	   			label_24.setText(valittuVastaus.getContact());
	   		}
	    }
		// asetetaan model comboboxin p‰ivitt‰mist‰ varten
		// https://moodle.oulu.fi/pluginfile.php/572575/mod_resource/content/4/swing/jTable/
		// oleellista on, ett‰ Listener reagoi comboboxin valitun itemin muunnokseen
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
		//T‰nne tullaan lis‰‰m‰‰n toiminnallisuuttaa: ilmoitus.muokkaa() ja 
		//ilmoitus.muokkaa() ja ilmoitus.poista
		
		if((JButton)e.getSource() == oi.button_1) {
			//Siirryt‰‰n muokkaamaan valittua ilmoitusta
			Message valittuIlmoitus = (Message) oi.box_1.getSelectedItem();
			
			//System.out.println(valittuIlmoitus);
			oi.frame.dispose();
			//System.out.println("Muokkaa");
			
			// new EditMessage(valittuIlmoitus);
			// T‰h‰n siis ...
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