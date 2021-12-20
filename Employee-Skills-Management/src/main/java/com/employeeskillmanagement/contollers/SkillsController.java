package com.employeeskillmanagement.contollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeeskillmanagement.dto.SkillsDTO;
import com.employeeskillmanagement.entities.Skills;
import com.employeeskillmanagement.service.SkillsService;

@RestController
@RequestMapping("/api/skill")
public class SkillsController {

	@Autowired
	private SkillsService skillsService;

	@GetMapping("/skills")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Skills> getAllSkills() {
		return this.skillsService.getAllSkills();
	}

	@GetMapping(value = "/skills/{id}")
	public ResponseEntity<Skills> getSkillById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(this.skillsService.getSkillById(id));
	}

	@PostMapping(value = "/saveSkill")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Skills> create(@Valid @RequestBody SkillsDTO skillsDto) throws Exception {
		System.out.println("Controller");
		Skills skill = this.skillsService.createSkill(skillsDto);
		return new ResponseEntity<>(skill, HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateSkill/{id}")
	public ResponseEntity<Skills> update(@RequestBody SkillsDTO skillsDto, @PathVariable Integer id) {
		skillsDto.setSkill_id(id);
		System.out.println("Updated the Skills_Details");
		return ResponseEntity.ok().body(this.skillsService.updateSkill(skillsDto));
	}

	@DeleteMapping(value = "/deleteSkill/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HttpStatus delete(@PathVariable Integer id) {
		System.out.println("Deleted");
		this.skillsService.deleteSkillById(id);
		return HttpStatus.OK;
	}

}
