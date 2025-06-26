package com.rd.bookservice.repository;

import com.rd.bookservice.log.BookLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookLogRepository extends MongoRepository<BookLog, String> {
}

