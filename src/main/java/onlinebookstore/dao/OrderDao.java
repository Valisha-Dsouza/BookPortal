package onlinebookstore.dao;

import com.fasterxml.uuid.Generators;
import onlinebookstore.models.OrderDTO;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.UUID;

public class OrderDao extends BaseHibernateDao {

    public OrderDTO findByPrimaryKey(String id) throws FinderException {
        return findById(id, OrderDTO.class);
    }

    public OrderDTO create(OrderDTO order) throws CreateException {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        order.setId(timebaseUUID.toString());
        insert(order);
        return order;
    }
}
