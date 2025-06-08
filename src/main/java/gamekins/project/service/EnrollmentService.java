package gamekins.project.service;

import gamekins.project.domain.Enrollment;
import gamekins.project.domain.Student;
import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.EnrollmentDTO;
import gamekins.project.mapper.EnrollmentMapper;
import gamekins.project.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<EnrollmentDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO create(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject subject = subjectRepository.findById(enrollmentDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Enrollment enrollment = EnrollmentMapper.toEntity(student, subject);
        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toDTO(enrollment);
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
