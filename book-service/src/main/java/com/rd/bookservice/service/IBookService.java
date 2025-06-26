package com.rd.bookservice.service;

import com.rd.bookservice.dto.BookRequest;
import com.rd.bookservice.dto.BookResponse;

import java.util.List;

public interface IBookService {

    BookResponse createBook(BookRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest request);

    void deleteBook(Long id);

}
