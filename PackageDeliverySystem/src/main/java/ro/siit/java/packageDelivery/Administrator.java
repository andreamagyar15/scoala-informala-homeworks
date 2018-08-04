package ro.siit.java.packageDelivery;

public class Administrator {
    private String userName;
    private String password;
    public Administrator(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn(String userName, String password){
        return null;
    }
}
