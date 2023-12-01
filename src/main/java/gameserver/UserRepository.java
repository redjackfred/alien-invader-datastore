package com.example.gameserver;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface UserRepository extends DatastoreRepository<User, Long> {
    List<User> findAllByUserId(String userId);  // get all userName from the same userId
}
