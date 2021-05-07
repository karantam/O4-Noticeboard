package o4;

import java.sql.SQLException;

public class ProgramMain {
    public static void main(String[] args) throws Exception {
        System.out.println("Launching Program...");
        System.out.println("Initializing databse ...");
        MessageDatabase.getInstance().open();
        System.out.println("Starting Program!");
        MainWindow sovellus = new MainWindow();
        sovellus.setVisible(true);  
        /*Boolean running = true;
        while (Boolean.TRUE.equals(running)) {
        	String shutdown = System.console().readLine();
        	if (shutdown.equals("/quit")) {
        		running = false;
        	} else {
        		System.out.println("Type /quit to shut down the chatserver");
        	}
        System.out.println("Shutting down Program...");
        MessageDatabase.getInstance().closeDB();
        System.out.println("Program has been shutdown.");
            }*/
    }

}
