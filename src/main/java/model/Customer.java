package model;

public class Customer {
    private int idCustomer;
    private String nameCustomer;
    private String phoneCustomer;
    private String emailCustomer;
    private String addressCustomer;

    /**
     * This is the constructor of the Customer
     *
     * @param idCustomer      - the id of the customer
     * @param nameCustomer    - the name of the customer
     * @param phoneCustomer   - the phone of the customer
     * @param emailCustomer   - the email of the customer
     * @param addressCustomer - the address of the customer
     */
    public Customer(int idCustomer, String nameCustomer, String phoneCustomer, String emailCustomer, String addressCustomer) {
        super();
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
    }

    /**
     * This is the second constructor of the Customer
     *
     * @param nameCustomer    - the name of the customer
     * @param phoneCustomer   - the phone of the cutomer
     * @param emailCustomer   - the email of the customer
     * @param addressCustomer - the address of the customer
     */
    public Customer(String nameCustomer, String phoneCustomer, String emailCustomer, String addressCustomer) {
        super();
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", phoneCustomer='" + phoneCustomer + '\'' +
                ", emailCustomer='" + emailCustomer + '\'' +
                ", addressCustomer='" + addressCustomer + '\'' +
                '}';
    }
}

