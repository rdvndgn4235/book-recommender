package com.rd.bookservice.log;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_logs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLog {
    @Id
    private String id;
    private String action;
    private Long bookId;
    private String username;
    private String timestamp;
}

