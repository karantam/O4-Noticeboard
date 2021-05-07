package o4;

import java.awt.Color;
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

public class KirjauduSisaan implements ActionListener {
	
	MainWindow paa;
	int palataan;
	String tuote;

	//panel_1
	JLabel label_1 = new JLabel("Käyttäjänimi:      ");
	JLabel label_2 = new JLabel("Salasana:           ");
	JTextField textfield_1 = new JTextField(20);
	JPasswordField textfield_2 = new JPasswordField(20);
	
	//panel_2
	JLabel label_3 = new JLabel("Eikö sinulla ole tiliä?     ");
	JButton button_1 = new JButton("Rekisteröidy");
	
	//panel_3
	JButton button_2 = new JButton("Kirjaudu sisään");
	JButton button_3 = new JButton("Peruuta");
	
	JFrame frame = new JFrame("Kirjaudu sisään");
	
	public KirjauduSisaan( MainWindow  paa, int palataan, String tuote) {
		
		this.paa = paa;
		this.palataan = palataan;
		this.tuote = tuote;
		
		//panel_1
		
		//Minimit ja maksimit
		
		
		label_1.setMinimumSize(label_1.getPreferredSize());
		label_1.setMaximumSize(label_1.getPreferredSize());
		label_2.setMinimumSize(label_1.getPreferredSize());
		label_2.setMaximumSize(label_1.getPreferredSize());
		
		textfield_1.setMinimumSize(textfield_1.getPreferredSize());
		textfield_1.setMaximumSize(textfield_1.getPreferredSize());
		textfield_2.setMinimumSize(textfield_1.getPreferredSize());
		textfield_2.setMaximumSize(textfield_1.getPreferredSize());
		
		
		
		//Tyyli
		
		JPanel panel_11 = new JPanel(); 
		BoxLayout boxlayout_panel_11 = new BoxLayout(panel_11, BoxLayout.X_AXIS);
		panel_11.setLayout(boxlayout_panel_11);
		panel_11.add(Box.createRigidArea(new Dimension(10, 0)));
		panel_11.add(label_1); 
		panel_11.add(textfield_1);
		panel_11.add(Box.createRigidArea(new Dimension(10 ,0)));
		
		JPanel panel_12 = new JPanel(); 
		BoxLayout boxlayout_panel_12 = new BoxLayout(panel_12, BoxLayout.X_AXIS);
		panel_12.setLayout(boxlayout_panel_12);
		panel_12.add(Box.createRigidArea(new Dimension(10, 0)));
		panel_12.add(label_2); 
		panel_12.add(textfield_2);
		panel_12.add(Box.createRigidArea(new Dimension(10 ,0)));
		
		JPanel panel_1 = new JPanel();
		BoxLayout boxlayout_panel_1 = new BoxLayout(panel_1, BoxLayout.Y_AXIS);
		panel_1.setLayout(boxlayout_panel_1);
		
		panel_1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel_1.add(panel_11); 
		panel_1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel_1.add(panel_12);
		panel_1.add(Box.createRigidArea(new Dimension(0, 10)));
		
		panel_1.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		//panel_2
		
		//Toiminnallisuus
		button_1.addActionListener(this);
		button_1.setFocusable(false);
		
		//Tyyli
		JPanel panel_2_sub = new JPanel();
		BoxLayout boxlayout_panel_2_sub = new BoxLayout(panel_2_sub, BoxLayout.X_AXIS);
		panel_2_sub.setLayout(boxlayout_panel_2_sub);
		
		panel_2_sub.add(Box.createRigidArea(new Dimension(10, 0)));
		panel_2_sub.add(label_3); 
		panel_2_sub.add(Box.createRigidArea(new Dimension(61, 40)));
		panel_2_sub.add(Box.createHorizontalGlue()); 
		panel_2_sub.add(button_1);
		panel_2_sub.add(Box.createRigidArea(new Dimension(10, 0)));
		
		panel_2_sub.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JPanel panel_2 = new JPanel();
		panel_2.add(panel_2_sub);
		
		
		
		//panel_3
		
		//Toiminnallisuus
		button_2.addActionListener(this);
		button_2.setFocusable(false);
		button_3.addActionListener(this);
		button_3.setFocusable(false);
		
		//Tyyli
		button_1.setMinimumSize(button_2.getPreferredSize());
		button_1.setPreferredSize(button_2.getPreferredSize());
		button_1.setMaximumSize(button_2.getPreferredSize());
		button_2.setMinimumSize(button_2.getPreferredSize());
		button_2.setPreferredSize(button_2.getPreferredSize());
		button_2.setMaximumSize(button_2.getPreferredSize());
		button_3.setMinimumSize(button_2.getPreferredSize());
		button_3.setPreferredSize(button_2.getPreferredSize());
		button_3.setMaximumSize(button_2.getPreferredSize());
		
		JPanel panel_3_sub = new JPanel();
		BoxLayout boxlayout_panel_3_sub = new BoxLayout(panel_3_sub, BoxLayout.X_AXIS);
		panel_3_sub.setLayout(boxlayout_panel_3_sub);
		
		panel_3_sub.add(button_2); 
		panel_3_sub.add(Box.createRigidArea(new Dimension(75, 40)));
		panel_3_sub.add(Box.createHorizontalGlue()); 
		panel_3_sub.add(button_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.add(panel_3_sub);
		
		
		
		//mainPanel
		JPanel mainPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxlayout);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(panel_1);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(panel_2);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(panel_3);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		
		//frame
		//JFrame frame = new JFrame("Kirjaudu sisään");
		frame.setSize(400, 280);
		frame.setMinimumSize(new Dimension(350, 240));
		frame.setLocationRelativeTo(null);
		frame.add(mainPanel);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //System.exit(0);
                frame.dispose();
                if (palataan != 0) {
    				ShowMessage answer = new ShowMessage(paa, tuote);
    				answer.setVisible(true);
    			}
            }
        });
		
	}

	
	/*public static void main(String[] args) {
		new KirjauduSisaan("MainWindow");
	}*/


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_1) {
			System.out.println("Rekisteröidy");
			new Rekisteroityminen(paa, palataan, tuote);
			frame.dispose();
		}
		if(e.getSource() == button_2) {
			System.out.println("Kirjaudu sisään");
			String userName = textfield_1.getText();
			String password = textfield_2.getText(); //JPassword.getText() saattaa kusta joissain versioissa	
			System.out.println(userName);
			System.out.println(password);
			boolean value = false;
			try {
				value = MessageDatabase.getInstance().checkUser(userName, password);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(frame, "Käyttäjänimen ja salasanan tarkistuksessa tapahtui virhe", "Virhe" , JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(frame, "Käyttäjänimi tai salasana ei kelpaa", "Virhe" , JOptionPane.WARNING_MESSAGE);
			}
			/*if(palataan == "MainWindow") {
				// return userName;
				// new MainWindow(userName);
				//tai uploadataan pääikkuna
				System.out.println("Palataan aloitusnäkymään");
			}
			if(palataan == "AnswerMessage") {
				// new AnswerMessage();
				System.out.println("Palataan AnswerMessage - ikkunaan");
			}*/
		}
		if(e.getSource() == button_3) {
			System.out.println("Peruuta");
			frame.dispose();
			if (palataan != 0) {
				ShowMessage answer = new ShowMessage(paa, tuote);
				answer.setVisible(true);
			}
		}
			
	}

}