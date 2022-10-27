package onlinebookstore.dao;

import com.fasterxml.uuid.Generators;
import onlinebookstore.models.CategoryDTO;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.UUID;

public class CategoryDao extends BaseHibernateDao {

    public CategoryDTO findByPrimaryKey(String id) throws FinderException {
        return findById(id, CategoryDTO.class);
    }

    public java.util.Collection findAll() throws FinderException {
        return getList(CategoryDTO.class);
    }

    public CategoryDTO create(CategoryDTO category) throws CreateException {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        category.setId(timebaseUUID.toString());
        insert(category);
        return category;
    }
}
