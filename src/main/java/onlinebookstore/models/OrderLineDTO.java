package onlinebookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "order_line")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO implements java.io.Serializable {

    //declare fields for Order_Line entity
    @Id
    @Column(name = "id")
    private String id;    //id
    private String orderId;   //order id (foreign key)
    private String bookId;    //book id (foreign key)
    private Double unitPrice;   //unit price
    private Long quantity;  //quantity

    public OrderLineDTO(String bookId, Double unitPrice, Long quantity) {
        this.bookId = bookId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof OrderLineDTO) {
            OrderLineDTO other = (OrderLineDTO) obj;
            if (other.getId().equals(id) && other.getOrderId().equals(orderId)
                    && other.getBookId().equals(bookId)
                    && other.getUnitPrice().equals(unitPrice)
                    && other.getQuantity().equals(quantity)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ orderId.hashCode() ^ bookId.hashCode()
                ^ unitPrice.hashCode() ^ quantity.hashCode();
    }
}
