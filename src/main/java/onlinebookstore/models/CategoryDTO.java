package onlinebookstore.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category")
@ToString
public class CategoryDTO implements java.io.Serializable {

    //declare fields f
    // or Category entity
    @Id
    @Column(name = "id")
    private String id;    //category id
    private String name;    //category name
    @Column(name = "sort_order")
    private Integer sortOrder;  //sort order

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof CategoryDTO) {
            CategoryDTO other = (CategoryDTO) obj;
            if (other.getId().equals(id) && other.getName().equals(name)
                    && other.getSortOrder().equals(sortOrder)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ name.hashCode() ^ sortOrder.hashCode();
    }
}
