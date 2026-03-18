package com.placementportal;

import com.placementportal.entity.User;
import com.placementportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PlacementPortalApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PlacementPortalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if not already present
        if (userRepository.findByEmail("admin@college.com").isEmpty()) {
            User admin = new User();
            admin.setName("Placement Admin");
            admin.setEmail("admin@college.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
            System.out.println("========================================");
            System.out.println("Default Admin created:");
            System.out.println("  Email   : admin@college.com");
            System.out.println("  Password: admin123");
            System.out.println("========================================");
        } else {
            System.out.println("Admin account already exists. Skipping creation.");
        }
    }
}
