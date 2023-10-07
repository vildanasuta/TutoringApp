package com.spring.common.services;

import com.spring.dao.models.MyUserDetails;
import com.spring.dao.models.Tutee;
import com.spring.dao.models.Tutor;
import com.spring.dao.models.User;
import com.spring.dao.repositories.TuteeRepository;
import com.spring.dao.repositories.TutorRepository;
import com.spring.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for UserDetailsService implementation
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    TuteeRepository tuteeRepository;

    /**
     * Retrieves a {@link UserDetails} object for a given username.
     *
     * @param userName the username to search for
     * @throws UsernameNotFoundException if the user is not found with the given username
     * @returna {@link UserDetails} object for the given username
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            Optional<Tutor> tutor = tutorRepository.findByEmail(userName);
            if (tutor.isPresent()) {
                return tutor.map(MyUserDetails::new).get();
            } else {
                Optional<Tutee> tutee = tuteeRepository.findByEmail(userName);
                if (tutee.isPresent()) {
                    return tutee.map(MyUserDetails::new).get();
                } else {
                    throw new UsernameNotFoundException("not found with " + userName);
                }
            }
        } else {
            return user.map(MyUserDetails::new).get();
        }
    }

    /**
     * CRUD Service method save()
     *
     * @param object instance of {@link User}
     * @return save User instance to repository
     */
    public User save(User object) {
        return userRepository.save(object);
    }


}
