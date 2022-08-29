package com.booksapi.service.seviceImpl;

import com.booksapi.model.entities.Book;
import com.booksapi.model.dto.BookDto;
import com.booksapi.repository.BooksRepository;
import com.booksapi.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        System.out.println("Before Saving Book Details  "+bookDto.toString());
        Book book=this.mapper.map(bookDto,Book.class);
        Book newBook=booksRepository.save(book);
        System.out.println("After Saving Book Details  "+book.toString());
        BookDto newBookDto=this.mapper.map(newBook,BookDto.class);
        return newBookDto;
    }

    @Override
    public List<BookDto> fetchBooks() {
        List<Book> bookList=booksRepository.findAll();
        List<BookDto> bookDtoList=bookList.stream().map(book->this.mapper.map(book,BookDto.class)).
                                    collect(Collectors.toList());
        return bookDtoList;
    }

    @Override
    public BookDto getBook(Long bookId) throws Exception {
        return null;
    }

    @Override
    public BookDto updateBook(BookDto updatedBook, Long bookId) throws Exception {
        return null;
    }

    @Override
    public void deleteBook(Long bookId) throws Exception {

    }

    @Override
    public BookDto getByTitle(String title) {
        return null;
    }
}
