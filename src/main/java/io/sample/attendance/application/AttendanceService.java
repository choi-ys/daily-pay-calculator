package io.sample.attendance.application;

import io.sample.attendance.dto.AttendanceDto.AttendanceRequest;
import io.sample.attendance.dto.AttendanceDto.AttendanceResponse;
import io.sample.attendance.repo.AttendanceRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;

    public AttendanceResponse saveAttendance(AttendanceRequest attendanceRequest) {
        return AttendanceResponse.from(attendanceRepo.save(attendanceRequest.toEntity()));
    }
}