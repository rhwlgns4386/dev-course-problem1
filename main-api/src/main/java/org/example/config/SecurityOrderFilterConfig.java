package org.example.config;

import org.example.dispatcher.Filter;
import org.example.dispatcher.OrderFilterConfig;
import org.example.dispatcher.OrderFilter;
import org.example.dispatcher.dto.Request;
import org.example.user.Authority;
import org.example.user.config.UserConfig;
import org.example.user.domain.service.UserRepository;

import java.util.List;
import java.util.Set;

public class SecurityOrderFilterConfig extends OrderFilterConfig {

    private final Set<String> adminPath = Set.of("/boards/edit", "/boards/add", "/boards/remove");
    private final Set<String> memberPath = Set.of("/posts/add", "/posts/edit", "/posts/remove","/accounts/remove","/accounts/edit","/accounts/signout");
    private final Set<String> annonyMousUrl = Set.of("/accounts/signin", "/accounts/signup");

    public AuthorityFilter adminFilter() {
        return new AuthorityFilter(0, Set.of(Authority.ADMIN), adminPath, userRepository());
    }

    public AuthorityFilter memberFilter() {
        return new AuthorityFilter(1, Set.of(Authority.ADMIN, Authority.MEMBER), memberPath, userRepository());
    }

    public OrderFilter anonymousFilter() {
        return new OrderFilter(2) {
            private final Set<String> urls = annonyMousUrl;
            @Override
            public void doFilter(Filter filter, Request request) {
                if(urls.contains(request.getPath())) {
                    if(request.hasSession()){
                        System.out.println("잘못된 접근입니다.");
                        return;
                    }
                }
                filter.doFilter(filter, request);
            }
        };
    }

    private UserRepository userRepository() {
        return UserConfig.userRepository();
    }

    @Override
    protected void addFilter(List<OrderFilter> orderFilters) {
        orderFilters.addAll(List.of(adminFilter(), memberFilter(), anonymousFilter()));
    }
}
