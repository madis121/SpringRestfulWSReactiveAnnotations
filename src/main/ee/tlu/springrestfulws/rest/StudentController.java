package ee.tlu.springrestfulws.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ee.tlu.springrestfulws.dto.Student;
import ee.tlu.springrestfulws.service.impl.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/schools/{schoolId}/students")
public class StudentController {

	private StudentRepository studentRepository;
	
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Flux<Student> getAllStudents(@PathVariable(value = "schoolId") Long schoolId) {
		return studentRepository.all(schoolId);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	public Mono<Student> getStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId) {
		return studentRepository.findById(schoolId, studentId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Mono<Void> insertStudent(@PathVariable(value = "schoolId") Long schoolId,
			@RequestBody Student student) {
		return studentRepository.save(schoolId, student);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
	public Mono<Student> updateStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId, @RequestBody Student student) {
		return studentRepository.update(schoolId, studentId, student);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
	public Mono<Void> removeStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId) {
		return studentRepository.delete(schoolId, studentId);
	}

}
