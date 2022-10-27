package onlinebookstore.dao;

import com.fasterxml.uuid.Generators;
import onlinebookstore.models.BookDTO;

import java.util.List;
import java.util.UUID;


public class BookDao extends BaseHibernateDao {

    public BookDTO findByPrimaryKey(String id) {
        return findById(id, BookDTO.class);
    }

    public java.util.Collection findBooksByTitleAndAuthorAndCategoryId(String title, String author, String categoryId) {
        List<BookDTO> bookDaos = executeQuery(" from BookDTO where categoryId = '" + categoryId + "' and author = '" + author + "' and title = '" + "'");
        return bookDaos;
    }

    public java.util.Collection findBooksByTitleAndCategoryId(String title, String categoryId) {
        List<BookDTO> bookDaos = executeQuery(" from BookDTO where categoryId = '" + categoryId + "' and title = '" + title + "'");
        return bookDaos;
    }

    public java.util.Collection findBooksByAuthorAndCategoryId(String author, String categoryId) {
        List<BookDTO> bookDaos = executeQuery(" from BookDTO where categoryId = '" + categoryId + "' and author = '" + author + "'");
        return bookDaos;
    }

    public java.util.Collection findBooksByCategoryId(String categoryId) {
        List<BookDTO> bookDaos = executeQuery(" from BookDTO where categoryId = '" + categoryId + "'");
        return bookDaos;
    }

    public BookDTO create(BookDTO book) {
        UUID timebaseUUID = Generators.timeBasedGenerator().generate();
        book.setId(timebaseUUID.toString());
        insert(book);
        return book;
    }
}
