package com.example.stamp;

import com.example.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stampId;

    @Column(nullable = false)
    private int stampCount;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // Member 와 일대일 매핑
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }
}
