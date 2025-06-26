package com.rd.bookservice.mapper;

import com.rd.bookservice.dto.BookRequest;
import com.rd.bookservice.dto.BookResponse;
import com.rd.bookservice.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequest request);
    BookResponse toResponse(Book book);
    List<BookResponse> toResponseList(List<Book> books);
    List<Book> toEntityList(List<BookRequest> books);
}
