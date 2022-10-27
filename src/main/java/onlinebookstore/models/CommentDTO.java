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
@Table(name = "comment")
public class CommentDTO implements java.io.Serializable {

    //declare fields for Comment entity
    @Id
    @Column(name = "id")
    private String id;    //id
    private Integer rating;    //rating
    private String content; //content
    private String userId;    //user id (foreign key)
    private String bookId;    //book id (foreign key)
    private Date date;  //comment date

    public CommentDTO() {

    }

    public CommentDTO(String userId, String bookId, Integer rating, String content) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.content = content;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof CommentDTO) {
            CommentDTO other = (CommentDTO) obj;
            if (other.getId().equals(id) && other.getRating().equals(rating)
                    && other.getContent().equals(content)
                    && other.getUserId().equals(userId)
                    && other.getBookId().equals(bookId)
                    && other.getDate().equals(date)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ rating.hashCode() ^ content.hashCode()
                ^ userId.hashCode() ^ bookId.hashCode()
                ^ date.hashCode();
    }
}
