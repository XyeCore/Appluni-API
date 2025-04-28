package az.xecore.appluni.services;

import az.xecore.appluni.exceptions.UserAlreadyExistsException;
import az.xecore.appluni.exceptions.UserNotFoundException;
import az.xecore.appluni.models.User;
import az.xecore.appluni.repos.UserRepository;
import az.xecore.appluni.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {
    private final UserRepository repository;

    /**
     * Saves a user
     *
     * @param user the user to save
     * @return the saved user
     */
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    /**
     * Creates a new user
     *
     * @param user the user to create
     * @return the created user
     * @throws UserAlreadyExistsException if username or email already exists
     */
    @Transactional
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + user.getUsername());
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + user.getEmail());
        }

        return save(user);
    }

    /**
     * Gets user by username
     *
     * @param username the username to search for
     * @return the found user
     * @throws UsernameNotFoundException if user not found
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    /**
     * Provides UserDetailsService implementation
     *
     * @return UserDetailsService instance
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Gets the currently authenticated user
     *
     * @return the current user
     * @throws UsernameNotFoundException if user not found
     */
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Gets user by ID
     *
     * @param id the user ID
     * @return the found user
     * @throws UserNotFoundException if user not found
     */
    public User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    /**
     * Grants admin role to current user (for demonstration purposes)
     *
     * @deprecated This is for demo only, remove in production
     */
    @Deprecated
    @Transactional
    public void grantAdminToCurrentUser() {
        User user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}