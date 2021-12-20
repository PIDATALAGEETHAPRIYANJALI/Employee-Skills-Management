package com.employeeskillmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeskillmanagement.dto.SkillsDTO;
import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.entities.Mail;
import com.employeeskillmanagement.entities.Organization;
import com.employeeskillmanagement.entities.PasswordGenerator;
import com.employeeskillmanagement.entities.Skills;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.exception.CustomException;
import com.employeeskillmanagement.repository.AuthorityRepository;
import com.employeeskillmanagement.repository.OrganizationRepository;
import com.employeeskillmanagement.repository.SkillsRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordGenerator passwordGenerator;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	@Transactional
	public Skills createSkill(SkillsDTO skillsDto) {

		Skills skills = new Skills();

		skills.setSkill_name(skillsDto.getSkill_name());
		skills.setDescription(skillsDto.getDescription());

		List<UserDTO> userdto = skillsDto.getUserDto();

		List<User> u = new ArrayList();

		for (int i = 0; i < userdto.size(); i++) {

			User user1 = null;

			user1 = new User();

			user1.setFirstName(userdto.get(i).getFirstName());
			user1.setLastName(userdto.get(i).getLastName());
			user1.setUsername(userdto.get(i).getUsername());
			user1.setEmail(userdto.get(i).getEmail());
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String pass = passwordGenerator.generateRandomPassword(8);
			String encodedPassword = passwordEncoder.encode(pass);
			user1.setPassword(encodedPassword);

			User user2 = null;

			List<Authority> listAll = authorityRepository.findAll();
			String superAdmin = listAll.get(0).getName();
			List<String> superList = new ArrayList<>();
			superList.add(superAdmin);

			List<Authority> addAuthorities = authorityRepository.find(userdto.get(i).getRole());

			if (superList.equals(userdto.get(i).getRole())) {
				throw new CustomException("this role was not added ");
			} else {

				user1.setAuthorities(addAuthorities);
				user2 = userRepository.save(user1);
				u.add(user2);

				Mail mail = new Mail();
				mail.setSubject("Welcome to Employee Skill Management Project");
				mail.setToEmail(user1.getEmail());
				mail.setContent("Your credentials are :" + "\n" + "username : " + user1.getUsername() + "\n"
						+ "password :" + pass);
				try {
					emailService.sendEmail(mail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		skills.setUser(u);

		// to get organization id in skills table (2 lines code) and should have to
		// declare OrganizationName in SkillsDTO.
		Organization org = this.organizationRepository.findByOrganizationName(skillsDto.getOrganizationName());
		skills.setOrganization(org);

		return skillsRepository.save(skills);

	}

	@Override
	public Skills getSkillById(Integer id) {
		Optional<Skills> skill = this.skillsRepository.findById(id);

		if (skill.isPresent()) {
			return skill.get();
		} else {
			throw new CustomException("Record not found with this id" + id);
		}
	}

	@Override
	public List<Skills> getAllSkills() {
		return this.skillsRepository.findAll();
	}

	@Override
	public Skills updateSkill(SkillsDTO skillsDto) {

		Optional<Skills> skill = this.skillsRepository.findById(skillsDto.getSkill_id());

		if (skill.isPresent()) {

			Skills skillUpdate = new Skills();
			skillUpdate.setSkill_id(skill.get().getSkill_id());
			skillUpdate.setSkill_name(skillsDto.getSkill_name());
			skillUpdate.setDescription(skillsDto.getDescription());

			List<UserDTO> user = skillsDto.getUserDto();

			List<User> user1 = new ArrayList();

			for (int i = 0; i < user.size(); i++) {
				User userUpdate = this.userRepository.findByUsername(user.get(i).getUsername());
				user1.add(userUpdate);

			}
			skillUpdate.setUser(user1);

			this.skillsRepository.save(skillUpdate);

			return skillUpdate;

		} else {
			throw new CustomException("Record not found with this id :" + skillsDto.getSkill_id());
		}

	}

	@Override
	@Transactional
	public void deleteSkillById(Integer id) {
		Optional<Skills> skill = this.skillsRepository.findById(id);

		if (skill.isPresent()) {
			this.skillsRepository.deleteById(id);
		} else {
			throw new CustomException("Record not found with this id :" + id);
		}

	}

}
