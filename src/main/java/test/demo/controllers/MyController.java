package test.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.services.MyService;

@RestController
@RequestMapping("/api/numbers")
public class MyController {
    @Autowired
    private MyService myService;

    @Operation(summary = "Найти N-ное минимальное число из Excel файла")
    @GetMapping("/find-nth-min")
    public ResponseEntity<?> findNthMinNumber(
            @RequestParam String filePath,
            @RequestParam int n) {

        try {
            int result = myService.findMin(filePath, n);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
