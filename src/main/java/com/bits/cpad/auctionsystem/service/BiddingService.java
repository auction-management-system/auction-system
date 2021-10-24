package com.bits.cpad.auctionsystem.service;

import com.bits.cpad.auctionsystem.vo.BiddingDetailsVO;

import java.util.List;

public interface BiddingService {

    BiddingDetailsVO getBiddingById(Long id);

    List<BiddingDetailsVO> getBiddingByItemId(Long itemId);

    List<BiddingDetailsVO> getBiddingByUserId(Long itemId);

    List<BiddingDetailsVO> getAllBidding();

    Long addNewBid(BiddingDetailsVO biddingDetailsVO);

    Double calculateHighestBid(Long itemId);

}
