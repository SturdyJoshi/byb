package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.entity.PurchasedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<PurchasedHistory, Long> {
}
