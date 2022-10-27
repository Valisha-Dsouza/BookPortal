package onlinebookstore.dao;

import com.fasterxml.uuid.Generators;
import onlinebookstore.models.OrderLineDTO;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.UUID;

public class OrderLineDao extends BaseHibernateDao {

    public OrderLineDTO findByPrimaryKey(String id) throws FinderException {
        return findById(id, OrderLineDTO.class);
    }

    public OrderLineDTO create(OrderLineDTO orderLine) throws CreateException {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        orderLine.setId(timebaseUUID.toString());
        insert(orderLine);
        return orderLine;
    }
}
