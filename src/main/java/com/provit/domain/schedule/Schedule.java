package com.provit.domain.schedule;

import com.provit.domain.BaseTimeEntity;
import com.provit.domain.member.Member;
import com.provit.domain.schedule.annotation.ScheduleDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SCHEDULE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    @Column(length = 1000)
    private String content;
    @ScheduleDate
    private String startDate;
    @ScheduleDate
    private String endDate;
}
