package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        //Evitar ingresar un null
        if(book == null){
            return false;
        }

        //Evitar ingresar libros con valores nulos
        if(book.getAuthor() == null || book.getTittle() == null || book.getIsbn() == null){
            return false;
        }

        //Para evitar ingresar un libro con un ISBN igual a uno ya existente
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            Book tempBook = entry.getKey();
            if(book.equals(tempBook) && 
            !book.getTittle().equals(tempBook.getTittle()) && 
            !book.getAuthor().equals(tempBook.getAuthor())){
                return false;
            }
        }

        //Simplemente, tomo el valor y aumento 1
        int amount = books.getOrDefault(book, 0); //Esto es para que si el libro no esta, En vez de dar null, da 0, esto para simplemente sumar 1 y ya.
        amount++;
        books.put(book, amount);
        return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        Loan loan = new Loan();
        loan.setBook(null);
        loan.setUser(null);
        loan.setLoanDate(null);
        loan.setStatus(null);
        loan.setReturnDate(null);
        
        if(userId == null || isbn == null){
            return loan;
        }

        
    
        //Verificamos que el libro exista y haya libros para poder prestar
        Book book = null;
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            Book tempBook = entry.getKey();
            if(tempBook.getIsbn().equals(isbn)){
                book = tempBook;
                break;
            }
        }
        int amount = books.getOrDefault(book,0);
        if(book == null || amount <=0){
            return loan;
        }

        //Verificamos que el usuario si exista
        User user = null;
        for (User userTemp : users) {
            if(userTemp.getId().equals(userId)){
                user = userTemp;
                break;
            }
        }
        if(user == null){
            return loan;
        }


        //Disminuir un libro
        amount--;
        books.put(book, amount);
        LocalDateTime loanDate = LocalDateTime.now();


        //Creacion del prestamo
        loan.setBook(book);
        loan.setUser(user);
        
        loan.setLoanDate(loanDate);
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setReturnDate(loanDate.plusWeeks(1));

        
        return loan;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    //Añadi esta clase para lograr obtener el mapa de libros y asi mismo, en las pruebas, poder saber si todo iba bien o no (Para los test)
    public Map<Book, Integer> getBookMap(){
        return books;
    }

    //Añadi esta clase para obtener los prestamos (Para los test)
    public List<Loan> getLoans(){
        return loans;
    }

}