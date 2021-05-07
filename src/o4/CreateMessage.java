package o4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateMessage extends JFrame{
	
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
    private JPanel sisus;
    private JPanel sisus2;
    private JPanel nimisisus;
    private JPanel sijaintisisus;
    private JPanel hintasisus;
    private JPanel kuvaussisus;
    private JScrollPane kuvausalue;
    private JPanel nappisisus;
    private JFrame itse; //Talletuspaikka ikkunan viittaukselle itseens�
    
    
    public CreateMessage(String lahettaja) {
 
        super("Jätä ilmoitus");
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
        //kuvausrivi.setMaximumSize(kuvausrivi.getPreferredSize());
        kuvausrivi.setLineWrap(true);
        kuvausalue = new JScrollPane(kuvausrivi);
        kuvausalue.setMinimumSize(kuvausalue.getPreferredSize());
        kuvausalue.setMaximumSize(kuvausalue.getPreferredSize());
        
        laheta = new JButton ("Lähetä");
        peruuta = new JButton ("Peruuta");
        laheta.setPreferredSize(peruuta.getPreferredSize());
        
        //paneli = new JPanel(new FlowLayout());
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
        //nimisisus.setBackground(Color.blue);
        
        sijaintisisus = new JPanel();
        BoxLayout sijaintibox = new BoxLayout(sijaintisisus, BoxLayout.X_AXIS);
        sijaintisisus.setLayout(sijaintibox);
        //sijaintisisus.setBackground(Color.red);
        
        hintasisus = new JPanel();
        BoxLayout hintabox = new BoxLayout(hintasisus, BoxLayout.X_AXIS);
        hintasisus.setLayout(hintabox);
        //hintasisus.setBackground(Color.orange);
        
        kuvaussisus = new JPanel();
        BoxLayout kuvausbox = new BoxLayout(kuvaussisus, BoxLayout.X_AXIS);
        kuvaussisus.setLayout(kuvausbox);
        //kuvaussisus.setBackground(Color.yellow);
        
        nappisisus = new JPanel();
        BoxLayout nappibox = new BoxLayout(nappisisus, BoxLayout.X_AXIS);
        nappisisus.setLayout(nappibox);
        //nappisisus.setBackground(Color.cyan);
        
        //Lis�t��n napille toiminnallisuus nimett�m�n sis�luokan avulla.
        //Toiminnallisuus on sama kuin EchoListener-luokassa.
        peruuta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//System.exit(0);
            	dispose();
            }
        });
        
        laheta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String nimi = nimirivi.getText();
            	String sijainti = sijaintirivi.getText();
            	String hinta = hintarivi.getText();
            	String kuvaus = kuvausrivi.getText();
            	double hintaluku;
            	try {
            		hintaluku = Double.parseDouble(hinta);
            		if (!nimi.isBlank() && nimi.length() > 3 && sijainti.length() > 2 && hintaluku > 0 && kuvaus.length() > 5) {
            			Message ilmoitus = new Message(lahettaja, nimi, sijainti, hintaluku, kuvaus);
            			boolean value = false;
            			boolean value2 = false;
            			value = MessageDatabase.getInstance().checkMessage(ilmoitus.getName());
            			if (value) {
            				value2 = MessageDatabase.getInstance().setMessage(ilmoitus);
            				if (value2) {
                				JOptionPane.showMessageDialog(itse, "Ilmoitus on lähettetty onnistuneesti.","Viesti" , JOptionPane.PLAIN_MESSAGE);
                				dispose();
                			} else {
                				JOptionPane.showMessageDialog(itse, "Ilmoituksen lähetys epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
                			}
            			} else {
            				JOptionPane.showMessageDialog(itse, "Ilmoitusta ei voitu lähettä, koska kyseinen tuotteen nimi on jo käytössä", "Virhe" , JOptionPane.WARNING_MESSAGE);
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
        //nimisisus.add(Box.createHorizontalGlue());
        //nimirivi.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nimisisus.add(nimirivi);
        nimisisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        sijaintisisus.add(Box.createRigidArea(new Dimension(10,1)));
        sijaintisisus.add(sijainti);
        //sijaintisisus.add(Box.createHorizontalGlue());
        sijaintisisus.add(sijaintirivi);
        sijaintisisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        hintasisus.add(Box.createRigidArea(new Dimension(10,1)));
        hintasisus.add(hinta);
        //hintasisus.add(Box.createHorizontalGlue());
        hintasisus.add(hintarivi);
        hintasisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        kuvaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        kuvaussisus.add(kuvaus);
        //kuvaussisus.add(Box.createHorizontalGlue());
        kuvaussisus.add(kuvausalue);
        kuvaussisus.add(Box.createRigidArea(new Dimension(10,1)));
        
        //nappisisus.add(Box.createHorizontalStrut( 20 ));
        nappisisus.add(Box.createRigidArea(new Dimension(20,40)));
        nappisisus.add(laheta);
        nappisisus.add(Box.createHorizontalGlue());
        nappisisus.add(peruuta);
        nappisisus.add(Box.createRigidArea(new Dimension(20,40)));
        
        //koostetaan pääpaneeli
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(nimisisus);
        //nimisisus.setAlignmentX_ALIGNMENT);
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
        
        //lis�t��n ikkunaan viel� toiminnallisuus, joka sulkee ohjelman
        //k�ytet��n t�ss�kin sis�luokkaa.
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //System.exit(0);
                dispose();
            }
        });
        
    }
    
     /*public static void main(String[] args) {
    	 CreateMessage sovellus = new CreateMessage();
        sovellus.setVisible(true);  
    }*/
}