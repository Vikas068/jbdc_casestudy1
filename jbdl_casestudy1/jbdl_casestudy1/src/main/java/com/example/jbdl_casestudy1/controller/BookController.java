package com.example.jbdl_casestudy1.controller;

import com.example.jbdl_casestudy1.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private HashMap<Integer, Book> bookMap = new HashMap<Integer, Book>();
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    //Getting all the books.
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks()
    {
        logger.info("Getting all book details");
        return bookMap.values().stream().collect(Collectors.toList());
    }

    //Creating/adding books.
    @PostMapping("/addBook")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book)
    {
        logger.info("Insertion of data"+book);
        if(bookMap.containsKey(book.getIsbn()))
        {
            logger.info("This book is already present in database");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        bookMap.put(book.getIsbn(),book);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //update book by ISBN.
    @PutMapping("/updateBook/{ISBN}")
    public ResponseEntity<Book> updateBook(@PathVariable("ISBN") int ISBN,@RequestBody Book book)
    {
        if(bookMap.containsKey(ISBN)) {
            logger.info("Update is done for the ISBN ="+ISBN);
            Book updateBook = new Book();
            updateBook.setIsbn(book.getIsbn());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setCost(book.getCost());
            updateBook.setName(book.getName());
            bookMap.put(updateBook.getIsbn(), updateBook);
            return ResponseEntity.status(HttpStatus.OK).body(updateBook);

        }
        else {
            logger.info("This ISBN Book details is not available.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //get Book by ISBN.
    @GetMapping("/getBookByIsbn/{ISBN}")
    public Book getBookByISBN(@PathVariable ("ISBN") int ISBN)
    {
        logger.info("getting data by isbn="+ISBN);
        return bookMap.get(ISBN);
    }

    //delete Book by ISBN.
    @DeleteMapping("/deleteBook/{ISBN}")
    public  String deleteByIsbn(@PathVariable ("ISBN") int ISBN)
    {
        logger.info("deleted book of ISBN = "+ISBN);
        bookMap.remove(ISBN);
        return "Deleted successfully";
    }

}
