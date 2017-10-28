package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by user on 10/28/2017.
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    User findUserByName(String name);
    User findUserById(String id);
    User findUserByUsername(String userName);
}
