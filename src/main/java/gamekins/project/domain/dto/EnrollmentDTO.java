package gamekins.project.domain.dto;

import gamekins.project.domain.Enrollment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentDTO {
    private Long id;
    private LocalDateTime enrollmentDate;
    private String status;
    private String studentName;
    private String subjectName;

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        dto.setStudentName(enrollment.getStudent().getName());
        dto.setSubjectName(enrollment.getSubject().getName());
        return dto;
    }
}
