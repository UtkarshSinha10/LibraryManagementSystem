package com.examplejdbc.demojdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    private static Connection connection;
    public static void getConnection() throws SQLException {
        if(connection==null){
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "1234@MySQL");
        }
    }

    public static void createTable(String table_name) throws SQLException {
        getConnection();
        Statement statement=connection.createStatement();
        boolean isOpSuc=statement.execute("CREATE TABLE "+ table_name +"(id INT PRIMARY KEY auto_increment ,book_name VARCHAR(30),author_name VARCHAR(30),cost INT)");
        if(isOpSuc) System.out.println("Table Created");
        else System.out.println("Table Not Created");
    }

    public static List<Book> getBooks() throws SQLException {
        getConnection();

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM  my_books");

        List<Book> booksToReturn=new ArrayList<>();
        while (resultSet.next()){
            int book_id=resultSet.getInt(1);
            String book_name=resultSet.getString(2);
            String author_name=resultSet.getString(3);
            int cost=resultSet.getInt(4);
            Book b=new Book(book_id,book_name,author_name,cost);
            booksToReturn.add(b);

        }
        return  booksToReturn;

    }

    public static Book getBook(String id) throws SQLException {
        getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("Select * From my_books where id="+id);

        while(resultSet.next()){
            int book_id=resultSet.getInt(1);
            String book_name=resultSet.getString(2);
            String author_name=resultSet.getString(3);
            int cost=resultSet.getInt(4);
            return new Book(book_id,book_name,author_name,cost);
        }
        System.out.println("Book not present with id: "+id);
        return null;
    }

    public static void insert(Book book) throws SQLException {
        getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("Insert into my_books Values (null,?,?,?)");
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthorName());
        preparedStatement.setInt(3, book.getCost());

        int row=preparedStatement.executeUpdate();
        if(row<1) System.out.println("Insert Book not successful");
        else System.out.println("Insert Book Successful");
    }

}
