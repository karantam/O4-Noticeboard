package o4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ShowMessage extends JFrame{
	
	private JButton kirjaudu;
    private JButton peruuta;
    private JLabel nimi;
    private JTextField nimirivi;
    private JLabel sijainti;
    private JTextField sijaintirivi;
    private JLabel hinta;
    private JTextField hintarivi;
    private JLabel kuvaus;
    private JTextArea kuvausrivi;
    private JLabel huomautus;
    private JPanel sisus;
    private JPanel sisus2;
    private JPanel sisus3;
    private JPanel nimisisus;
    private JPanel sijaintisisus;
    private JPanel hintasisus;
    private JPanel kuvaussisus;
    private JScrollPane kuvausalue;
    private JPanel nappisisus;
    private JFrame itse; //Talletuspaikka ikkunan viittaukselle itseensä
    
    
    public ShowMessage( MainWindow  paa, String tuote) {
 
        super("Näytä ilmoitus");
        itse = this;    //asetetaan itseviite talteen kuuntelijaa varten.
        setSize(500, 400);
        itse.setMinimumSize(new Dimension(470,350));
        setLocationRelativeTo(null);
        
        
        nimi = new JLabel ("Tuotteen nimi:  ");
        nimi.setMinimumSize(nimi.getPreferredSize());
        nimi.setMaximumSize(nimi.getPreferredSize());
        nimirivi = new JTextField (30);
        nimirivi.setMinimumSize(nimirivi.getPreferredSize());
        nimirivi.setMaximumSize(nimirivi.getPreferredSize());
        nimirivi.setText("NIMI");
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
        kuvausrivi.setText("Kuvaus");
        kuvausrivi.setEditable(false);
        kuvausrivi.setOpaque(false);
        kuvausalue = new JScrollPane(kuvausrivi);
        kuvausalue.setMinimumSize(kuvausalue.getPreferredSize());
        kuvausalue.setMaximumSize(kuvausalue.getPreferredSize());
        kuvausalue.setBorder(BorderFactory.createLineBorder(Color.black));
        
        huomautus = new JLabel ("Kirjaudu sisään vastataksesi ilmoituksiin.");
        huomautus.setMinimumSize(huomautus.getPreferredSize());
        huomautus.setMaximumSize(huomautus.getPreferredSize());
        
        kirjaudu = new JButton ("Kirjaudu sisään");
        peruuta = new JButton ("Peruuta");
        peruuta.setPreferredSize(kirjaudu.getPreferredSize());
        
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
        //hintasisus.setBackground(Color.orange);
        
        kuvaussisus = new JPanel();
        BoxLayout kuvausbox = new BoxLayout(kuvaussisus, BoxLayout.X_AXIS);
        kuvaussisus.setLayout(kuvausbox);
        
        sisus3 = new JPanel();
        sisus3.setLayout(new  BoxLayout(sisus3, BoxLayout.X_AXIS));
        sisus3.setBorder(BorderFactory.createRaisedBevelBorder());
        
        nappisisus = new JPanel();
        BoxLayout nappibox = new BoxLayout(nappisisus, BoxLayout.X_AXIS);
        nappisisus.setLayout(nappibox);
        
        //Asetetaan nappien toiminnallisuudet
        
        //peruuta sulkee ikkunan
        peruuta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	dispose();
            }
        });
        
        //kirjaudu avaa kirjaudu sisään ikkunan
        kirjaudu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	KirjauduSisaan kirjaudu = new KirjauduSisaan(paa, 1,tuote);
            	dispose();
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
        
        sisus3.add(Box.createRigidArea(new Dimension(10,40)));
        sisus3.add(huomautus);
        sisus3.add(Box.createRigidArea(new Dimension(65,1)));
        sisus3.add(kirjaudu);
        sisus3.add(Box.createRigidArea(new Dimension(10,40)));
        
        nappisisus.add(Box.createRigidArea(new Dimension(10,40)));
        nappisisus.add(Box.createHorizontalGlue());
        nappisisus.add(peruuta);
        nappisisus.add(Box.createRigidArea(new Dimension(10,40)));
        nappisisus.setMinimumSize(sisus3.getPreferredSize());
        nappisisus.setMaximumSize(sisus3.getPreferredSize());
        
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
        sisus.add(sisus3);
        sisus.add(Box.createRigidArea(new Dimension(1,10)));
        sisus.add(Box.createVerticalGlue());
        sisus.add(nappisisus);

        this.getContentPane().add(sisus);
        
        // Haetaan ilmoituksen tiedot tietokannasta
        try {
			Message valittu = MessageDatabase.getInstance().getMessage(tuote);
			nimirivi.setText(valittu.getName());
			sijaintirivi.setText(valittu.getLocation());
			hintarivi.setText(String.valueOf(valittu.getPrice()));
			kuvausrivi.setText(valittu.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(itse, "Ilmoituksen tietojen hakemisessa tapahtui virhe", "Virhe" , JOptionPane.ERROR_MESSAGE);
			dispose();
		}
        
        //Asetetaan raksin toiminnallisuus
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	dispose();
            }
        });
        
    }
}