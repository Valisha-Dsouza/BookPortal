package onlinebookstore.dao;


import com.fasterxml.uuid.Generators;
import onlinebookstore.models.CommentDTO;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.List;
import java.util.UUID;

public class CommentDao extends BaseHibernateDao {

    public CommentDTO findByPrimaryKey(String id) throws FinderException {
        return findById(id, CommentDTO.class);

    }

    public CommentDTO findByUserIdAndBookId(Long userId, Long bookId) throws FinderException {
        List<CommentDTO> commentDTOS = executeQuery(" from CommentDTO where userId = '" + userId + "' and bookId = '" + bookId + "'");
        return CollectionUtils.isNotEmpty(commentDTOS) ? commentDTOS.get(0) : null;
    }

    public java.util.Collection findAllByBookId(String bookId) throws FinderException {
        List<CommentDTO> commentDTOS = executeQuery(" from CommentDTO where bookId = '" + bookId + "'");
        System.out.println("BookId" + bookId + " "+ commentDTOS);
        return commentDTOS;
    }

    public CommentDTO create(CommentDTO comment) throws CreateException {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        comment.setId(timebaseUUID.toString());
        insert(comment);
        return comment;
    }
}
