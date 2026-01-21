package shubham.JobTracker.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shubham.JobTracker.Service.UserDetailServiceImpl;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {


    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");


        String userName = null;
        String token  = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("header "+ authHeader);
            token = authHeader.substring(7);
            userName = jwtUtil.extractUserName(token);
            System.out.println(userName);// here is the user name

        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()== null){
            UserDetails userDetails = userDetailService.loadUserByUsername(userName);

            if(jwtUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken  =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request,response);

    }
}

