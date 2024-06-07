package com.example.backend;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestRepo testRepo;

    @GetMapping("/")
    public ResponseEntity<List<Test>> getTests() {
        List<Test> tests = testRepo.findAll();
        System.out.println(Arrays.toString(tests.toArray()));
        return ResponseEntity.ok(tests);
    }

}
