package com.Zazahome;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.User;


@SpringBootApplication
@ComponentScan(basePackages = "com.Zazahome.*")
public class ZazahomeApplication implements CommandLineRunner {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(ZazahomeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();

		user.setId(1);
		user.setEmail("admin@Zazahome.com");
		user.setEnable(true);
		user.setName("Rhazali ABDERRAZZAQ");
		user.setPhone("0760391206");
		user.setRole("ROLE_ADMIN");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setProfile("admin.png");
		user.setDate(new Date());

		this.userRepo.save(user);



	}

}
