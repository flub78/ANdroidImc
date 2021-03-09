package flub78.org.imc.model;

/**
 * Created by flub78 on 2021-03-09.
 */
public class User {

    // No constructor to initialize firstName, as it is only known when the user
    // enters his/her name

    // public interface
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    // private part
    private String mFirstName;

}
