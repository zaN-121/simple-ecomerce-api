package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.exception.FobiddenException;
import com.enigma.livecodeecomerce.model.Role;
import com.enigma.livecodeecomerce.model.request.RoleRequest;
import com.enigma.livecodeecomerce.model.response.SuccessResponse;
import com.enigma.livecodeecomerce.service.RoleService;
import com.enigma.livecodeecomerce.util.JwtUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
@Getter @Setter
@NoArgsConstructor
public class RoleController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity createRole(@RequestBody RoleRequest roleRequest, @RequestHeader(value = "Authorization", required = false) String token) {
//        token = token.split(" ")[1];
//        Map<String, String> claims = this.jwtUtil.getClaims(token);
//
//        if (!claims.get("subject").equals("admin")) {
//            throw new FobiddenException("Fobidden to access");
//        }
        Role role = this.roleService.create(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Role>("201", "success", "berhasil menambah role", role));

    }

    @GetMapping
    public ResponseEntity getRole() {
        List<Role> roles = this.roleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Role>>("200", "success", "berhasil menambah role", roles));

    }
}
