package io.muic.ssc.project.backend.init;

import io.muic.ssc.project.backend.User;
import io.muic.ssc.project.backend.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // add default admin user and set its password if missing
        User admin = userRepository.findFirstByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("USER");
            userRepository.save(admin);
        }
    }
}
