package uk.co.datadisk.ddflix.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uk.co.datadisk.ddflix.entities.user.Role;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class DdflixAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("User authentication: " + authentication.getPrincipal().toString());

        User user = userRepository.findByEmail(authentication.getPrincipal().toString());
        if(user == null) return null;

        System.out.println("Found user (authentication): " + user.getEmail());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            System.out.println("Adding role (ddflix authenticator): " + role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // do some checking, cause an exception to not login in
        System.out.println("User password credentials match? " + bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()));

        if( !bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()) ){
                return null;
        }

        if(user.isActive() == false || user.isEnabled() == false){
            System.out.println("User " + user.getEmail() + " is either not active or disabled");
            return null;
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), grantedAuthorities);

        session.setAttribute("roles", user.getRoles());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("id", user.getId());

        return token;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return UsernamePasswordAuthenticationToken.class.equals(auth);
    }
}