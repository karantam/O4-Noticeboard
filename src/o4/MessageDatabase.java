package o4;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MessageDatabase {
	
	private Connection dbConnection = null;
    private static MessageDatabase singleton = null;

    public static synchronized MessageDatabase getInstance() {
        if (null == singleton) {
            singleton = new MessageDatabase();
        }
        return singleton;
    }

    private MessageDatabase() {
    }

    /*
     * open method checks if the database exists and if it dosen't calls
     * initializeDatabase method to create it. Then it creates a connection to the
     * database
     */
    public void open() throws SQLException {
    	String dbName = "Ilmoitus.db";
        File dbFile = new File(dbName);
        boolean exists = dbFile.exists();
        String path = dbFile.getAbsolutePath();
        String database = "jdbc:sqlite:" + path;
        dbConnection = DriverManager.getConnection(database);
        if (!exists) {
            initializeDatabase();
        } else {
        	System.out.println("Using already existing database");
        }
    }

    /*
     * initializeDatabase method creates the database
     */
    private boolean initializeDatabase() throws SQLException {
        if (null != dbConnection) {
            // Creating three tables in the database. Registration for user information,
            // chat for chat messages and channel for created channels
            String registrationDB = "create table registration (user varchar(50) PRIMARY KEY, userpassword varchar(50) NOT NULL, useremail varchar(50) NOT NULL)";
            String messageDB = "create table message (id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(50) NOT NULL, messagename varchar(50) NOT NULL, location varchar(50) NOT NULL, price DOUBLE(10,2) NOT NULL, message varchar(800) NOT NULL)";
            String answerDB = "create table answer (messagename varchar(50) NOT NULL, username varchar(50) NOT NULL, answer varchar(800) NOT NULL, contact varchar(50) NOT NULL)";
            try (Statement createStatement = dbConnection.createStatement()) {
                createStatement.executeUpdate(registrationDB);
                createStatement.executeUpdate(messageDB);
                createStatement.executeUpdate(answerDB);
                System.out.println("DB successfully created");

                return true;
            } catch (SQLException e) {
            	System.out.println("ERROR: SQLException while creating the database");
            }
        }

        System.out.println("DB creation failed");
        return false;
    }

    /*
     * closeDB method closes the database connection
     */
    public void closeDB() throws SQLException {
        if (null != dbConnection) {
            dbConnection.close();
            System.out.println("closing database connection");
            dbConnection = null;
        }
    }
    
 // Methods for the registration table

    /*
     * setUser method saves a new user into the database if the username isn't
     * already in use.
     */
    public boolean setUser(User user) throws SQLException {
        // Registering a new user
        User existing = getUser(user.getUsername());
        // Checking if the user name is already in use
        if (existing.getUsername() != null && existing.getUsername().equals(user.getUsername())) {
        	System.out.println("ERROR: Invalid User");
            return false;
        } else {
            // Securing the password before saving it
            //String hashedPassword = Crypt.crypt(user.getPassword());
            // Saving the data of the new user into the database
            String setMessageString = "insert into registration VALUES('" + user.getUsername().replace("'", "''")
                    + "','" + user.getPassword() + "','" + user.getEmail().replace("'", "''") + "')";
            try (Statement createStatement = dbConnection.createStatement()) {
                createStatement.executeUpdate(setMessageString);
            } catch (SQLException e) {
            	System.out.println("ERROR: SQLException while adding user to database");
                return false;
            }
            return true;
        }
    }

    /*
     * getUser method retrieves user information from the database.
     */
    public User getUser(String user) throws SQLException {
        // retrieves username, password and email for the given user
        String getMessagesString = "select user, userpassword, useremail from registration where user =  '"
                + user.replace("'", "''") + "'";
        String username = null;
        String password = null;
        String email = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);
            while (rs.next()) {
                username = rs.getString("user");
                // This version of the password has been hashed
                password = rs.getString("userpassword");
                email = rs.getString("useremail");
            }

        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading user information from database");
        }
        return new User(username, password, email);

    }

    /*
     * checkUser method checks if the given username and password match the ones in
     * the database.
     */
    public boolean checkUser(String username, String password) throws SQLException {
        User existing = getUser(username);

        if (existing.getUsername() != null && existing.getUsername().equals(username) && existing.getPassword() != null
                && existing.getPassword().equals(existing.getPassword())) {
        	System.out.println("User OK");
            return true;
        } else {
        	System.out.println("ERROR: Invalid User");
            return false;
        }
    }

 // Methods for the message table

    /*
     * setMessage method saves a new message into the database.
     */
    public boolean setMessage(Message message) throws SQLException {
        String setMessageString;
        setMessageString = "insert into message  (username, messagename, location, price, message) VALUES('"
        		+ message.getUser().replace("'", "''") + "','" + message.getName().replace("'", "''") + "','"
                + message.getLocation().replace("'", "''") + "','" + message.getPrice() + "','" + message.getMessage().replace("'", "''") + "')";
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(setMessageString);
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while adding message to database");
            return false;
        }
        return true;
    }

    /*
     * editMessage method edits a previously saved message in the database
     */
    public boolean editMessage(Message message, String oldName) throws SQLException {
        String editMessageString;
        int messageId = getId(oldName);
        // Editing a message 
        editMessageString = "UPDATE message SET username = '" + message.getUser().replace("'", "''") + "', messagename = '"
        		+ message.getName().replace("'", "''") + "', location = '" + message.getLocation().replace("'", "''") 
        		+ "', price = '" + message.getPrice() + "', message = '" + message.getMessage().replace("'", "''") + "' WHERE id = '" + messageId + "'";
        
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(editMessageString);
            editAnswers(oldName, message.getName());
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException while editing a message in the database");
            return false;
        }
        return true;
    }

    /*
     * deleteMessage method deletes a previously saved message
     */
    public boolean deleteMessage(Message message) throws SQLException {
        String editMessageString;
        int messageId = getId(message.getName());
        // Deleting a message
        editMessageString = "DELETE FROM message  WHERE id = '" + messageId + "'";
        
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(editMessageString);
            deleteAnswers(message.getName());
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while deleting a message from the database");
            return false;
        }
        return true;
    }

    /*
     * getAllMessages method retrieves all messages from the database.
     */
    public List<Message> getAllMessages() throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        // Getting all the messages
        String getMessagesString;
        // Handling the case where there is no channel and no time
        getMessagesString = "select username, messagename, location, price, message from message order by LOWER(messagename) ASC";
        String username = null;
        String messagename = null;
        String location = null;
        double price = 0;
        String messagetext = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
                username = rs.getString("username");
                messagename =  rs.getString("messagename");
                location = rs.getString("location");
                price = rs.getDouble("price");
                messagetext = rs.getString("message");
                Message message = new Message(username, messagename, location, price, messagetext);
                messages.add(message);
            }

        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading messages from database");
        }
        return messages;

    }

    /*
     * getUsersMessages method retrieves all messages sent by the given user from the database.
     */
    public List<Message> getUsersMessages(String user) throws SQLException {
        ArrayList<Message> messages = new ArrayList<>();
        // Getting all the messages
        String getMessagesString;
        // Handling the case where there is no channel and no time
        getMessagesString = "select username, messagename, location, price, message from message WHERE username ='" + user.replace("'", "''") + "' order by LOWER(messagename) ASC";
        String username = null;
        String messagename = null;
        String location = null;
        double price = 0;
        String messagetext = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
                username = rs.getString("username");
                messagename =  rs.getString("messagename");
                location = rs.getString("location");
                price = rs.getDouble("price");
                messagetext = rs.getString("message");
                Message message = new Message(username, messagename, location, price, messagetext);
                messages.add(message);
            }

        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading messages from database");
        }
        return messages;

    }
    
    /*
     * getMessage method retrieves the message with messagename target from the database
     */
    public Message getMessage(String target) throws SQLException {
        Message message = null;
        // Getting all the messages
        String getMessagesString;
        // Handling the case where there is no channel and no time
        getMessagesString = "select username, messagename, location, price, message from message WHERE messagename ='" + target.replace("'", "''") + "'";
        String username = null;
        String messagename = null;
        String location = null;
        double price = 0;
        String messagetext = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
                username = rs.getString("username");
                messagename =  rs.getString("messagename");
                location = rs.getString("location");
                price = rs.getDouble("price");
                messagetext = rs.getString("message");
                message = new Message(username, messagename, location, price, messagetext);
            }

        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading message " + target + "from database");
        }
        return message;

    }
    
    /*
     * getId method retrieves a messages id number from the database.
     */
    public int getId(String messageName) throws SQLException {
        // Getting the id number
        String getMessagesString;
        getMessagesString = "select id from message WHERE messagename ='" + messageName.replace("'", "''") + "'";
        int idNumber = -1;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
                idNumber = rs.getInt("id");
            }
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading messages from database");
        }
        return idNumber;

    }
    
    /*
     * checkMessage method checks if a message with given name already exists
     * It returns true if the messagename is not in use.
     */
    public boolean checkMessage(String messageName) throws SQLException {
        // Getting the id number
        String getMessagesString;
        getMessagesString = "select messagename from message WHERE messagename ='" + messageName.replace("'", "''") + "'";
        String exists = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
            	exists = rs.getString("messagename");
            }
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while checking if a messagename is already in use");
        	return false;
        }
        if (exists != null && exists.equals(messageName)) {
        	return false;
        } else {
        	return true;
        }

    }
    
 // Methods for the answer table

    /*
     * setAnswer method saves a new message into the database.
     */
    public boolean setAnswer(Answer answer) throws SQLException {
        String setMessageString;
                // Handling a message without a channel and without a location
                setMessageString = "insert into answer  (messagename, username, answer, contact) VALUES('"
                        + answer.getMessageName().replace("'", "''") + "','" + answer.getUser().replace("'", "''") + "','"
                        + answer.getAnswerText().replace("'", "''") + "','" + answer.getContact().replace("'", "''") + "')";
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(setMessageString);
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while adding answer to database");
        	return false;
        }
        return true;
    }

    /*
     * getAnswers method retrieves answers to message target from the database.
     */
    public List<Answer> getAnswers(String target) throws SQLException {
        ArrayList<Answer> answers = new ArrayList<>();
        // Getting all the messages
        String getMessagesString;
        // Handling the case where there is no channel and no time
        getMessagesString = "select messagename, username, answer, contact from answer WHERE messagename ='" + target.replace("'", "''") + "'";
        String messagename = null;
        String username = null;
        String answertext = null;
        String contact = null;
        try (Statement queryStatement = dbConnection.createStatement()) {
            ResultSet rs = queryStatement.executeQuery(getMessagesString);

            while (rs.next()) {
            	messagename = rs.getString("messagename");
            	username =  rs.getString("username");
            	answertext = rs.getString("answer");
            	contact = rs.getString("contact");
                Answer answer = new Answer(messagename, username, answertext, contact);
                answers.add(answer);
            }

        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while reading answers from database");
        }
        return answers;

    }
    
    /*
     * editAnswers method edits a previously saved answer in the database
     */
    public boolean editAnswers(String target, String newName) throws SQLException {
        String editMessageString;
        // Editing a message 
        editMessageString = "UPDATE answer SET messagename = '" + newName.replace("'", "''") + "' WHERE messagename = '" + target.replace("'", "''") + "'";
        
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(editMessageString);
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException while editing a answer in the database");
            return false;
        }
        return true;
    }
    
    /*
     * deleteAnswer method deletes answers to a deleted message
     */
    public boolean deleteAnswers(String target) throws SQLException {
        String editMessageString;
        // Deleting a message
        editMessageString = "DELETE FROM answer  WHERE messagename = '" + target.replace("'", "''") + "'";
        
        try (Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(editMessageString);
        } catch (SQLException e) {
        	System.out.println("ERROR: SQLException while deleting answers from the database");
            return false;
        }
        return true;
    }
}
