package com.module9.services;

import com.module9.config.DatabaseConfig;
import com.module9.models.StudentAttendanceDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentAttendanceService {

    public List<StudentAttendanceDto> getStudentsFromDB(String groupFilter) {
        List<StudentAttendanceDto> result = new ArrayList<>();
        String sql = """
            SELECT s.id, s.name, g.name AS group_name, s.is_attended
            FROM students s
            JOIN groups g ON s.group_id = g.id
            """;

        //TODO: Не хватило StringUtils.hasText :)
        if (groupFilter != null && !groupFilter.isBlank()) {
            sql += " WHERE g.name = ?";
        }

        try (Connection conn = DatabaseConfig.getConnectionToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (groupFilter != null && !groupFilter.isBlank()) {
                stmt.setString(1, groupFilter);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentAttendanceDto dto = StudentAttendanceDto.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .groupName(rs.getString("group_name"))
                            .isAttended(rs.getBoolean("is_attended"))
                            .build();
                    result.add(dto);
                }
            }

        } catch (SQLException e) {
            log.error("Failed to fetch students", e);
        }

        return result;
    }


    public void insertStudent(String name, String groupName, boolean isAttended) {
        String findGroupSql = "SELECT id FROM groups WHERE name = ?";
        String insertGroupSql = "INSERT INTO groups(name) VALUES (?) RETURNING id";
        String insertStudentSql = "INSERT INTO students(name, group_id, is_attended) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnectionToDatabase()) {
            int groupId;

            try (PreparedStatement findGroupStmt = conn.prepareStatement(findGroupSql)) {
                findGroupStmt.setString(1, groupName);
                ResultSet rs = findGroupStmt.executeQuery();

                if (rs.next()) {
                    groupId = rs.getInt("id");
                } else {
                    try (PreparedStatement insertGroupStmt = conn.prepareStatement(insertGroupSql)) {
                        insertGroupStmt.setString(1, groupName);
                        ResultSet groupRs = insertGroupStmt.executeQuery();
                        groupRs.next();
                        groupId = groupRs.getInt("id");
                    }
                }
            }

            try (PreparedStatement insertStudentStmt = conn.prepareStatement(insertStudentSql)) {
                insertStudentStmt.setString(1, name);
                insertStudentStmt.setInt(2, groupId);
                insertStudentStmt.setBoolean(3, isAttended);
                insertStudentStmt.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Failed to insert student: {}", e.getMessage());
        }
    }

    public List<String> getAllGroups() {
        List<String> groups = new ArrayList<>();
        String sql = "SELECT name FROM groups ORDER BY name";

        try (Connection conn = DatabaseConfig.getConnectionToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                groups.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            log.error("Failed to fetch groups", e);
        }

        return groups;
    }

    public void deleteStudentById(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnectionToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete student", e);
        }
    }


}
