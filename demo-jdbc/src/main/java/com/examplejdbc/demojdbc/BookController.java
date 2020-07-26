package com.examplejdbc.demojdbc;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class BookController {
    @GetMapping("/books")
    public List<Book> getBook() throws SQLException {
        return DBOperations.getBooks();
    }

    @PostMapping("/book")
    public void insertBook(@RequestBody Book book) throws SQLException {
        DBOperations.insert(book);
    }

    @GetMapping("/book")
    public Book get_Book(@RequestParam(value="id") String id) throws SQLException {
        return DBOperations.getBook(id);
    }

    @PostMapping("/table/{table_name}")
    public void createTable(@PathVariable String table_name) throws SQLException {
        DBOperations.createTable(table_name);
    }




}
