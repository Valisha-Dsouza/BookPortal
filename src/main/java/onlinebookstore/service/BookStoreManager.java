package onlinebookstore.service;

import onlinebookstore.dao.*;
import onlinebookstore.models.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.registry.infomodel.User;
import java.awt.print.Book;
import java.util.*;

public class BookStoreManager {
    private BookDao bookManager = new BookDao();
    private CategoryDao categoryManager = new CategoryDao();
    private CommentDao commentManager = new CommentDao();
    private OrderDao orderManager = new OrderDao();
    private OrderLineDao orderLineManager = new OrderLineDao();
    private UserDao userManager = new UserDao();
    //declare new shopping cart
    private ArrayList cart = new ArrayList();
    //declare new order
    private OrderDTO order = new OrderDTO();
    //declare current user
    private UserDTO user = null;

    static BookStoreManager bookStoreManager = null;

    public synchronized static BookStoreManager getInstance() {
        if (null == bookStoreManager) {
            bookStoreManager = new BookStoreManager();
        }

        return bookStoreManager;
    }

    public void logout() {
        this.user = null;
        System.out.println("Logout" + this.user);
    }

    //register new user

    public void register(UserDTO user) throws Exception {
        System.out.println("Register" + this.user);
        //if user already logged in, throw new error
        if (this.user != null) {
            throw new Exception("Cannot register! Only anonymous users can create new account!");
        }

        //if username is empty, throw an error
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            throw new Exception("Username & Email cannot be blank!");
        }

        try {
            UserDTO userDTO = userManager.findByLogin(user.getLogin());
            if (null == userDTO) {
                userDTO = userManager.findByEmail(user.getEmail());
            }
            if (null == userDTO) {
                userDTO = userManager.create(user);
            }

            this.user = userDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Registering failed due to exception:{}", e);
        }


    }

    //login user to the system
    public void login(String username, String password) throws Exception {
        try {
            user = userManager.findByLoginDetails(username, password);
            System.out.println(user);
        } catch (Exception e) {
            throw new Exception("User not existed!");
        }
    }

    //update user personal information
    public void updateProfile(UserDTO user) throws Exception {

        //if user not logged in, throw an error
        if (this.user == null) {
            throw new Exception("Only logged in users can do this!");
        } else if (!user.getLogin().equals(this.user.getLogin())) {
            throw new Exception("Incorrect username!");
        } else if (user.getPassword().isEmpty()) {
            throw new Exception("Password cannot be blank!");
        } else if (user.getEmail().isEmpty()) {
            throw new Exception("Email cannot be blank!");
        } else {
            try {
                this.user = userManager.findByEmail(user.getEmail());
                userManager.updateUser(user, this.user.getId());
            } catch (Exception e) {
                e.printStackTrace();
                this.user = user;
            }
        }
    }

    //return current user object
    public UserDTO getCurrentUser() {
        return user;
    }

    //get category with specified id
    public CategoryDTO getCategory(String categoryId) throws Exception {
        System.out.println("Finding by categoryId" + categoryId);
        //if category is null, return null
        if (categoryId == null) {
            return null;
        }

        //return DTO object
        return categoryManager.findByPrimaryKey(categoryId);
    }

    //browse for books by categories
    public ArrayList browseBooks(String categoryId, Integer pageSize) throws Exception {
        System.out.println("Browsing books" +categoryId);
        ArrayList<String> categoryIDS = new ArrayList<String>();
        ArrayList books = new ArrayList(); //output book collection
        ArrayList pageBooks = new ArrayList();    //collection to store books per page
        Iterator iterator = null;   //iterator to loop through collection(s)
        int counter = 0;    //counter to count number of items processed for each colllection


        if (StringUtils.isNotEmpty(categoryId)) {
            categoryIDS.add(categoryId);
        } else {
            Collection categories = categoryManager.findAll();
            iterator = categories.iterator();

            while (iterator.hasNext()) {
                CategoryDTO category = (CategoryDTO) iterator.next();
                categoryIDS.add(category.getId());
            }
        }

        System.out.println("IN BROWSEBOOKS categories here :{}" + categoryIDS);

        for (String category: categoryIDS) {
            Collection<BookDTO> bookDTOS = bookManager.findBooksByCategoryId(category);
            Iterator<BookDTO> bookDTOIterator = bookDTOS.iterator();
            while (bookDTOIterator.hasNext()) {
                BookDTO bookDTO = bookDTOIterator.next();
                pageBooks.add(bookDTO);
                counter++;

                if (!bookDTOIterator.hasNext() || counter == pageSize.intValue()) {
                    books.add(pageBooks);
                    pageBooks = new ArrayList();
                    counter = 0;
                }
            }
        }

        System.out.println("Books" + books);
        return books;
    }

    //search for book with title, author name and category id (optional)
    public ArrayList searchBooks(String title, String author, final String categoryId, Integer pageSize) throws Exception {
        if (pageSize.intValue() < 1) {
            return null;
        }

        List<String> categories = new ArrayList<String>();
        ArrayList<BookDTO> foundBooks = new ArrayList();    //found books collection
        ArrayList<ArrayList<BookDTO>> books = new ArrayList(); //output book collection
        ArrayList<BookDTO> pageBooks = new ArrayList();    //temp collection to store books in a page
        int counter = 0;    //counter to count number of items in a page (for pagination)


        if (categoryId == null) {
            Collection<CategoryDTO> categoryDaos = categoryManager.findAll();
            if (CollectionUtils.isNotEmpty(categoryDaos)) {
                Iterator<CategoryDTO> categoryDTOIterator = categoryDaos.iterator();
                while (categoryDTOIterator.hasNext()) {
                    categories.add(categoryDTOIterator.next().getId());
                }
            }
        } else {
            categories.add(categoryId);
        }

        for (String category: categories) {
            if (StringUtils.isEmpty(title) && StringUtils.isEmpty(author)) {
                foundBooks.addAll(bookManager.findBooksByCategoryId(category));
            } else if (StringUtils.isEmpty(title)) {
                foundBooks.addAll(bookManager.findBooksByAuthorAndCategoryId(author, category));
            } else if (StringUtils.isEmpty(author)) {
                foundBooks.addAll(bookManager.findBooksByTitleAndCategoryId(title, category));
            } else {
                foundBooks.addAll(bookManager.findBooksByTitleAndAuthorAndCategoryId(title, author, category));
            }
        }

        for (BookDTO bookDTO: foundBooks) {
            pageBooks.add(bookDTO);
            counter++;

            if (counter == pageSize.intValue() || counter == foundBooks.size()) {
                books.add(pageBooks);
                pageBooks = new ArrayList();
                counter = 0;
            }
        }

        return books;
    }

    //get book with specified id
    public BookDTO getBook(String id) throws Exception {
        return bookManager.findByPrimaryKey(id);
    }

    //get all user names by book comments
    public ArrayList getUserNamesByBookComments(String bookID) throws Exception {
        Collection bookComments = commentManager.findAllByBookId(bookID);
        Iterator iterator = bookComments.iterator();   //iterator to loop through comment collection

        //declare output variable
        ArrayList userNames = new ArrayList();

        //get user name from each user object and store in a new collection
        while (iterator.hasNext()) {
            CommentDTO commentDTO = (CommentDTO) iterator.next();
            String userId = commentDTO.getUserId();
            UserDTO userDTO = userManager.findById(userId, UserDTO.class);
            userNames.add(userDTO.getName());
        }

        return userNames;
    }

    //get all comments of a book
    public ArrayList getBookComments(String bookID) throws Exception {
        Collection bookComments = commentManager.findAllByBookId(bookID);
        System.out.println("BookComments"+ bookComments);
        Iterator iterator = bookComments.iterator();
        ArrayList comments = new ArrayList();

        while (iterator.hasNext()) {
            comments.add(iterator.next());
        }

        return comments;

    }

    //add rating and comment too a book
    public void addComment(CommentDTO comment) throws Exception {
        commentManager.create(comment);
    }

    //add a book to shopping cart
    public void addToCart(String bookId) throws Exception {
        BookDTO bookDTO = bookManager.findByPrimaryKey(bookId);

        Iterator iterator = cart.iterator();

        //search for order line in cart
        while (iterator.hasNext()) {
            //get currently pointed order line local item
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

            //if matching found, throw an error
            if (orderLineDTO.getBookId().equals(bookId)) {
               return;
            }
        }

        OrderLineDTO orderLineDTO = new OrderLineDTO(bookId, bookDTO.getPrice(), new Long(1));
        cart.add(orderLineDTO);
    }

    //update cart with current session's cart
    public void updateCart(ArrayList cart) throws Exception {
        this.cart = cart;
    }

    //remove a book from cart
    public void removeFromCart(String bookId) throws Exception {

        Iterator iterator = cart.iterator();
        while (iterator.hasNext()) {
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();
            if (orderLineDTO.getBookId().equals(bookId)) {
                iterator.remove();
                return;
            }
        }
    }

    //modify cart item
    public void modifyCart(String bookId, Long quantity) throws Exception {
        Iterator iterator = cart.iterator();

        while (iterator.hasNext()) {
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

            if (orderLineDTO.getBookId().equals(bookId)) {
                orderLineDTO.setQuantity(quantity);
                return;
            }
        }
    }

    //view shopping cart
    public ArrayList getCart() {
        return cart;

    }

    //get total cart value
    public Double getCartValue() throws Exception {
        double total = 0;

        Iterator iterator = cart.iterator();
        while (iterator.hasNext()) {
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();
            BookDTO book = bookManager.findByPrimaryKey(orderLineDTO.getBookId());
            total += book.getPrice().doubleValue() * orderLineDTO.getQuantity().longValue();
        }

        return new Double(total);
    }


    //checkout and add shipping address
    public void checkOut(String address) throws Exception {
        //if user is not logged in, throw an error
        if (user == null) {
            throw new Exception("Only logged in users can checkout!");
        } else if (cart.isEmpty()) {
            //if cart is empty, throw an error
            throw new Exception("Shopping cart empty!");
        } else {
            order = new OrderDTO(address, new Date(), getCartValue());
        }
    }

    //confirm checkout
    public void confirmOrder(Boolean confirmation) throws Exception {
        if (!confirmation.booleanValue()) {
            return;
        }

        System.out.println("Order is" + order + "User is" + user);
        order.setUserId(user.getId());

        //add order to database and get returned order local object
        OrderDTO orderDTO = orderManager.create(order);

        //setup iterator for cart collection
        Iterator iterator = cart.iterator();

        int counter = 1;

        //set order id and add each order line to database
        while (iterator.hasNext()) {
            OrderLineDTO tempOrderLine = (OrderLineDTO) iterator.next();
            tempOrderLine.setOrderId(orderDTO.getId());
            OrderLineDTO orderLineDTO = orderLineManager.create(tempOrderLine);
            BookDTO bookDTO = bookManager.findByPrimaryKey(tempOrderLine.getBookId());

            //set order for order line local object
            //      orderLineLocal.setOrder(orderLocal);

            //set book for order line local object
            //    orderLineLocal.setBook(bookDTO);
        }

        //reset order and cart
        order = new OrderDTO();
        cart.clear();
    }
}
