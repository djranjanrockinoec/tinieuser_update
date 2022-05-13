package com.tinie.Update.User.repositories;

import com.tinie.Update.User.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    /**
     * Retrieve a {@link UserDetails} matching given {@code ID} and {@code username}
     * @param phoneNumber Phone number to match on
     * @param username Username to match on
     * @return An {@link Optional} of {@link UserDetails} or {@link Optional#empty()}
     */
    Optional<UserDetails> findByPhoneNumberAndUsernameIgnoreCase(long phoneNumber, String username);
}
