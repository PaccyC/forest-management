package com.paccy.spring_project.services;



import com.paccy.spring_project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository repo;

    public UserDetailsServiceImpl(UserRepository repo) {
        super();
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found") );
    }

}