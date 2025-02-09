//Aca estoy importando todas las clases a las que le debemos hacer las pruebas o usamos para las pruebas
package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

//Importaciones de las cosas para los test
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
   private Library library;
   private Book book1;
   private Book book2;
   private User user1;

   @BeforeEach
   public void setup() {
      // ===================== DATOS general
      library = new Library();
      book1 = new Book("Libro", "Autor", "codigo");
      book2 = new Book("Libro 2", "Autor 2", "codigo 2");
      // =====================

      // ===================== DATOS PARA LOANBOOK
      user1 = new User();
      user1.setName("User 1");
      user1.setId("01");
      library.addUser(user1);
      // =====================
   }

   @AfterEach
   public void setdown() {
      library = null;
   }

   // ================================================== INICIO PRUEBAS ADDBOOK
   /**
    * EXPLICACION DE MIS IDEAS
    * 1. Mire como debe "funcionar" basicamente el metodo
    * - los Book se guardan en un mapa. Donde esta libro:cantidad de ese libro
    * - Cada libro tiene nombre, autor y codigo de barras.
    * - Si el libro no existe al añadirse al mapa, aparece como 1
    * - Si el libro ya existe al añadirse al mapa, incrementa en 1
    * - Si la operacion es satisfactoria (TIpo se hizo lo que queriamos hacer) da
    * true,si no, da false
    * 
    * 2. Cosas basicas que deberian funcionar
    * 2.1 Añadir un libro nuevo. El conteo de este libro en el mapa debe ser 1*
    * 2.2 Añadir un libro existente. El conteo de este libro en el mapa debe ser 2*
    * 2.3 Añadir 5 veces un libro. El conteo de este libro en el mapa debe ser 5*
    * 
    * 3. Cosas basicas que no deberian funcionar
    * 3.1 Añadir null*
    * 3.2 Añadir un book lleno de nulls (Todos sus campos son nulos)*
    * 
    * 4. Cosas mas avanzadas que deberian funcionar
    * 4.1 Añadir un libro con un nombre igual a otro libro, pero de diferente autor
    * y codigo*
    * 4.2 Añadir un libro con un nombre diferente a otro libro, pero de igual autor
    * y codigo diferente*
    * 4.3 Añadir un lirbo con mismo nombre y autor pero diferente ISBN*
    * 
    * 5. Cosas mas avanzadas que no deberian funcionar
    * 5.1 Añadir un libro con un ISBN igual a otro pero todo lo demas diferente*
    * 
    * Ya con eso dije, deben ser pruebas suficientes para que esto funcione
    */

   // 2.1
   @Test
   public void testAddBookNewBook() {
      boolean result = library.addBook(book1);
      assertTrue(result);

      Map<Book, Integer> books = library.getBookMap();
      int amountOfBook = books.getOrDefault(book1, 0);
      assertEquals(amountOfBook, 1);

   }

   // 2.2
   @Test
   public void testAddBookExistingBook() {
      library.addBook(book1);

      boolean result = library.addBook(book1);
      assertTrue(result);

      Map<Book, Integer> books = library.getBookMap();
      int amountOfBook = books.getOrDefault(book1, 0);
      assertEquals(amountOfBook, 2);
   }

   // 2.3
   @Test
   public void testAddBookAddFiveBooks() {
      library.addBook(book1);
      library.addBook(book1);
      library.addBook(book1);
      library.addBook(book1);

      boolean result = library.addBook(book1);
      assertTrue(result);

      Map<Book, Integer> books = library.getBookMap();
      int amountOfBook = books.getOrDefault(book1, 0);
      assertEquals(amountOfBook, 5);
   }

   // 3.1
   @Test
   public void testAddBookAddNull() {
      boolean result = library.addBook(null);
      assertFalse(result);
   }

   // 3.2
   @Test
   public void testAddBookAddNullBook() {
      Book nBook = new Book(null, null, null);
      boolean result = library.addBook(null);
      assertFalse(result);
   }

   // 4.1
   @Test
   public void testAddBookSameTittle() {
      Book book2 = new Book("Libro", "Autor 2", "codigo 2");

      library.addBook(book1);
      boolean result = library.addBook(book2);
      assertTrue(result);

   }

   // 4.2
   @Test
   public void testAddBookSameAutor() {
      Book book3 = new Book("Libro 3", "Autor", "codigo 3");

      library.addBook(book1);
      boolean result = library.addBook(book3);
      assertTrue(result);
   }

   // 4.3
   @Test
   public void testAddBookDiferentISBN() {
      Book book4 = new Book("Libro", "Autor", "codigo 4");

      library.addBook(book1);
      boolean result = library.addBook(book4);
      assertTrue(result);

   }

   // 5.1
   @Test
   public void testAddBookSameISBN() {
      Book book5 = new Book("Libro 5", "Autor 5", "codigo");

      library.addBook(book1);
      boolean result = library.addBook(book5);
      assertFalse(result);
   }

   // ================================================== CIERRE PRUEBAS ADDBOOK

   // ================================================== INICIO PRUEBAS LOAN BOOK
   /**
    * EXPLICACION DE MIS IDEAS
    * 1. Mire como debe "funcionar" basicamente el metodo
    * - LOAN tiene (Libro, usuario, fecha inicial, estado, fecha final)
    * - Se guardan en una List cada <Loan>
    * - El libro debe estar disponible
    * - El usuario debe existir
    * - El usuario no puede tener un prestamo por el mismo libro mientras haya otro
    * activo
    * - Estados de Loan #Active
    * - Fecha incial CurrentDate
    * - Fecha FInal estableci
    * - La cantidad de libros disminuye en 1 (Del libro que saco)
    * 
    * 2. Cosas basicas que deberian funcionar
    * 2.1 Ingresar un nuevo prestamo
    * 2.2 Un usuario debe obtener un prestamo con otro libro
    * 2.3 El estado del prestamo debe ser #ACTIVE
    * 2.4 La fecha de prestamo debe ser menor a la fecha de devolucion
    * 
    * 3. Cosas basicas que no deberian funcionar
    * 3.1 Pedir un prestamo con un usuario que no existe
    * 3.2 Pedir un prestamo con un usuario null
    * 3.3 Pedir un prestamo con un libro no existente o no dispobile (Sea como sea,
    * no hay de ese libroo)
    * 3.4 Pedir un prestamo con un libro null
    * 3.5 Repetir el prestamo mientras esta vigente un prestamo al mismo por el
    * libro
    * 
    * 4. Cosas mas avanzadas que deberian funcionar
    * 
    * 5. Cosas mas avanzadas que no deberian funcionar
    * 
    */

   // 2.1
   @Test
   public void testLoanBookNewLoan() {
      library.addBook(book1);
      Loan loan1 = library.loanABook("01", "codigo");
      assertTrue(user1.getId().equals(loan1.getUser().getId()));
   }

   // 2.2
   @Test
   public void testLoanBookOtherLoanOtherBook() {
      library.addBook(book1);
      library.addBook(book2);

      Loan loan1 = library.loanABook("01", "codigo");
      Loan loan2 = library.loanABook("01", "codigo 2");
      assertEquals(loan1.getUser(), loan2.getUser());

   }

   // 2.3
   @Test
   public void testLoanBookLoanStatus() {
      library.addBook(book1);
      Loan loan1 = library.loanABook("01", "codigo");
      assertEquals(LoanStatus.ACTIVE, loan1.getStatus());

   }

   // 2.4
   @Test
   public void testLoanBookDates() {
      library.addBook(book1);
      Loan loan1 = library.loanABook("01", "codigo");
      assertNotNull(loan1.getLoanDate());
      assertNotNull(loan1.getReturnDate());
      assertTrue(loan1.getLoanDate().isBefore(loan1.getReturnDate()));
   }

   // 3.1
   @Test
   public void testLoanBookUserDoesntExist() {
      List<Loan> l1 = library.getLoans();
      Loan loan1 = library.loanABook("9999", "codigo");
      List<Loan> l2 = library.getLoans();

      assertEquals(l1, l2);
   }

   // 3.2
   @Test
   public void testLoanBookNullUser() {
      List<Loan> l1 = library.getLoans();
      Loan loan1 = library.loanABook(null, "codigo");
      List<Loan> l2 = library.getLoans();

      assertEquals(l1, l2);
   }

   // 3.3
   @Test
   public void testLoanBookBookDoesntExist() {
      List<Loan> l1 = library.getLoans();
      Loan loan1 = library.loanABook("01", "CODIGOINEXISTENTE");
      List<Loan> l2 = library.getLoans();

      assertEquals(l1, l2);

   }

   // 3.4
   @Test
   public void testLoanBookNullBook() {
      List<Loan> l1 = library.getLoans();
      Loan loan1 = library.loanABook("01", null);
      List<Loan> l2 = library.getLoans();

      assertEquals(l1, l2);

   }

   // 3.5
   @Test
   public void testLoanBookDuplicateLoan() {
      Loan loan1 = library.loanABook("01", "codigo");
      List<Loan> l1 = library.getLoans();
      Loan loan2 = library.loanABook("01", "codigo");
      List<Loan> l2 = library.getLoans();

      assertEquals(l1, l2);

   }
}
