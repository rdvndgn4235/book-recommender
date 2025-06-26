package com.rd.bookservice.service;

import com.rd.bookservice.log.BookLog;
import com.rd.bookservice.repository.BookLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BookLogService {

    private final BookLogRepository bookLogRepository;

    public void logBookAccess(String action, Long bookId, String username) {
        BookLog log = BookLog.builder()
                             .action(action)
                             .bookId(bookId)
                             .username(username)
                             .timestamp(Instant.now().toString())
                             .build();
        bookLogRepository.save(log);
    }
}

