package com.example.unit_test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MailTask {
    @GeneratedValue()
    @Id
    private Integer Id ;
    String calledClass;

    @Enumerated(EnumType.STRING)
    private QuartzStatus quartzStatus;

    private Integer executionCount;
    public MailTask(Integer id, String calledClass) {
        Id = id;
        this.calledClass = calledClass;
    }
    private boolean isBounced;
    private  boolean isSent ;
    private boolean isOpened;
    public MailTask(Integer id, String calledClass, QuartzStatus quartzStatus) {
        Id = id;
        this.calledClass = calledClass;
        this.quartzStatus = quartzStatus;
    }

    public MailTask(Integer id, String calledClass, QuartzStatus quartzStatus, Integer executionCount) {
        Id = id;
        this.calledClass = calledClass;
        this.quartzStatus = quartzStatus;
        this.executionCount = executionCount;
    }

    public MailTask(Integer id, String calledClass, QuartzStatus quartzStatus, Integer executionCount, boolean isBounced, boolean isSent, boolean isOpened) {
        Id = id;
        this.calledClass = calledClass;
        this.quartzStatus = quartzStatus;
        this.executionCount = executionCount;
        this.isBounced = isBounced;
        this.isSent = isSent;
        this.isOpened = isOpened;
    }

    public MailTask() {
    }
}
