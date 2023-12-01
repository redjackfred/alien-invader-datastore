package com.example.gameserver;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordRepository extends DatastoreRepository<Record, Long> {
    Page<Record> findAllByUserId(String userId, Pageable pageable);

}

