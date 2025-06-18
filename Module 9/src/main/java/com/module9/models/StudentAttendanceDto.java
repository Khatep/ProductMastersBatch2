package com.module9.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAttendanceDto {
    Integer id;
    String name;
    String groupName;
    boolean isAttended;
}
