package com.users.component.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.exception.BusinessException;
import com.users.component.util.JwtTokenUtils;
import com.users.modules.user.service.impl.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiangchao on 2019/2/27.
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //log.info("JwtAuthenticationTokenFilter-->doFilterInternal.............");
        //获取Authorization字段
        String authHeader = request.getHeader("Authorization");
        String tokenHead = "Bearer";
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            //获取token
            String authToken = authHeader.substring(tokenHead.length());
            //获取用户名
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            try {
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //根据token用户名信息加载用户信息
                    UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                    //校验token和用户信息
                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        System.out.println("校验token和用户信息");
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } catch (BusinessException e) {
                Message message = new Message(STATUS.CODE_FAILURE, e.getMsg());
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(new ObjectMapper().writeValueAsString(message));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
