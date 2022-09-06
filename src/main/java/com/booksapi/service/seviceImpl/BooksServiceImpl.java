package com.booksapi.service.seviceImpl;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.entities.Author;
import com.booksapi.model.entities.Book;
import com.booksapi.model.dto.BookDto;
import com.booksapi.repository.AuthorRepository;
import com.booksapi.repository.BooksRepository;
import com.booksapi.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService{

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public BookDto createBook(BookDto bookDto,int authorId) {
        System.out.println("Before Saving Book Details  "+bookDto.toString());
        Author author=authorRepository.findById(authorId).get();
        if(bookDto.getBook_image()==null ||bookDto.getBook_image().isEmpty())
            bookDto.setBook_image("default.png");
        Book book=this.mapper.map(bookDto,Book.class);
        book.setAuthor(author);
        Book newBook=booksRepository.save(book);
        System.out.println("After Saving Book Details  "+book.toString());
        BookDto newBookDto=this.mapper.map(newBook,BookDto.class);
        return newBookDto;
    }

    @Override
    public List<BookDto> fetchBooks() {
        List<Book> bookList= booksRepository.findAll();
        List<BookDto> bookDtoList=bookList.stream().map(book->{
            book.getAuthor().setBookList(new ArrayList<>());
            return this.mapper.map(book,BookDto.class);}
                        ).collect(Collectors.toList());
        return bookDtoList;

        //return bookDtoList;
    }

    @Override
    public BookDto getBook(int bookId) throws Exception {
        Book book=booksRepository.findById(bookId).orElseThrow(()->new Exception("Book not found"));
        return mapper.map(book,BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto updatedBook, int bookId) throws Exception {
        Author author=authorRepository.findById(updatedBook.getAuthorId()).get();
        Book book=booksRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundEx("Book not found",HttpStatus.NOT_FOUND));
        //book.setBookId(updatedBook.getBookId());
        book.setBookDes(updatedBook.getBookDes());
        book.setBook_image(updatedBook.getBook_image());
        book.setPrice(updatedBook.getPrice());
        book.setTitle(updatedBook.getTitle());
        book.setGenreId(updatedBook.getGenreId());
        book.setRating(updatedBook.getRating());
        book.setAuthor(author);
        book.setPublishedDate(updatedBook.getPublishedDate());
        Book res=this.booksRepository.save(book);
        return mapper.map(res,BookDto.class);
    }

    @Override
    public void deleteBook(int bookId) throws Exception {
        Book book=booksRepository.findById(bookId).orElseThrow(()->new Exception("Book not found"));
        booksRepository.delete(book);
    }

    @Override
    public List<BookDto> getByTitle(String title) throws Exception {
        List<Book> books=booksRepository.findByTitleContaining(title);
        List<BookDto> bookDtoList=books.stream().map(book->this.mapper.map(book,BookDto.class)).
                collect(Collectors.toList());
        return bookDtoList;
    }

}
