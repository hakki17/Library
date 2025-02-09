//Aca estoy importando todas las clases a las que le debemos hacer las pruebas o usamos para las pruebas
package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;

//Importaciones de las cosas para los test
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
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
    private Book book3;
    private Book book4;

    @BeforeEach
    public void setup(){
        library = new Library();

        book1 = new Book("Libro", "Autor","codigo" );
        book2 = new Book("Libro #2", "Autor #2 ","codigo #2" );
        book3 = new Book("Libro #3", "Autor #3 ","codigo #3" );
        book4 = new Book("Libro #4", "Autor #4 ","codigo #4" );
        
    }

    @AfterEach
    public void setdown(){
        library = null;
    }

    /** EXPLICACION DE MIS IDEAS
     * 1. Mire como debe "funcionar" basicamente el metodo
     * - los Book se guardan en un mapa. Donde esta libro:cantidad de ese libro
     *  - Cada libro tiene nombre, autor y codigo de barras.
     * - Si el libro no existe al añadirse al mapa, aparece como 1
     * - Si el libro ya existe al añadirse al mapa, incrementa en 1
     * - Si la operacion es satisfactoria (TIpo se hizo lo que queriamos hacer) da true,si no, da false
     * 
     * 2. Cosas basicas que deberian funcionar
     * 2.1 Añadir un libro nuevo. El conteo de este libro en el mapa debe ser 1
     * 2.2 Añadir un libro existente. El conteo de este libro en el mapa debe ser 2
     * 2.3 Añadir 5 veces un libro. El conteo de este libro en el mapa debe ser 5
     * 
     * 3. Cosas basicas que no deberian funcionar
     * 3.1 Añadir un string
     * 3.2 Añadir un bool
     * 3.3 Añadir un user
     * 3.4 Añadir null
     * 3.5 Añadir un book lleno de nulls (Todos sus campos son nulos)
     * 
     * 4. Cosas mas avanzadas que deberian funcionar
     * 4.1 Añadir un libro con un nombre igual a otro libro, pero de diferente autor
     * 4.2 Añadir un libro con un nombre diferente a otro libro, pero de igual autor
     * 
     * 5. Cosas mas avanzadas que no deberian funcionar
     * 5.1 Añadir un lirbo con mismo nombre y autor pero diferente ISBN
     * 5.2 Añadir un libro con un ISBN igual a otro pero todo lo demas diferente
     * 
     * Ya con eso dije, deben ser pruebas suficientes para que esto funcione
     */


     //2.1
     @Test
     public void testAddBookNewBook (){    
        boolean result = library.addBook(book1);
        assertTrue(result);
        
        Map<Book, Integer> books = library.getBookMap();
        int amountOfBook = books.getOrDefault(book1, 0);
        assertEquals(amountOfBook,1);

     }

     //2.2
     @Test
     public void testAddBookExistingBook (){
        library.addBook(book1); 

        boolean result = library.addBook(book1);
        assertTrue(result);
        
        Map<Book, Integer> books = library.getBookMap();
        int amountOfBook = books.getOrDefault(book1, 0);
        assertEquals(amountOfBook,2);
     }
    
     //2.3
     @Test
     public void testAddBookAddFiveBooks (){
        library.addBook(book1); 
        library.addBook(book1); 
        library.addBook(book1); 
        library.addBook(book1); 

        boolean result = library.addBook(book1);
        assertTrue(result);
        
        Map<Book, Integer> books = library.getBookMap();
        int amountOfBook = books.getOrDefault(book1, 0);
        assertEquals(amountOfBook,5);
     }


     @Test
     public void testAddBook (){

     }
     



    



}
