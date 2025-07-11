package com.rd.bookservice.service;

import com.rd.bookservice.dto.BookRequest;
import com.rd.bookservice.dto.BookResponse;
import com.rd.bookservice.exception.ResourceNotFoundException;
import com.rd.bookservice.mapper.BookMapper;
import com.rd.bookservice.model.Book;
import com.rd.bookservice.repository.BookLogRepository;
import com.rd.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookLogService bookLogService;

    @CacheEvict(value = {"books", "book"}, allEntries = true)
    @Override
    public BookResponse createBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);
        Book saved = bookRepository.save(book);
        bookLogService.logBookAccess("CREATE_BOOK_BY_ID", saved.getId(), null);
        return bookMapper.toResponse(saved);
    }

    @Cacheable(value = "books")
    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(bookMapper::toResponse)
                             .collect(Collectors.toList());
    }

    @Cacheable(value = "books", key = "#id")
    @Override
    public BookResponse getBookById(Long id) {
        bookLogService.logBookAccess("GET_BOOK_BY_ID",id, null);
        return bookRepository.findById(id)
                             .map(bookMapper::toResponse)
                             .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @CachePut(value = "books", key = "#id")
    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPublishedDate(request.getPublishedDate());
        book.setPrice(request.getPrice());

        bookLogService.logBookAccess("UPDATE_BOOK_BY_ID",id, null);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @CacheEvict(value = "books", key = "#id")
    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
        bookLogService.logBookAccess("DELETE_BOOK_BY_ID",id, null);
    }
}
