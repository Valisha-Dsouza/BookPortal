package onlinebookstore.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "users")
public class UserDTO implements java.io.Serializable {

    //declare fields for User entity
    @Id
    @Column(name = "id")
    private String id;    //user id
    private String login;   //login name
    private String email;   //email address
    private String password;    //password
    private String phone;  //phone number
    @Column(name = "fullname")
    private String name;    //full name
    private Date birthday;  //user's birthday
    @Column(name = "date_created")
    private Date dateCreated;   //account creation date

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof UserDTO) {
            UserDTO other = (UserDTO) obj;
            if (other.getId().equals(id) && other.getLogin().equals(login)
                    && other.getEmail().equals(email)
                    && other.getPassword().equals(password)
                    && other.getPhone().equals(phone)
                    && other.getName().equals(name)
                    && other.getBirthday().equals(birthday)
                    && other.getDateCreated().equals(dateCreated)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ login.hashCode() ^ email.hashCode()
                ^ password.hashCode() ^ phone.hashCode() ^ name.hashCode()
                ^ birthday.hashCode() ^ dateCreated.hashCode();
    }
}
