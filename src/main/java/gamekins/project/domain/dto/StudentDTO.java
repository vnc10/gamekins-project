package gamekins.project.domain.dto;

import gamekins.project.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String raNumber;
    private String courseName;

    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setRaNumber(student.getRaNumber());
        if (student.getCourse() != null) {
            dto.setCourseName(student.getCourse().getName());
        }
        return dto;
    }
}
