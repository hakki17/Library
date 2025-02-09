//Aca estoy importando todas las clases a las que le debemos hacer las pruebas o usamos para las pruebas
package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

//Importaciones de las cosas para los test
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse; 
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
    public void setUp(){
          //===================== DATOS PARA LOANBOOK
          library = new Library();
          book1 = new Book("Libro", "Autor","codigo" );
          
          user1 = new User();
          user1.setName("User 1");
          user1.setId("01");

          library.addBook(book1);
          library.addBook(book1);

          library.addUser(user1);

          //===================== 
          
    }

    @AfterEach
    public void setDown(){
        library = null;
    }


    //================================================== INICIO PRUEBAS LOAN BOOK
    /** EXPLICACION DE MIS IDEAS
     * 1. Mire como debe "funcionar" basicamente el metodo
     * - LOAN tiene (Libro, usuario, fecha inicial, estado, fecha final)
     * - Se guardan en una List cada <Loan>
     * - El libro debe estar disponible
     * - El usuario debe existir
     * - El usuario no puede tener un prestamo por el mismo libro mientras haya otro activo
     * - Estados de Loan #Active
     * - Fecha incial CurrentDate
     * - Fecha FInal estableci
     * - La cantidad de libros disminuye en 1 (Del libro que saco)
     * 
     * 2. Cosas basicas que deberian funcionar
     * 2.1 Ingresar un nuevo prestamo
     * 2.2 Un usuario debe obtener un prestamo con otro libro
     * 2.3 El estado del prestamo debe ser #ACTIVE
     * 2.4 La fecha del prestamo debe ser hoy (hoy es sea cuando sea que usted ejecute esto)
     * 2.5 La fecha de prestamo debe ser menor a la fecha de devolucion
     * 
     * 3. Cosas basicas que no deberian funcionar
     * 3.1 Pedir un prestamo con un usuario que no existe
     * 3.2 Pedir un prestamo con un usuario null
     * 3.3 Pedir un prestamo con un libro no existente o no dispobile (Sea como sea, no hay de ese libroo)
     * 3.4 Pedir un prestamo con un libro null
     * 3.5 Repetir el prestamo mientras esta vigente un prestamo al mismo por el libro
     * 
     * 4. Cosas mas avanzadas que deberian funcionar
     
     * 5. Cosas mas avanzadas que no deberian funcionar
     * 
     */

     //2.1
     @Test
     public void testLoanBookNewLoan (){
        Loan loan1 = library.loanABook("01", "codigo");
        assertEquals(user1, loan1.getUser());
     }

     //2.2
     @Test
     public void testLoanBookOtherLoanOtherBook (){
        Loan loan1 = library.loanABook("01", "codigo");
        Loan loan2 = library.loanABook("01", "codigo 2");
        assertEquals(loan1.getUser(), loan2.getUser());
        
     }

     //2.3
     @Test
     public void testLoanBookLoanStatus (){
        Loan loan1 = library.loanABook("01", "codigo");
        assertEquals(LoanStatus.ACTIVE,loan1.getStatus());
        
     }

     //2.4
     @Test
     public void testLoanBookLoanDate (){
        Loan loan1 = library.loanABook("01", "codigo");
        assertEquals(LocalDate.now(),loan1.getLoanDate());
        
     }

     //2.5
     @Test
     public void testLoanBookDates (){
        Loan loan1 = library.loanABook("01", "codigo");
        assertTrue(loan1.getLoanDate().isBefore(loan1.getReturnDate()));
     }

     //3.1
     @Test
     public void testLoanBookUserDoesntExist (){
        List<Loan> l1 = library.getLoans();
        Loan loan1 = library.loanABook("9999", "codigo");
        List<Loan> l2 = library.getLoans();

        assertEquals(l1,l2);
     }

     //3.2
     @Test
     public void testLoanBookNullUser (){
        List<Loan> l1 = library.getLoans();
        Loan loan1 = library.loanABook(null, "codigo");
        List<Loan> l2 = library.getLoans();

        assertEquals(l1,l2);
     }

     //3.3
     @Test
     public void testLoanBookBookDoesntExist (){
        List<Loan> l1 = library.getLoans();
        Loan loan1 = library.loanABook("01", "CODIGOINEXISTENTE");
        List<Loan> l2 = library.getLoans();

        assertEquals(l1,l2);
        
     }

     //3.4
     @Test
     public void testLoanBookNullBook (){
        List<Loan> l1 = library.getLoans();
        Loan loan1 = library.loanABook("01", null);
        List<Loan> l2 = library.getLoans();

        assertEquals(l1,l2);
        
     }

     //3.5
     @Test
     public void testLoanBookDuplicateLoan (){
        Loan loan1 = library.loanABook("01", "codigo");
        List<Loan> l1 = library.getLoans();
        Loan loan2 = library.loanABook("01", "codigo");
        List<Loan> l2 = library.getLoans();

        assertEquals(l1,l2);
        
     }
}
