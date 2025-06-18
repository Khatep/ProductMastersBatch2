package com.module9.servlets;

import com.module9.models.StudentAttendanceDto;
import com.module9.services.StudentAttendanceService;
import com.module9.utils.AttendanceNameUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;

@WebServlet("/students/attendance")
public class StudentAttendanceServlet extends HttpServlet {
    private final StudentAttendanceService studentAttendanceService = new StudentAttendanceService();

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> groups = studentAttendanceService.getAllGroups();
        String selectedGroup = req.getParameter("group");
        List<StudentAttendanceDto> list = studentAttendanceService.getStudentsFromDB(selectedGroup);

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("""
                    <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                margin: 20px;
                            }
                            h2 {
                                color: #333;
                            }
                            .form-row {
                                display: flex;
                                align-items: flex-start;
                                gap: 50px;
                                margin-bottom: 40px;
                            }
                            form {
                                margin: 0;
                            }
                            table {
                                width: 90%;
                                border-collapse: collapse;
                                margin-top: 20px;
                                font-size: 16px;
                            }
                            th, td {
                                border: 1px solid #ddd;
                                padding: 10px;
                                text-align: left;
                            }
                            th {
                                background-color: #f2f2f2;
                            }
                            input[type='text'], select {
                                margin: 8px 0;
                                padding: 8px;
                                width: 250px;
                                display: block;
                            }
                            input[type='submit'], button {
                                margin-top: 10px;
                                padding: 8px 16px;
                                background-color: #4CAF50;
                                color: white;
                                border: none;
                                cursor: pointer;
                            }
                            input[type='submit']:hover, button:hover {
                                background-color: #45a049;
                            }
                        </style>
                    </head>
                    <body>
                        <h2>Посещение лекций</h2>
                        <div class="form-row">
                """);

        out.println("""
                    <form method="GET" action="/api/students/attendance">
                        Группа:
                        <select name="group">
                            <option value="">-- Все группы --</option>
                """);
        for (String g : groups) {
            String selected = g.equals(selectedGroup) ? "selected" : "";
            out.printf("""
                        <option value="%s" %s>%s</option>
                    """, g, selected, g);
        }
        out.println("""
                        </select>
                        <input type="submit" value="Фильтровать">
                    </form>
                """);

        out.println("""
                    <form action="/api/students/attendance" method="POST">
                        ФИО: <input type="text" name="name" required>
                        Группа: <input type="text" name="groupName" required>
                        Посетил:
                        <select name="isAttended">
                            <option value="true">Да</option>
                            <option value="false">Нет</option>
                        </select>
                        <input type="submit" value="Добавить">
                    </form>
                    </div>
                """);

        out.println("""
                    <table>
                        <tr>
                            <th>ФИО</th>
                            <th>Группа</th>
                            <th>Посетил</th>
                            <th>Удалить</th>
                        </tr>
                """);

        if (list.isEmpty()) {
            out.println("""
                        <tr><td colspan="4">Нет данных</td></tr>
                    """);
        } else {
            for (StudentAttendanceDto studentAttendanceDto : list) {
                out.printf("""
                                <tr>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td><button onclick="deleteStudent(%d)">Удалить</button></td>
                                </tr>
                                """,
                        studentAttendanceDto.getName(),
                        studentAttendanceDto.getGroupName(),
                        AttendanceNameUtil.fromBooleanToString(studentAttendanceDto.isAttended()),
                        studentAttendanceDto.getId()
                );
            }
        }

        out.println("""
                    </table>
                    <script>
                        function deleteStudent(id) {
                            if (!confirm("Удалить этого студента?")) return;

                            fetch('/api/students/attendance?id=' + id, {
                                method: 'DELETE'
                            }).then(response => {
                                if (response.ok) {
                                    location.reload();
                                } else {
                                    alert("Ошибка при удалении");
                                }
                            });
                        }
                    </script>
                    </body>
                    </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String groupName = req.getParameter("groupName");
        boolean isAttended = Boolean.parseBoolean(req.getParameter("isAttended"));

        studentAttendanceService.insertStudent(name, groupName, isAttended);

        resp.sendRedirect("/api/students/attendance");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            studentAttendanceService.deleteStudentById(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing student id");
        }
    }
}
