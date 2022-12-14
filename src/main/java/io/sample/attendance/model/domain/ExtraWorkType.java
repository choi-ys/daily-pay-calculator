package io.sample.attendance.model.domain;

import io.sample.global.model.EnumType;
import lombok.Getter;

@Getter
public enum ExtraWorkType implements EnumType {
    NIGHT_SHIFT(150, "야간 근무"),
    OVERTIME(100, "연장 근무");

    private int extraPayPerMinute;
    private String description;

    ExtraWorkType(int extraPayPerMinute, String description) {
        this.extraPayPerMinute = extraPayPerMinute;
        this.description = description;
    }

    public boolean isNightShift() {
        return this == NIGHT_SHIFT;
    }

    public boolean isOvertime() {
        return this == OVERTIME;
    }

    public int calculateExtraPay(int totalExtraWorkMinutes) {
        return totalExtraWorkMinutes * this.extraPayPerMinute;
    }

    @Override
    public String getName() {
        return name();
    }
}
