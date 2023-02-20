package com.example.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Message {
    @Id // 해당 엔티티의 고유 식별자 역할을 한다. db의 pk로 지정한 컬럼이 해당된더.
    private long messageId;
    private String message;
}
