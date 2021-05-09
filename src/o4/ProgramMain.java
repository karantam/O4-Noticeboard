package o4;

import java.sql.SQLException;

/*
 * main method of the program
 */

public class ProgramMain {
    public static void main(String[] args) throws Exception {
        System.out.println("Launching Program...");
        System.out.println("Initializing database ...");
        //Opening a database connection
        MessageDatabase.getInstance().open();
        System.out.println("Starting Program!");
        //Launching the MainWindow
        MainWindow sovellus = new MainWindow();
        sovellus.setVisible(true);
    }
}