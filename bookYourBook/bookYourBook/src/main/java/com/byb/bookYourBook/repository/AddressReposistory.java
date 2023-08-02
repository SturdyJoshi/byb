package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressReposistory  extends JpaRepository<Address,Long> {
}
