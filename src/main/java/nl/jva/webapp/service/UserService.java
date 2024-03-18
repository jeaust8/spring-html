// UserService.java
package nl.jva.webapp.service;

import nl.jva.webapp.model.User;

public interface UserService {
    User getUserById(Long userId);
    void updateUser(User user);
}





