package onlinebookstore.service;

import onlinebookstore.dao.CategoryDao;
import onlinebookstore.models.CategoryDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CategoryService {

    CategoryDao categoryDao = new CategoryDao();

    public CategoryDTO getCategory(String categoryId) throws Exception {
        //if category is null, return null
        if (categoryId == null) {
            return null;
        }

        return categoryDao.findByPrimaryKey(categoryId);
    }


    public ArrayList getAllCategories() throws Exception {

        //declare category collection variable
        ArrayList categories = null;

        //get all category local objects ordered by category sort order and name
        Collection allCategories = categoryDao.findAll();

        //setup iterator and output collection
        Iterator iterator = allCategories.iterator();
        categories = new ArrayList();

        //transform each category local object into category DTO and store in a new collection
        while (iterator.hasNext()) {
            //convert local object to DTO object and add to output collection
            categories.add(iterator.next());
        }

        return categories;

    }
}
