package onlinebookstore.dao;

import com.fasterxml.uuid.Generators;
import onlinebookstore.models.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class UserDao extends BaseHibernateDao {

    public UserDTO findByLoginDetails(String login, String password) {
        List<UserDTO> userDTOList = executeQuery(" from UserDTO where login = '" + login + "' and password = '" + password + "'");
        return CollectionUtils.isNotEmpty(userDTOList) ? userDTOList.get(0) : null;
    }

    public UserDTO findByLogin(String login) {
        List<UserDTO> userDTOList = executeQuery(" from UserDTO where login = '" + login + "'");
        return CollectionUtils.isNotEmpty(userDTOList) ? userDTOList.get(0) : null;
    }

    public UserDTO findByEmail(String email) {
        List<UserDTO> userDTOList = executeQuery(" from UserDTO where email = '" + email + "'");
        return CollectionUtils.isNotEmpty(userDTOList) ? userDTOList.get(0) : null;
    }

    public UserDTO create(UserDTO user) {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        user.setId(timebaseUUID.toString());
        insert(user);
        return user;
    }

    public int updateUser(UserDTO userDTO, String id)
    {
        Session session = getSession();
        Transaction tx = null;
        int result=0;
        // boolean flag=false;
        try {
            tx = session.beginTransaction();
            String updt="update User u set u.login=:uslogin,u.email=:usemail,u.phone=:usphone, u.name=:usfullname, " +
                    "u.birthday=:usbday, u.password =:uspassword" +
                    " where u.id=:usid";
            Query hql = session.createQuery(updt);
            hql.setParameter("usid", id);
            hql.setParameter("usname", userDTO.getName());
            hql.setParameter("usemail", userDTO.getEmail());
            hql.setParameter("usphone",userDTO.getPhone());
            hql.setParameter("usfullname",userDTO.getName());
            hql.setParameter("usbday",userDTO.getBirthday());
            hql.setParameter("uspassword",userDTO.getPassword());
            result=hql.executeUpdate();
            tx.commit();
        }
        catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;

    }
}
