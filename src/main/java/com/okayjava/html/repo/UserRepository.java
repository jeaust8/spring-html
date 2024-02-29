package com.okayjava.html.repo;

import com.okayjava.html.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer> {
}
