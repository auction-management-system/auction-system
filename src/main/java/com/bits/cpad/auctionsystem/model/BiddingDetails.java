package com.bits.cpad.auctionsystem.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(schema = "auction", name = "bidding_details")
public class BiddingDetails {

    @Id
    @GeneratedValue
    @Column(name = "biddingId")
    private Long biddingId;
    @Column(name = "bidderId")
    private Long bidderId;
    @Column(name = "itemId")
    private Long itemId;
    @Column(name = "biddingPrice")
    private Double biddingPrice;
    @Column(name = "bidDateTm")
    private Date bidDateTm;
}
