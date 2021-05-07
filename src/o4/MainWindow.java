package o4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainWindow extends JFrame{

	private JButton ilmoitus;
    private JButton omat;
    private JButton poistu;
    private JButton kirjaudu;
    private JButton hae;
    private JLabel kayttaja;
    private JLabel nimiteksti;
    private JLabel sijaintiteksti;
    private JLabel hintaminteksti;
    private JLabel hintamaxteksti;
    private JLabel ohje;
    private JTextField nimi;
    private JTextField sijainti;
    private JTextField hintamin;
    private JTextField hintamax;
    private JTextArea huomautus;
    private JPanel sisus;
    private JPanel sisus2;
    private JPanel nappisisus;
    private JPanel nappisisus2;
    private JPanel hakusisus;
    private JPanel listasisus;
    private JPanel kayttajasisus;
    private JPanel ohjesisus;
    private JTable lista;
    private JScrollPane listakehys;
    private JFrame itse; //Talletuspaikka ikkunan viittaukselle itseens�
    
    private Object[][] data = null;
    
    
    public MainWindow() {
 
        super("Ohjelma");
        itse = this;    //asetetaan itseviite talteen kuuntelijaa varten.
        setSize(1200, 600);
        itse.setMinimumSize(new Dimension(800,400));
        setLocationRelativeTo(null);
        
        ilmoitus = new JButton ("Jätä Ilmoitus");
        ilmoitus.setEnabled(false);
        omat = new JButton ("Omat ilmoitukset");
        omat.setEnabled(false);
        poistu = new JButton("Poistu");
        kirjaudu = new JButton("Kirjaudu sisään");
        hae = new JButton("Hae");
        omat.setMaximumSize(omat.getPreferredSize());
        ilmoitus.setMaximumSize(omat.getPreferredSize());
        poistu.setMaximumSize(omat.getPreferredSize());
        kirjaudu.setMinimumSize(omat.getPreferredSize());
        kirjaudu.setPreferredSize(omat.getPreferredSize());
        kirjaudu.setMaximumSize(omat.getPreferredSize());
        hae.setMinimumSize(omat.getPreferredSize());
        hae.setPreferredSize(omat.getPreferredSize());
        hae.setMaximumSize(omat.getPreferredSize());
        hae.setFocusable(false);
        
        //paneli = new JPanel(new FlowLayout());
        sisus = new JPanel();
        sisus.setLayout(new BoxLayout(sisus, BoxLayout.X_AXIS));
        
        sisus2 = new JPanel();
        sisus2.setLayout(new BoxLayout(sisus2, BoxLayout.Y_AXIS));
       
        nappisisus = new JPanel();
        nappisisus.setLayout(new BoxLayout(nappisisus, BoxLayout.Y_AXIS));
        //nappisisus.setBackground(Color.cyan);
        
        nappisisus2 = new JPanel();
        nappisisus2.setLayout(new BoxLayout(nappisisus2, BoxLayout.Y_AXIS));
        //nappisisus2.setBackground(Color.MAGENTA);
        nappisisus2.setBorder(BorderFactory.createRaisedBevelBorder());
        
        hakusisus = new JPanel();
        hakusisus.setLayout(new BoxLayout(hakusisus, BoxLayout.X_AXIS));
        //hakusisus.setBackground(Color.red);
        hakusisus.setBorder(BorderFactory.createRaisedBevelBorder());
        
        listasisus = new JPanel();
        listasisus.setLayout(new BoxLayout(listasisus, BoxLayout.X_AXIS));
        //listasisus.setBackground(Color.orange);
        
        kayttajasisus = new JPanel();
        kayttajasisus.setLayout(new BoxLayout(kayttajasisus, BoxLayout.X_AXIS));
        //kayttajasisus.setBackground(Color.yellow);
        //kayttajasisus.setBorder(BorderFactory.createRaisedBevelBorder());
        
        ohjesisus = new JPanel();
        ohjesisus.setLayout(new BoxLayout(ohjesisus, BoxLayout.X_AXIS));
        //kayttajasisus.setBackground(Color.yellow);
        //ohjesisus.setBorder(BorderFactory.createLoweredBevelBorder());
        
        ohje = new JLabel("Avaa ilmoitus klikaamalla sitä");
        
        kayttaja =  new JLabel("WWWWWWWWWWWWWWWWWWWWWW", SwingConstants.CENTER);
        kayttaja.setMinimumSize(kayttaja.getPreferredSize());
        kayttaja.setPreferredSize(kayttaja.getPreferredSize());
        kayttaja.setMaximumSize(omat.getPreferredSize());
        kayttaja.setBorder(BorderFactory.createLoweredBevelBorder());
        kayttaja.setText(null);
        
        nimiteksti = new JLabel("Hakusana: ");
        
        sijaintiteksti = new JLabel("Sijainti: ");
        
        hintaminteksti = new JLabel("Hinta Min:");
        
        hintamaxteksti = new JLabel("Hinta Max: ");
        
        nimi = new JTextField(15);
        nimi.setMaximumSize(nimi.getPreferredSize());
        //nimi.setText("Hakusana");
        
        sijainti = new JTextField(8);
        sijainti.setMaximumSize(nimi.getPreferredSize());
        //sijainti.setText("Sijainti");
        
        hintamin = new JTextField(8);
        hintamin.setMaximumSize(nimi.getPreferredSize());
        //hintamin.setText("Hinta: Min");
        
        hintamax = new JTextField(8);
        hintamax.setMaximumSize(nimi.getPreferredSize());
        //hintamax.setText("Hinta: Max");
        
        huomautus = new JTextArea("Kirjaudu sisään \njättääksesi ilmoituksia");
        huomautus.setEditable(false);
        huomautus.setOpaque(false);
        huomautus.setMinimumSize(huomautus.getPreferredSize());
        huomautus.setMaximumSize(huomautus.getPreferredSize());
        
        String[] otsikot = {"Tuotteen nimi", "Sijainti", "Hinta"};
        /*Object[][] data = {
        		{"Auto", "Oulu", "1000.00"},
        		{"Puhelin", "Kemi", "50.00"}
        };*/
        //Object[][] data = null;
        //haetaan ilmoitukset tietokannasta
        try {
			List<Message> ilmoitukset = MessageDatabase.getInstance().getAllMessages();
			data = new Object[ilmoitukset.size()][3];
			for(int i = 0; i < ilmoitukset.size(); i++) {
				data[i][0] = ilmoitukset.get(i).getName();
				data[i][1] = ilmoitukset.get(i).getLocation();
				data[i][2] = ilmoitukset.get(i).getPrice();
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(itse, "Ilmoituksien hakeminen tietokannasta epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
		}
        
        
        lista = new JTable(new TableModel(otsikot, data));
        lista.getTableHeader().setReorderingAllowed(false);
        
        listakehys = new JScrollPane(lista);
        lista.setFillsViewportHeight(true);
        
      //Nappien toiminnallisuudet
        
        // Poistu nappi sulkee sovelluksen
        poistu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	System.out.println("Shutting down Program...");
                try {
					MessageDatabase.getInstance().closeDB();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                System.out.println("Program has been shutdown.");
            	System.exit(0);
            }
        });
        
        // Kirjaudu nappi kirjaa käyttäjän sisään tai ulos
        kirjaudu.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if (kayttaja.getText() != null && !kayttaja.getText().isBlank()) {
        			ilmoitus.setEnabled(false);
        			omat.setEnabled(false);
        			kirjaudu.setText("Kirjaudu sisään");
        			kayttaja.setText(null);
        		} else {
        			KirjauduSisaan kirjaudu = new KirjauduSisaan((MainWindow) itse, 0 , "");
        			//kirjaudu.setVisible(true);
        			//kirjautuminen("Käyttäjä");
        			/*ilmoitus.setEnabled(true);
        			omat.setEnabled(true);
        			kirjaudu.setText("Kirjaudu ulos");
        			kayttaja.setText("Käyttäjä");*/
        		}
            }
        });
        
        // Hae nappi rajaa näytettäviä ilmoituksia hakutermien mukaan
        hae.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String hakunimi;
    			String hakusijainti;
    			Double min = 0.0;
    			Double max = 0.0;
    			
    			hakunimi = nimi.getText().toLowerCase();
    			hakusijainti = sijainti.getText().toLowerCase();
    			if (!hintamin.getText().isBlank()) {
    				try {
    					min = Double.parseDouble(hintamin.getText());
    				}
        			catch(Exception exception) {
        				JOptionPane.showMessageDialog(itse, "Minimi hinnnan täytyy olla tyhjä tai numero","Virhe" , JOptionPane.WARNING_MESSAGE);
        				return;
        			}
        		}
    			if (!hintamax.getText().isBlank()) {
    				try {
    					max = Double.parseDouble(hintamax.getText());
    				}
        			catch(Exception exception) {
        				JOptionPane.showMessageDialog(itse, "Maksimi hinnnan täytyy olla tyhjä tai numero","Virhe" , JOptionPane.WARNING_MESSAGE);
        				return;
        			}
        		}
    			
    			//haetaan ilmoitukset tietokannasta
    	        try {
    				List<Message> ilmoitukset = MessageDatabase.getInstance().getAllMessages();
    				data = new Object[ilmoitukset.size()][3];
    				for(int i = 0; i < ilmoitukset.size(); i++) {
    					data[i][0] = ilmoitukset.get(i).getName();
    					data[i][1] = ilmoitukset.get(i).getLocation();
    					data[i][2] = ilmoitukset.get(i).getPrice();
    				}
    			} catch (SQLException e2) {
    				// TODO Auto-generated catch block
    				e2.printStackTrace();
    				JOptionPane.showMessageDialog(itse, "Ilmoituksien hakeminen tietokannasta epäonnistui", "Virhe" , JOptionPane.ERROR_MESSAGE);
    			}
    			
    			if (hakunimi.isBlank() && hakusijainti.isBlank() && min == 0 && max== 0) {
    				JOptionPane.showMessageDialog(itse, "Hakuehdot ovat tyhjät","Huomautus" , JOptionPane.PLAIN_MESSAGE);
    				lista.setModel(new TableModel(otsikot, data));
    			} else if (data.length == 0) {
    				lista.setModel(new TableModel(otsikot, data));
    			}else {
    				Object[][] data2 = CopyArray(data);
    				Object[][] data3;
    				if (!hakunimi.isBlank()) {
    					data3 = new Object[data.length][data[1].length];
    					int k = 0;
    					for(int i = 0; i < data2.length; i++) {
    						if (((String) data2[i][0]).toLowerCase().contains(hakunimi)){
    							for (int j = 0; j < data2[0].length; j++){
    								data3[k][j] = data2[i][j];
    							}
    							k = k+1;
    						}
    					}
    					System.out.println("Nimi");
    					data2 = CopyArray(data3);
    				}
    				if (!hakusijainti.isBlank()) {
    					data3 = new Object[data.length][data[1].length];
    					int k = 0;
    					for(int i = 0; i < data2.length; i++) {
    						if (((String) data2[i][1]).toLowerCase().contains(hakusijainti)){
    							for (int j = 0; j < data2[0].length; j++){
    								data3[k][j] = data2[i][j];
    							}
    							k = k+1;
    						}
    					}
    					System.out.println("Sijainti");
    					data2 = CopyArray(data3);
    				}
    				if (min > 0) {
    					data3 = new Object[data.length][data[1].length];
    					int k = 0;
    					for(int i = 0; i < data2.length; i++) {
    						if ((Double.parseDouble((String) data2[i][2])) >= min){
    							for (int j = 0; j < data2[0].length; j++){
    								data3[k][j] = data2[i][j];
    							}
    							k = k+1;
    						}
    					}
    					data2 = CopyArray(data3);
    				}
    				if (max > 0) {
    					data3 = new Object[data.length][data[1].length];
    					int k = 0;
    					for(int i = 0; i < data2.length; i++) {
    						if ((Double.parseDouble((String) data2[i][2])) <= max){
    							for (int j = 0; j < data2[0].length; j++){
    								data3[k][j] = data2[i][j];
    							}
    							k = k+1;
    						}
    					}
    					data2 = CopyArray(data3);
    				}
    				lista.setModel(new TableModel(otsikot, data2));
    			}
            }
        });
        
        // Ilmoitus nappi avaa jätä ilmoitus ikkunan
        ilmoitus.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		CreateMessage jata = new CreateMessage(kayttaja.getText());
                jata.setVisible(true);
            }
        });
        
     // Ilmoitus nappi avaa jätä ilmoitus ikkunan
        omat.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		OmatIlmoitukset jata = new OmatIlmoitukset(kayttaja.getText());
            }
        });
        
        // Näyttää listasta valitun ilmoituksen omassa ikkunassaan
        lista.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	//  Tämä if lauseke varmistaa että ikkuna avautuu vain kerran, kun se valitaan hiirellä
            	// ja näppäimistöllä valinnan liikuttaminen ei avaa ilmoitusta
            	if (event.getValueIsAdjusting() && lista.getSelectedRow() != -1) {
            		int column = 0;
            		int row = lista.getSelectedRow();
            		String tuote = lista.getModel().getValueAt(row, column).toString();
            		if (kayttaja.getText() != null && !kayttaja.getText().isBlank()) {
            			AnswerMessage vastaa = new AnswerMessage(tuote, kayttaja.getText());
            			vastaa.setVisible(true);
            		} else {
            			ShowMessage nayta = new ShowMessage((MainWindow) itse, tuote);
            			nayta.setVisible(true);
            		}
            	}
            	lista.clearSelection();
            }
        });
        
        // Painaa hae nappia jos fokus on tekstikentässä
        nimi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		hae.doClick();
        	}
        });
        
        sijainti.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		hae.doClick();
        	}
        });
        
        hintamin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		hae.doClick();
        	}
        });
        
        hintamax.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		hae.doClick();
        	}
        });
        
        //koostetaan panelit
        nappisisus2.add(Box.createRigidArea(new Dimension(1,10)));
        nappisisus2.add(ilmoitus);
        ilmoitus.setAlignmentX(CENTER_ALIGNMENT);
        nappisisus2.add(Box.createRigidArea(new Dimension(1,10)));
        nappisisus2.add(omat);
        omat.setAlignmentX(CENTER_ALIGNMENT);
        nappisisus2.add(Box.createRigidArea(new Dimension(1,10)));
        nappisisus2.add(huomautus);
        huomautus.setAlignmentX(CENTER_ALIGNMENT);
        nappisisus2.add(Box.createRigidArea(new Dimension(150,10)));
        
        kayttajasisus.add(Box.createHorizontalGlue());
        kayttajasisus.add(Box.createHorizontalGlue());
        kayttajasisus.add(Box.createHorizontalGlue());
        kayttajasisus.add(Box.createHorizontalGlue());
        kayttajasisus.add(Box.createHorizontalGlue());
        kayttajasisus.add(kayttaja);
        kayttaja.setAlignmentX(CENTER_ALIGNMENT);
        kayttajasisus.add(Box.createRigidArea(new Dimension(20,1)));
        kayttajasisus.add(kirjaudu);
        kayttajasisus.add(Box.createRigidArea(new Dimension(14,59)));
        //kayttajasisus.add(Box.createRigidArea(new Dimension(10,55)));
        
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(nimiteksti);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(nimi);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(Box.createHorizontalGlue());
        hakusisus.add(sijaintiteksti);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(sijainti);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(Box.createHorizontalGlue());
        hakusisus.add(hintaminteksti);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(hintamin);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(Box.createHorizontalGlue());
        hakusisus.add(hintamaxteksti);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(hintamax);
        hakusisus.add(Box.createRigidArea(new Dimension(10,1)));
        hakusisus.add(Box.createHorizontalGlue());
        hakusisus.add(hae);
        hakusisus.add(Box.createRigidArea(new Dimension(10,55)));
        hakusisus.setFocusCycleRoot(true);
        
        listasisus.add(listakehys);
        
        ohjesisus.add(Box.createRigidArea(new Dimension(10,1)));
        ohjesisus.add(ohje);
        ohjesisus.add(Box.createHorizontalGlue());
        
        //koostetaan pääpaneeli
        nappisisus.add(Box.createRigidArea(new Dimension(1,10)));
        nappisisus.add(nappisisus2);
        nappisisus.add(Box.createVerticalGlue());
        nappisisus.add(poistu);
        poistu.setAlignmentX(CENTER_ALIGNMENT);
        nappisisus.add(Box.createRigidArea(new Dimension(1,10)));
        
        //sisus2.add(Box.createVerticalGlue());
        sisus2.add(Box.createRigidArea(new Dimension(1,10)));
        sisus2.add(kayttajasisus);
        sisus2.add(Box.createRigidArea(new Dimension(1,10)));
        //sisus2.add(Box.createVerticalGlue());
        sisus2.add(hakusisus);
        sisus2.add(Box.createRigidArea(new Dimension(1,10)));
        sisus2.add(Box.createVerticalGlue());
        sisus2.add(ohjesisus);
        sisus2.add(Box.createRigidArea(new Dimension(1,10)));
        sisus2.add(listasisus);
        sisus2.add(Box.createRigidArea(new Dimension(1,10)));
        //sisus2.add(Box.createVerticalGlue());
        
        sisus.add(Box.createRigidArea(new Dimension(10,1)));
        sisus.add(nappisisus);
        sisus.add(Box.createRigidArea(new Dimension(10,1)));
        sisus.add(Box.createHorizontalGlue());
        sisus.add(sisus2);
        sisus.add(Box.createRigidArea(new Dimension(10,1)));

        this.getContentPane().add(sisus);
        
        //lis�t��n ikkunaan viel� toiminnallisuus, joka sulkee ohjelman
        //k�ytet��n t�ss�kin sis�luokkaa.
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	System.out.println("Shutting down Program...");
                try {
					MessageDatabase.getInstance().closeDB();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.out.println("Program has been shutdown.");
                System.exit(0);
            }
        });
        
    }
    
    private Object[][] CopyArray(Object[][] taulu){
    	int korkeus = 0;
    	int leveys = 0;
    	for(int i = 0; i < taulu.length; i++) {
    		if (taulu[i][0] != null) {
    			korkeus = korkeus + 1;
    		}
    	}
    	if (korkeus != 0) {
    		leveys = 3;
    	}
    	Object[][] taulu2 = new Object[korkeus][leveys];
    	int k = 0;
    	for(int i = 0; i < taulu.length; i++) {
    		if (taulu[i][0] != null) {
    			for (int j = 0; j < taulu[0].length; j++){
        			taulu2[k][j] = taulu[i][j];
        		}
    			k = k+1;
    		}
    	}
    	return taulu2;
    }
    
    public void kirjautuminen(String nimi) {
    	ilmoitus.setEnabled(true);
		omat.setEnabled(true);
		kirjaudu.setText("Kirjaudu ulos");
		kayttaja.setText(nimi);
    }
    
    public String kayttajanimi() {
		String username = kayttaja.getText();
		return username;
    }
    
     /*public static void main(String[] args) {
    	 MainWindow sovellus = new MainWindow();
         sovellus.setVisible(true);  
    }*/
}   