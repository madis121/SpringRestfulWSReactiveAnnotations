package ee.tlu.springrestfulws.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ee.tlu.springrestfulws.dto.School;
import ee.tlu.springrestfulws.service.impl.SchoolRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/schools")
public class SchoolController {

	private SchoolRepository schoolRepository;
	
	public SchoolController(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Flux<School> getAllSchools() {
		return schoolRepository.all();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Mono<School> getSchool(@PathVariable(value = "id") Long id) {
		return schoolRepository.findById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Mono<Void> insertSchool(@RequestBody School school) {
		return schoolRepository.save(school);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Mono<School> updateSchool(@PathVariable(value = "id") Long id, @RequestBody School school) {
		return schoolRepository.update(id, school);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Mono<Void> removeSchool(@PathVariable(value = "id") Long id) {
		return schoolRepository.delete(id);
	}

}
