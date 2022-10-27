package onlinebookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements java.io.Serializable {

    //declare fields for Order entity
    @Id
    @Column(name = "id")
    private String id;    //id
    @Column(name = "order_date")
    private Date date;  //order date
    private Double amount;  //order total amount
    private String userId;    //user id (foreign key)
    private String address; //shipping address

    public OrderDTO(String address, Date date, Double amount) {
        this.address = address;
        this.date = date;
        this.amount = amount;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof OrderDTO) {
            OrderDTO other = (OrderDTO) obj;
            if (other.getId().equals(id) && other.getDate().equals(date)
                    && other.getAddress().equals(amount)
                    && other.getUserId().equals(userId)
                    && other.getAddress().equals(address)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ date.hashCode() ^ amount.hashCode()
                ^ userId.hashCode() ^ address.hashCode();
    }
}
