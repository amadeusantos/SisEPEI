package br.upe.sisepei.sisepei.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/demo-controller")
public class DemoController {
    //p testar se ta funcionando

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }

    
}
