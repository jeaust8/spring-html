package com.okayjava.html.repo;

import com.okayjava.html.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFnameContainingOrLnameContainingOrEmailContainingOrPhonenumberContaining(String fname, String lname, String email, String phonenumber);

    boolean existsByUserId(Long userid);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    boolean existsByPhonenumber(String phonenumber);

}
