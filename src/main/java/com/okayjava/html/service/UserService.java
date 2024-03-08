// UserService.java
package com.okayjava.html.service;

import com.okayjava.html.model.User;

public interface UserService {
    User getUserById(Long userId);
    void updateUser(User user);
}





