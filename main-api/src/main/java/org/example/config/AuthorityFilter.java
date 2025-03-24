package org.example.config;

import org.example.dispatcher.Filter;
import org.example.dispatcher.OrderFilter;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.session.Session;
import org.example.user.Authority;
import org.example.user.domain.entity.User;
import org.example.user.domain.service.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorityFilter extends OrderFilter {

    private final Set<Authority> authority;
    private final Set<String> url;
    private final UserRepository userRepository;

    public AuthorityFilter(int order, Set<Authority> authority, Set<String> url,UserRepository userRepository) {
        super(order);
        this.authority = authority;
        this.url = url;
        this.userRepository = userRepository;
    }


    @Override
    public void doFilter(Filter filter, Request request) {
        if(url.contains(request.getPath())){
            if(!request.hasSession()){
                System.out.println("접근 권한이 없습니다.");
                return;
            }
            Session session = request.getSession();
            Long id = session.get("userId", Long.class);
            Optional<User> findUser = userRepository.findById(id);
            if(findUser.isEmpty()){
                System.out.println("접근 권한이 없습니다.");
                return;
            }
            User user = findUser.get();
            if(!authority.contains(user.getAuthority())){
                System.out.println("접근 권한이 없습니다.");
                return;
            }
        }

        filter.doFilter(filter, request);
    }
}
