package com.example.first.repos;

import com.example.first.Models.ContactsDb;
import org.springframework.data.repository.CrudRepository;

public interface ContactsDbRepo extends CrudRepository<ContactsDb, Long> {
}
