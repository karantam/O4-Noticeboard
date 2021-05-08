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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AnswerMessage extends JFrame{

	private JButton laheta;
    private JButton peruuta;
    private JLabel nimi;
    private JTextField nimirivi;
    private JLabel sijainti;
    private JTextField sijaintirivi;
    private JLabel hinta;
    private JTextField hintarivi;
    private JLabel kuvaus;
    private JTextArea kuvausrivi;
    private JScrollPane kuvausalue;
    private JLabel vastaus;
    private JTextArea vastausrivi;
    private JScrollPane vastausalue;
    private JLabel yhteys;
    private JTextField yhteysrivi;
    private JPanel sisus;
    private JPanel sisus2;
    private JPanel sisus3;
    private JPanel nimisisus;
    private JPanel sijaintisisus;
    private JPanel hintasisus;
    private JPanel kuvaussisus;
    private JPanel vastaussisus;
    private JPanel yhteyssisus;
    private JPanel nappisisus;
    private JFrame itse; //Talletuspaikka ikkunan viittaukselle itseensä
    
    
    public AnswerMessage(String tuote, String kayttaja) {
 
        super("Vastaa ilmoitukseen");
        itse = this;    //asetetaan itseviite talteen kuuntelijaa varten.
        setSize(500, 500);
        itse.setMinimumSize(new Dimension(400,400));
        setLocationRelativeTo(null);
        
        
        nimi = new JLabel ("Tuotteen nimi:  ");
        nimi.setMinimumSize(nimi.getPreferredSize());
        nimi.setMaximumSize(nimi.getPreferredSize());
        nimirivi = new JTextField (30);
        nimirivi.setMinimumSize(nimirivi.getPreferredSize());
        nimirivi.setMaximumSize(nimirivi.getPreferredSize());
        nimirivi.setEditable(false);
        nimirivi.setBorder(BorderFactory.createLineBorder(Color.black));
        
        sijainti = new JLabel ("Sijainti:               ");
        sijainti.setMinimumSize(nimi.getPreferredSize());
        sijainti.setMaximumSize(nimi.getPreferredSize());
        sijaintirivi = new JTextField (30);
        sijaintirivi.setMinimumSize(sijaintirivi.getPreferredSize());
        sijaintirivi.setMaximumSize(sijaintirivi.getPreferredSize());
        sijaintirivi.setEditable(false);
        sijaintirivi.setBorder(BorderFactory.createLineBorder(Color.black));
        
        hinta = new JLabel ("Hinta:                  ");
        hinta.setMinimumSize(nimi.getPreferredSize());
        hinta.setMaximumSize(nimi.getPreferredSize());
        hintarivi = new JTextField (30);
        hintarivi.setMinimumSize(hintarivi.getPreferredSize());
        hintarivi.setMaximumSize(hintarivi.getPreferredSize());
        hintarivi.setEditable(false);
        hintarivi.setBorder(BorderFactory.createLineBorder(Color.black));
        
        kuvaus = new JLabel ("Kuvaus:              ");
        kuvaus.setMinimumSize(nimi.getPreferredSize());
        kuvaus.setMaximumSize(nimi.getPreferredSize());
        kuvausrivi = new JTextArea (6,30);
        kuvausrivi.setLineWrap(true);
        kuvausrivi.setEditable(false);
        kuvausrivi.setOpaque(false);
        kuvausalue = new JScrollPane(kuvausrivi);
        kuvausalue.setMinimumSize(kuvausalue.getPreferredSize());
        kuvausalue.setMaximumSize(kuvausalue.getPreferredSize());
        kuvausalue.setBorder(BorderFactory.createLineBorder(Color.black));
        
        vastaus = new JLabel ("Vastaus:            ");
        vastaus.setMinimumSize(nimi.getPreferredSize());
        vastaus.setMaximumSize(nimi.getPreferredSize());
        vastausrivi = new JTextArea (6,30);
        vastausrivi.setLineWrap(true);
        vastausalue = new JScrollPane(vastausrivi);
        vastausalue.setMinimumSize(vastausalue.getPreferredSize());
        vastausalue.setMaximumSize(vastausalue.getPreferredSize());
        
        yhteys = new JLabel ("Yhteystiedot:    ");
        yhteys.setMinimumSize(nimi.getPreferredSize());
        yhteys.setMaximumSize(nimi.getPreferredSize());
        yhteysrivi = new JTextField (30);
        yhteysrivi.setMinimumSize(yhteysrivi.getPreferredSize());
        yhteysrivi.setMaximumSize(yhteysrivi.getPreferredSize());
        
        laheta = new JButton ("Lähetä");
        peruuta = new JButton ("Peruuta");
        laheta.setPreferredSize(peruuta.getPreferredSize());
        
        sisus = new JPanel();
        BoxLayout boxlayout = new BoxLayout(sisus, BoxLayout.Y_AXIS);
        sisus.setLayout(boxlayout);
        
        sisus2 = new JPanel();
        BoxLayout boxlayout2 = new BoxLayout(sisus2, BoxLayout.Y_AXIS);
        sisus2.setLayout(boxlayout2);
        sisus2.setBorder(BorderFactory.createRaisedBevelBorder());
        
        nimisisus = new JPanel();
        BoxLayout nimibox = new BoxLayout(nimisisus, BoxLayout.X_AXIS);
        nimisisus.setLayout(nimibox);
        
        sijaintisisus = new JPanel();
        BoxLayout sijaintibox = new BoxLayout(sijaintisisus, BoxLayout.X_AXIS);
        sijaintisisus.setLayout(sijaintibox);
        
        hintasisus = new JPanel();
        BoxLayout hintabox = new BoxLayout(hintasisus, BoxLayout.X_AXIS);
        hintasisus.setLayout(hintabox);
        
        kuvaussisus = new JPanel();
        BoxLayout kuvausbox = new BoxLayout(kuvaussisus, BoxLayout.X_AXIS);
        kuvaussisus.setLayout(kuvausbox);
        
        sisus3 = new JPanel();
        sisus3.setLayout(new  BoxLayout(sisus3, BoxLayout.Y_AXIS));
        sisus3.setBorder(BorderFactory.createRaisedBevelBorder());
        
        vastaussisus = new JPanel();
        vastaussisus.setLayout(new BoxLayout(vastaussisus, BoxLayout.X_AXIS));
        
        yhteyssisus = new JPanel();
        yhteyssisus.setLayout(new BoxLayout(yhteyssisus, BoxLayout.X_AXIS));
        
        nappisisus = new JPanel();
        BoxLayout nappibox = new BoxLayout(nappisisus, BoxLayout.X_AXIS);
        nappisisus.setLayout(nappibox);
        
        //Asetetaan nappien toiminnallisuudet
        
        peruuta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            }
        });
        
        laheta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String vastausteksti = vastausrivi.getText();
            	String yhteys = yhteysrivi.getText();
            	if (!vastausteksti.isBlank() && vastausteksti.length() > 3 && !yhteys.isBlank() && yhteys.length() > 3) {
            		Answer vastaus = new Answer(tuote, kayttaja, vastausteksti, yhteys);
            		boolean value = false;
        			try {
						value = MessageDatabase.getInstance().setAnswer(vastaus);
						if (value) {
	            			JOptionPane.showMessageDialog(itse, "Vastaus on lähettetty onnistuneesti.","Viesti" , JOptionPane.PLAIN_MESSAGE);
	            			dispose();
	            		} else {
	            			JOptionPane.showMessageDialog(itse, "Vastauksen lähetys epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
	            		}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(itse, "Vastauksen lähetys epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
					}
            	}
            	else {
            		JOptionPane.showMessageDialog(itse, "Jokin tieto puuttu tai ei kelpaa", "Virhe" , JOptionPane.WARNING_MESSAGE);
            	}	
            }
        });
        
        //koostetaan panelit
        nimisisus.add(Box.createRigidArea(new Dimension(10,1)));
        nimisisus.add(nimi);
        nimisisus.add(nimirivi);
        nimisisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        sijaintisisus.add(Box.createRigidArea(new Dimension(10,1)));
        sijaintisisus.add(sijainti);
        sijaintisisus.add(sijaintirivi);
        sijaintisisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        hintasisus.add(Box.createRigidArea(new Dimension(10,1)));
        hintasisus.add(hinta);
        hintasisus.add(hintarivi);
        hintasisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        kuvaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        kuvaussisus.add(kuvaus);
        kuvaussisus.add(kuvausalue);
        kuvaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        vastaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        vastaussisus.add(vastaus);
        vastaussisus.add(vastausalue);
        vastaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        yhteyssisus.add(Box.createRigidArea(new Dimension(10,1)));
        yhteyssisus.add(yhteys);
        yhteyssisus.add(yhteysrivi);
        yhteyssisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        nappisisus.add(Box.createRigidArea(new Dimension(20,40)));
        nappisisus.add(laheta);
        nappisisus.add(Box.createHorizontalGlue());
        nappisisus.add(peruuta);
        nappisisus.add(Box.createRigidArea(new Dimension(20,40)));
        
        //koostetaan pääpaneeli
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(nimisisus);
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(sijaintisisus);
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(hintasisus);
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(kuvaussisus);
        sisus2.add(Box.createVerticalGlue());
        
        sisus3.add(Box.createVerticalGlue());
        sisus3.add(vastaussisus);
        sisus3.add(Box.createVerticalGlue());
        sisus3.add(yhteyssisus);
        sisus3.add(Box.createVerticalGlue());
        
        sisus.add(Box.createVerticalGlue());
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(sisus2);
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(Box.createVerticalGlue());
        sisus.add(sisus3);
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(Box.createVerticalGlue());
        sisus.add(nappisisus);

        this.getContentPane().add(sisus);
        
        // Haetaan ilmoituksen tiedot tietokannasta
        try {
			Message valittu = MessageDatabase.getInstance().getMessage(tuote);
			if (valittu == null) {
				JOptionPane.showMessageDialog(itse, "Valittua ilmoitusta ei löytynyt. Se on saatettu poistaa.", "Virhe" , JOptionPane.WARNING_MESSAGE);
				dispose();
			}
			else {
				nimirivi.setText(valittu.getName());
				sijaintirivi.setText(valittu.getLocation());
				hintarivi.setText(String.valueOf(valittu.getPrice()));
				kuvausrivi.setText(valittu.getMessage());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(itse, "Ilmoituksen tietojen hakemisessa tapahtui virhe", "Virhe" , JOptionPane.ERROR_MESSAGE);
			dispose();
		}
        
        //Aseteaan raksin toiminnallisuus
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	dispose();
            }
        });
        
    }
}
