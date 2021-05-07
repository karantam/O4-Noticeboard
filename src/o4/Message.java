package o4;

/*
 * Message class creates a message object containing all the information about that message
 */
public class Message {
    private String user;
    private String name;
    private String location;
    private double price;
    private String message;

    // Constructor for the ChatMessage object
    public Message( String userpar,String namepar, String locationpar, double pricepar, String messagepar) {
        this.user = userpar;
        this.name = namepar;
        this.message = messagepar;
        this.location = locationpar;
        this.price = pricepar;
        this.message = messagepar;
    }

    // Get methods for variables contained in ChatMessage object
    public String getUser() {
        return this.user;
    }

    public String getName() {
        return this.name;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public String getMessage() {
        return this.message;
    }


    public Message getChatMessage() {
        return new Message(user, name, location, price, message);
    }
}
