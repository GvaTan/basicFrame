/**
 * 
 */
package top.anets.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@RestController
public class HelloController {
    @RequestMapping("/hi")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok().body("auth success!");
    }
}