package com.bits.cpad.auctionsystem.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;


@ToString
@Data
@Builder
public class BiddingDetailsVO {

    private Long biddingId;
    private Long bidderId;
    private Long itemId;
    private Double biddingPrice;
    private Date bidDateTm;
}
