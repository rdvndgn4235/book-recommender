package com.rd.bookservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String author;
    private String description;
    private LocalDate publishedDate;
    private BigDecimal price;
}

