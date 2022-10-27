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
@Table(name = "book")
public class BookDTO implements java.io.Serializable {

    //declare fields
    // for Book entity
    @Id
    @Column(name = "id")
    private String id;    //id
    private String title;   //title
    private String description; //description
    private String author;  //author name
    private String publisher;   //publisher name
    private Date datePublished; //publishing date
    private Double price;   //unit price
    private String photo;   //photo url
    private Long ratingValue;    //total rating value
    private Long ratingCount;   //total rating count
    private String categoryId;    //category id (foreign key)

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof BookDTO) {
            BookDTO other = (BookDTO) obj;
            if (other.getId().equals(id) && other.getTitle().equals(title)
                    && other.getDescription().equals(description)
                    && other.getAuthor().equals(author)
                    && other.getPublisher().equals(publisher)
                    && other.getDatePublished().equals(datePublished)
                    && other.getPrice().equals(price)
                    && other.getPhoto().equals(photo)
                    && other.getRatingValue().equals(ratingValue)
                    && other.getRatingCount().equals(ratingCount)
                    && other.getCategoryId().equals(categoryId)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ title.hashCode() ^ description.hashCode()
                ^ author.hashCode() ^ publisher.hashCode()
                ^ datePublished.hashCode() ^ price.hashCode()
                ^ photo.hashCode() ^ ratingValue.hashCode()
                ^ ratingCount.hashCode() ^ categoryId.hashCode();
    }
}
