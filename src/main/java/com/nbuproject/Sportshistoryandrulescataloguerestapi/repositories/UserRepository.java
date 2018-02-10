package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

/**
 * Created by user on 10/28/2017.
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    Collection<User> findAll();
    User findUserByName(String name);
    User findUserById(String id);
    User findUserByUsername(String userName);
}
