package Model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    /**Default constructor*/
    public Client() {
    }

    /**
     * Constructor with parameters
     * @param id the id of the client
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     */
    public Client(int id, String name, String address, String email) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    //region getters and setters

    /**
     * gets the id of a client
     */
    public int getId() {
        return id;
    }

    /**
     * gets the name of a client
     */
    public String getName() {
        return name;
    }

    /**
     * gets the address of a client
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets the email of a client
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the id of a client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets the name of a client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the address of a client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * sets the email of a client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * constructs a string representation of a client
     */
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
    }

    //endregion

}