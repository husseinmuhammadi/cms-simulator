package com.tasnim.trade.eshop.web.service;

import com.tasnim.trade.eshop.configuration.security.JwtTokenUtil;
import com.tasnim.trade.eshop.dto.JwtCredential;
import com.tasnim.trade.eshop.dto.JwtToken;
import com.tasnim.trade.eshop.dto.Response;
import com.tasnim.trade.eshop.dto.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Response> createAuthenticationToken(@RequestBody JwtCredential credential) throws Exception {
        authenticate(credential.getUsername(), credential.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(credential.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new SuccessfulResponse<>(new JwtToken(token, userDetails.getUsername())));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
