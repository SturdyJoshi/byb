package com.byb.bookYourBook.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
public class PurchasedHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long historyId;

    @ManyToOne
    private BookListing bookListing;

    @ManyToOne
    private User purchasedBy;

    @CreationTimestamp
    private Timestamp purchasedOn;

}
