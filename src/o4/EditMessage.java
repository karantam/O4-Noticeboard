package o4;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

public class EditMessage extends JFrame{
	
    private JButton tallenna;
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
    private JPanel sisus;
    private JPanel sisus2;
    private JPanel nimisisus;
    private JPanel sijaintisisus;
    private JPanel hintasisus;
    private JPanel kuvaussisus;
    private JPanel nappisisus;
    private JFrame itse; //Talletuspaikka ikkunan viittaukselle itseensä
    
    
    public EditMessage(Message ilmoitus) {
 
        super("Muokkaa ilmoitusta");
        itse = this;    //asetetaan itseviite talteen kuuntelijaa varten.
        setSize(500, 400);
        itse.setMinimumSize(new Dimension(400,300));
        setLocationRelativeTo(null);
        
        
        nimi = new JLabel ("Tuotteen nimi:  ");
        nimi.setMinimumSize(nimi.getPreferredSize());
        nimi.setMaximumSize(nimi.getPreferredSize());
        nimirivi = new JTextField (30);
        nimirivi.setMinimumSize(nimirivi.getPreferredSize());
        nimirivi.setMaximumSize(nimirivi.getPreferredSize());
        
        sijainti = new JLabel ("Sijainti:               ");
        sijainti.setMinimumSize(nimi.getPreferredSize());
        sijainti.setMaximumSize(nimi.getPreferredSize());
        sijaintirivi = new JTextField (30);
        sijaintirivi.setMinimumSize(sijaintirivi.getPreferredSize());
        sijaintirivi.setMaximumSize(sijaintirivi.getPreferredSize());
        
        hinta = new JLabel ("Hinta:                  ");
        hinta.setMinimumSize(nimi.getPreferredSize());
        hinta.setMaximumSize(nimi.getPreferredSize());
        hintarivi = new JTextField (30);
        hintarivi.setMinimumSize(hintarivi.getPreferredSize());
        hintarivi.setMaximumSize(hintarivi.getPreferredSize());
        
        kuvaus = new JLabel ("Kuvaus:             ");
        kuvaus.setMinimumSize(nimi.getPreferredSize());
        kuvaus.setMaximumSize(nimi.getPreferredSize());
        kuvausrivi = new JTextArea (6,30);
        kuvausrivi.setLineWrap(true);
        kuvausalue = new JScrollPane(kuvausrivi);
        kuvausalue.setMinimumSize(kuvausalue.getPreferredSize());
        kuvausalue.setMaximumSize(kuvausalue.getPreferredSize());
        
        tallenna = new JButton ("tallenna");
        peruuta = new JButton ("Peruuta");
        peruuta.setPreferredSize(tallenna.getPreferredSize());
        
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
        
        nappisisus = new JPanel();
        BoxLayout nappibox = new BoxLayout(nappisisus, BoxLayout.X_AXIS);
        nappisisus.setLayout(nappibox);
        
        //Asetetaan nappien toimnnallisuudet.
        
        //peruutta sulkee ikkunan
        peruuta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            	OmatIlmoitukset jata = new OmatIlmoitukset(ilmoitus.getUser());

            }
        });
        
        //tallenna tallentaa muutokset tietokantaan
        tallenna.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String nimi = nimirivi.getText();
            	String sijainti = sijaintirivi.getText();
            	String hinta = hintarivi.getText();
            	String kuvaus = kuvausrivi.getText();
            	double hintaluku;
            	try {
            		hintaluku = Double.parseDouble(hinta);
            		if (!nimi.isBlank() && nimi.length() > 3 && !sijainti.isBlank() && sijainti.length() > 2 && hintaluku > 0 && !kuvaus.isBlank() && kuvaus.length() > 5) {
            			boolean value = false;
            			boolean value2 = false;
            			//Tarkistetaan onko tuotteen nimi jo käytössä ellei se ole sama kuin vanha tuotteen nimi 
            			if (nimi.equals(ilmoitus.getName())) {
            				value = true;
            			} else {
            				value = MessageDatabase.getInstance().checkMessage(nimi);
            			}
            			if (value) {
            				Message ilmoitus_2 = new Message(ilmoitus.getUser(), nimi, sijainti, hintaluku, kuvaus);
            				value2 = MessageDatabase.getInstance().editMessage(ilmoitus_2, ilmoitus.getName());
            				if (value2) {
            					JOptionPane.showMessageDialog(itse, "Muutokset tallennettu onnistuneesti.","Viesti" , JOptionPane.PLAIN_MESSAGE);
                				dispose();
                				OmatIlmoitukset jata = new OmatIlmoitukset(ilmoitus.getUser());
                			} else {
                				JOptionPane.showMessageDialog(itse, "Muutosten tallennus epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
                			}
            			} else {
            				JOptionPane.showMessageDialog(itse, "Muutoksia ei voitu tallentaa, koska tuotteen uusi nimi on jo käytössä", "Virhe" , JOptionPane.WARNING_MESSAGE);
            			}
            		}
            		else {
            			JOptionPane.showMessageDialog(itse, "Jokin tieto puuttu tai ei kelpaa", "Virhe" , JOptionPane.WARNING_MESSAGE);
            		}
            	}
            	catch(Exception exception) {
            		JOptionPane.showMessageDialog(itse, "Hinnnan täytyy olla numero","Virhe" , JOptionPane.WARNING_MESSAGE);
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
        
        nappisisus.add(Box.createRigidArea(new Dimension(20,40)));
        nappisisus.add(tallenna);
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
        
        sisus.add(Box.createVerticalGlue());
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(sisus2);
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(Box.createVerticalGlue());
        sisus.add(nappisisus);

        this.getContentPane().add(sisus);
        
        // Haetaan ilmoituksen tiedot annetusta oliosta
        nimirivi.setText(ilmoitus.getName());
		sijaintirivi.setText(ilmoitus.getLocation());
		hintarivi.setText(String.valueOf(ilmoitus.getPrice()));
		kuvausrivi.setText(ilmoitus.getMessage());
        
        //Asetetaan raksin toiminnallisuus
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
                OmatIlmoitukset jata = new OmatIlmoitukset(ilmoitus.getUser());

            }
        });
        
    }
}