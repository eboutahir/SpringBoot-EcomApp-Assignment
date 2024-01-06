package com.example.APPEcom;


import com.example.APPEcom.Comptes.Models.Role;
import com.example.APPEcom.Comptes.Models.RoleEnum;
import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Comptes.Repositories.CompteRepository;
import com.example.APPEcom.Comptes.Repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

	private final CompteRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public SetupDataLoader(CompteRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		SecurityContextHolder.clearContext();
		if (alreadySetup)
			return;
		
		createRoleIfNotFound(RoleEnum.ROLE_ADMIN.name());
		createRoleIfNotFound(RoleEnum.ROLE_USER.name());


		Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.name());
		Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER.name());

		
		creatUserIfNotFound("admin@test.com", "test123", new Role[]{adminRole,userRole});
		creatUserIfNotFound("user@test.com", "test123", new Role[]{userRole});


		alreadySetup = true;
	}
	
	@Transactional
	public void creatUserIfNotFound(String userName, String password,Role[] role) {
		if(userRepository.findByEmail(userName) == null) {
			User user = new User();
			user.setFirstName("Test");
			user.setLasttName("usercin");
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(userName);

			Set<Role> roles = new HashSet<Role>(Arrays.asList(role));
			user.setRoles(roles);
			userRepository.save(user);
		}
		
	}

	@Transactional
	public void createRoleIfNotFound(String name) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
	}
	 
}