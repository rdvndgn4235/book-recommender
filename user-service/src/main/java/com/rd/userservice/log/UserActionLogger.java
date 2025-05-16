package com.rd.userservice.log;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActionLogger {

    private final UserActionLogRepository userActionLogRepository;

    public UserActionLogger(UserActionLogRepository userActionLogRepository) {
        this.userActionLogRepository = userActionLogRepository;
    }

    public void logAction(String action, Long userId, String performedBy) {
        UserActionLog log = UserActionLog.builder()
                                         .action(action)
                                         .userId(userId)
                                         .performedBy(performedBy)
                                         .timestamp(LocalDateTime.now())
                                         .build();
        userActionLogRepository.save(log);
    }

}
