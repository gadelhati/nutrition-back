package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseAuth;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestAuth;
import br.eti.gadelha.nutrition.service.ServiceAuth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class ControllerAuth {

    private final ServiceAuth serviceAuth;

    @PostMapping("/login")
    public ResponseEntity<DTOResponseAuth> login(@RequestBody @Valid DTORequestAuth value){
        return ResponseEntity.accepted().body(serviceAuth.login(value));
    }
}