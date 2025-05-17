package com.example.alphaapi.config;

import com.example.alphaapi.model.dto.UserDto;
import com.example.alphaapi.model.dto.UserLoginDto;
import com.example.alphaapi.model.entity.*;
import com.example.alphaapi.model.enums.Role;
import com.example.alphaapi.repo.*;
import com.example.alphaapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class AppCommandRunner implements CommandLineRunner {

    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final PartRepo partRepo;
    private final LessonRepo lessonRepo;
    private final PdfFileRepo pdfFileRepo;
    private final QuizRepo quizRepo;
    private final QuizItemRepo quizItemRepo;


    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) {

        if (userRepo.findAll().isEmpty()) {

            userRepo.save(User.builder()
                    .name("khalil jawabra")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("12345678"))
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build()
            );

            courseRepo.save(Course.builder()
                    .teacherName("أحمد مقداد")
                    .name("تشريح")
                            .price(100000)
                    .build());
            Course course3 = courseRepo.save(Course.builder()
                    .teacherName("خليل جوابرة")
                    .name("لغات برمجة")
                    .price(200000)

                    .build());

//            @4e4eo3j

            partRepo.save(Part.builder()
                    .course(course3)
                    .name("Basic")
                    .price(20000)
                    .build());
            partRepo.save(Part.builder()
                    .course(course3)
                    .name("OOP")
                    .price(50000)
                    .build());

        }
        UserLoginDto adminRequest = UserLoginDto.builder()
                .email("admin@gmail.com")
                .password("12345678")
                .build();

        UserDto adminResponse = authService.login(adminRequest);

        System.out.println("============================= Admin =============================");
        System.out.println("ID: " + adminResponse.getId());
        System.out.println("Name: " + adminResponse.getName());
        System.out.println("Role: " + adminResponse.getRole());
        System.out.println("Token: " + adminResponse.getToken());


    }
}
