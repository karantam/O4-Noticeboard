package o4;

public class Answer {
    private String messagename;
    private String user;
    private String answertext;
    private String contact;

    // Constructor for the Answer object
    public Answer( String messagenamepar,String userpar, String answertextpar, String contactpar) {
        this.messagename = messagenamepar;
        this.user = userpar;
        this.answertext = answertextpar;
        this.contact = contactpar;
    }

    // Get methods for variables contained in Answer object
    public String getMessageName() {
        return this.messagename;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String getAnswerText() {
        return this.answertext;
    }
    
    public String getContact() {
        return this.contact;
    }


    public Answer getAnswer() {
        return new Answer(messagename, user, answertext, contact);
    }
    
    // Palauttaa vastauksen jättäjän nimen kun olio luetaan
    public String toString() {
    	return user;
    	}
}

