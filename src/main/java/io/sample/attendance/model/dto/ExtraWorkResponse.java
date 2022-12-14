package io.sample.attendance.model.dto;

import io.sample.attendance.model.domain.ExtraWork;
import io.sample.attendance.model.domain.ExtraWorkType;
import io.sample.attendance.model.domain.TimeTable;
import io.sample.attendance.model.domain.WorkDuration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtraWorkResponse {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private WorkDuration workDuration;
    private ExtraWorkType extraWorkType;
    private int extraPay;

    private ExtraWorkResponse(
        Long id,
        LocalDateTime startAt,
        LocalDateTime endAt,
        WorkDuration workDuration,
        ExtraWorkType extraWorkType,
        int extraPay
    ) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.workDuration = workDuration;
        this.extraWorkType = extraWorkType;
        this.extraPay = extraPay;
    }

    public static ExtraWorkResponse from(ExtraWork extraWork) {
        TimeTable extraWorkTimeTable = TimeTable.of(extraWork.getStartAt(), extraWork.getEndAt());
        return new ExtraWorkResponse(
            extraWork.getId(),
            extraWorkTimeTable.getStartAt(),
            extraWorkTimeTable.getEndAt(),
            extraWorkTimeTable.getWorkDuration(),
            extraWork.getExtraWorkType(),
            extraWork.getPay()
        );
    }
}
