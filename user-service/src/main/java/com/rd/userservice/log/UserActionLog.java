package com.rd.userservice.log;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_action_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActionLog {

    @Id
    private String id;

    private String action; // CREATE, UPDATE, DELETE
    private Long userId;
    private String performedBy; // JWT varsa user bilgisi buraya
    private LocalDateTime timestamp;
}
