
package com.comm.commApp.Configuration;



import com.comm.commApp.repository.RoleRepository;
import com.comm.commApp.repository.UserReository;
import com.comm.commApp.Model.Role;
import com.comm.commApp.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

      @Autowired
      RoleRepository roleRepository;
      @Autowired
      UserReository userReository;

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
         OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
         String email=token.getPrincipal().getAttributes().get("email").toString();
         if(userReository.findUserByEmail(email).isPresent())
         {

         }else
         {
            User user=new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
            List<Role> roles=new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
             user.setPassword(bCryptPasswordEncoder.encode("googleoauth2password"));

             user.setRoles(roles);
            userReository.save(user);

         }
         redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/home");

    }
}
