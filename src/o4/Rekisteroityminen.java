package o4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Rekisteroityminen implements ActionListener {
	
	MainWindow paa;
	int palataan;
	String tuote;
	
	//panel_1
	JLabel label_1 = new JLabel("K�ytt�j�nimi:               ");
	JLabel label_2 = new JLabel("S�hk�posti:                 ");
	JLabel label_3 = new JLabel("Salasana:                     ");
	JLabel label_4 = new JLabel("Vahvista salasana:    ");
	
	JTextField textfield_1 = new JTextField(20);
	JTextField textfield_2 = new JTextField(20);
	
	JPasswordField textfield_3 = new JPasswordField(20);
	JPasswordField textfield_4 = new JPasswordField(20);
	
	
	
	//panel_2
	JButton button_1 = new JButton("Rekister�idy");
	JButton button_2 = new JButton("Peruuta");
		
	
	
	//frame
	JFrame frame = new JFrame("Rekister�ityminen");
			
	public Rekisteroityminen(MainWindow paa, int palataan, String tuote) {
		
		this.paa = paa;
		this.palataan = palataan;
		this.tuote = tuote;
		
		//panel_1 labelit (130,25)
		
		//minimit ja maksimit
		label_1.setMinimumSize(label_4.getPreferredSize());
		label_1.setMaximumSize(label_4.getPreferredSize());
		label_2.setMinimumSize(label_4.getPreferredSize());
		label_2.setMaximumSize(label_4.getPreferredSize());
		label_3.setMinimumSize(label_4.getPreferredSize());
		label_3.setMaximumSize(label_4.getPreferredSize());
		label_4.setMinimumSize(label_4.getPreferredSize());
		label_4.setMaximumSize(label_4.getPreferredSize());
		
		textfield_1.setMinimumSize(textfield_2.getPreferredSize());
		textfield_1.setMaximumSize(textfield_2.getPreferredSize());
		textfield_2.setMinimumSize(textfield_2.getPreferredSize());
		textfield_2.setMaximumSize(textfield_2.getPreferredSize());
		textfield_3.setMinimumSize(textfield_2.getPreferredSize());
		textfield_3.setMaximumSize(textfield_2.getPreferredSize());
		textfield_4.setMinimumSize(textfield_2.getPreferredSize());
		textfield_4.setMaximumSize(textfield_2.getPreferredSize());
		
		
		JPanel panel_11 = new JPanel(); 
		BoxLayout boxlayout_panel_11 = new BoxLayout(panel_11, BoxLayout.X_AXIS);
		panel_11.setLayout(boxlayout_panel_11);
		panel_11.add(Box.createRigidArea(new Dimension(10,1))); panel_11.add(label_1); panel_11.add(textfield_1);panel_11.add(Box.createRigidArea(new Dimension(10,1)));
		
		JPanel panel_12 = new JPanel(); 
		BoxLayout boxlayout_panel_12 = new BoxLayout(panel_12, BoxLayout.X_AXIS);
		panel_12.setLayout(boxlayout_panel_12);
		panel_12.add(Box.createRigidArea(new Dimension(10,1))); panel_12.add(label_2); panel_12.add(textfield_2); panel_12.add(Box.createRigidArea(new Dimension(10,1)));
		
		JPanel panel_13 = new JPanel(); 
		BoxLayout boxlayout_panel_13 = new BoxLayout(panel_13, BoxLayout.X_AXIS);
		panel_13.setLayout(boxlayout_panel_13);
		panel_13.add(Box.createRigidArea(new Dimension(10,1))); panel_13.add(label_3); panel_13.add(textfield_3); panel_13.add(Box.createRigidArea(new Dimension(10,1)));
		
		JPanel panel_14 = new JPanel();
		BoxLayout boxlayout_panel_14 = new BoxLayout(panel_14, BoxLayout.X_AXIS);
		panel_14.setLayout(boxlayout_panel_14);
		panel_14.add(Box.createRigidArea(new Dimension(10,1))); panel_14.add(label_4); panel_14.add(textfield_4); panel_14.add(Box.createRigidArea(new Dimension(10,1)));
		
		
		JPanel panel_1 = new JPanel();
		
		BoxLayout boxlayout_panel_1 = new BoxLayout(panel_1, BoxLayout.Y_AXIS);
		panel_1.setLayout(boxlayout_panel_1);

		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_11); 
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_12);
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_13); 
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		panel_1.add(panel_14);  
		panel_1.add(Box.createRigidArea(new Dimension(1,10)));
		
		panel_1.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//panel_2
		
		//toiminnallisuus
		button_1.addActionListener(this);
		button_1.setFocusable(false);
		
		button_2.addActionListener(this);
		button_2.setFocusable(false);
		
		//tyyli
		button_1.setMinimumSize(button_1.getPreferredSize());
		button_1.setPreferredSize(button_1.getPreferredSize());
		button_1.setMaximumSize(button_1.getPreferredSize());
		button_2.setMinimumSize(button_1.getPreferredSize());
		button_2.setPreferredSize(button_1.getPreferredSize());
		button_2.setMaximumSize(button_1.getPreferredSize());
		
		JPanel panel_2 = new JPanel();
		//BoxLayout boxlayout_panel_2 = new BoxLayout(panel_2, BoxLayout.X_AXIS);
		
		JPanel panel_2_sub = new JPanel();
		BoxLayout boxlayout_panel_2_sub = new BoxLayout(panel_2_sub, BoxLayout.X_AXIS);
		//panel_2_sub.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		//panel_2.setLayout(boxlayout_panel_2);
		
		panel_2_sub.setLayout(boxlayout_panel_2_sub);
		
		panel_2_sub.add(button_1);
		panel_2_sub.add(Box.createRigidArea(new Dimension(140, 40)));
		panel_2_sub.add(Box.createHorizontalGlue()); 
		panel_2_sub.add(button_2);
		
		panel_2.add(panel_2_sub);
		
		
		//sisus.add(Box.createVerticalGlue()); T�RKE��!!!
		//nappi.setAlignmentX(CENTER_ALIGNMENT)

		
		//mainPanel
		JPanel mainPanel = new JPanel();
		BoxLayout boxlayout_main = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxlayout_main);
		
		
		mainPanel.add(Box.createVerticalGlue()); 
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(panel_1);
		mainPanel.add(Box.createRigidArea(new Dimension(1,10)));
		mainPanel.add(Box.createVerticalGlue()); 
		mainPanel.add(panel_2);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		
		
		//frame
		frame.setSize(400, 250);
		frame.setMinimumSize(new Dimension(350, 230));
		frame.setLocationRelativeTo(null);
		frame.add(mainPanel);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //System.exit(0);
                frame.dispose();
                KirjauduSisaan kirjaudu = new KirjauduSisaan(paa, palataan, tuote);
            }
        });
		
	}
	
	
	
	
	/*public static void main(String[] args) {
		new Rekisteroityminen("MainWindow");
	}*/



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_1) {
			String userName = textfield_1.getText();
			String email = textfield_2.getText();
			String password = textfield_3.getText();
			String passwordAgain = textfield_4.getText();
			
			if(informationsAreAllowed(userName, email, password, passwordAgain)) {
				System.out.println("Rekister�idy");
				System.out.println("Luodaan uusi k�ytt�j� databaseen");
				User kayttaja = new User(userName, password, email);
				boolean value = false;
				try {
					value = MessageDatabase.getInstance().setUser(kayttaja);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(frame, "K�ytt�j�nimen ja salasanan rekister�inniss� tapahtui virhe", "Virhe" , JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if(value) {
					paa.kirjautuminen(userName);
					frame.dispose();
					if (palataan != 0) {
						AnswerMessage answer = new AnswerMessage(tuote, paa.kayttajanimi());
						answer.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "K�ytt�j�nimi tai salasana ei kelpaa", "Virhe" , JOptionPane.WARNING_MESSAGE);
					textfield_1.setText("");
					textfield_3.setText("");
					textfield_4.setText("");
				}
				/*if(palataan == "AnswerMessage") {
					//Palataan vastaamisikkunaan
					System.out.println("Palataam AnswerMessage - ikkunaan");
				}
				if(palataan == "MainWindow") {
					//Palataan aloitusn�kym��n tai p�ivitet��n se
					System.out.println("Palataan aloitusn�kym��n");
				}*/
			//else {return;}
			}
		}
		if(e.getSource() == button_2) {
			KirjauduSisaan kirjaudu = new KirjauduSisaan(paa, palataan, tuote);
			frame.dispose();
			}
	}
	
	//Apufunktio_1
	public String checkUserName(String userName) {
		String problem = null;
		//3-20 merkki�
		if(userName.length() > 20 | userName.length() < 3) 
			{return problem = "K�ytt�j�nimen on oltava v�hint��n 3 merkki� ja enint��n 20 merkki� pitk�.";}
		//Kaikki ok
		else{return problem;}
	}
	
	//Apufunktio_2
	public String checkEmail(String email) {
		String problem = null;
		//Ei sis�ll� at-merkki�
		char someChar = '@';
		int count = 0;
		for (int i = 0; i < email.length(); i++) {
		    if (email.charAt(i) == someChar) {
		        count++;
		    }
		}
		if(count != 1)
			{return problem = "S�hk�posti ei ole kelvollinen";}
		//Kaikki ok
		else {return problem;}
	}
	
	
	//Apufunktio_3
	public String checkPassword(String password) {
		String problem = null;
		//6-18 merkki�
		if(password.length() > 18 | password.length() < 6) 
			{return problem = "Salasanan on oltava v�hint��n 6 merkki� ja enint��n 18 merkki� pitk�.";}
		//Kaikki ok
		else {return problem;}
	}
	
	
	//Apufunktio_4
	public String checkPasswordAgain(String password, String passwordAgain) {
		String problem = null;
		//Salasanat ovat erit
		if(!password.equals(passwordAgain))
			{return problem = "Salasanat eiv�t t�sm��.";}
		//Kaikki ok
		else {return problem;}
	}
	
	
	//Tietojen tarkistus - funktio
	public boolean informationsAreAllowed(String userName, String email, String password, String passwordAgain){
		String problem;
		
		problem = checkUserName(userName);
		if(problem != null) 
			{textfield_1.setText(""); 
			JOptionPane.showMessageDialog(frame, problem, "Virhe" , JOptionPane.WARNING_MESSAGE);
			return false;}
		
		problem = checkEmail(email);
		if(problem != null) 
			{textfield_2.setText(""); 
			JOptionPane.showMessageDialog(frame, problem, "Virhe" , JOptionPane.WARNING_MESSAGE);
			return false;}
		
		problem = checkPassword(password);
		if(problem != null) 
			{textfield_3.setText(""); textfield_4.setText(""); 
			JOptionPane.showMessageDialog(frame, problem, "Virhe" , JOptionPane.WARNING_MESSAGE);
			return false;}
		
		problem = checkPasswordAgain(password, passwordAgain);
		if(problem != null)
			{textfield_3.setText(""); textfield_4.setText(""); 
			JOptionPane.showMessageDialog(frame, problem, "Virhe" , JOptionPane.WARNING_MESSAGE);
			return false;}
		
		else {return true;}
	}
	
}
