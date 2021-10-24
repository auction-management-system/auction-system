package com.bits.cpad.auctionsystem.conroller;

import com.bits.cpad.auctionsystem.service.BiddingService;
import com.bits.cpad.auctionsystem.vo.BiddingDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @GetMapping("/bid/{biddingId}")
    public BiddingDetailsVO getBiddingByBiddingId(@PathVariable("biddingId") @NotNull Long biddingId) {
        if (biddingId != 0) {
            return biddingService.getBiddingById(biddingId);
        } else throw new IllegalArgumentException("BiddingId in path is zero");
    }

    @GetMapping("/bid")
    public List<BiddingDetailsVO> getBiddingByUserId(@RequestParam("userId") @NotNull Long userId) {
        if (userId != 0) {
            return biddingService.getBiddingByUserId(userId);
        } else throw new IllegalArgumentException("UserId in path is zero");
    }

    @GetMapping("/bids")
    public List<BiddingDetailsVO> getAllBids() {
        List<BiddingDetailsVO> detailsVOList = biddingService.getAllBidding();
        if (detailsVOList.isEmpty()) {
            return new ArrayList<>();
        }
        return detailsVOList;
    }

    @PostMapping("/bid")
    public Long addBiddingDetails(@RequestBody @NotNull BiddingDetailsVO biddingDetailsVO) {
        return biddingService.addNewBid(biddingDetailsVO);
    }

    @GetMapping("/bid/item/{itemId}")
    public List<BiddingDetailsVO> getBiddingByItemId(@PathVariable("itemId") @NotNull Long itemId) {
        if (itemId != 0) {
            return biddingService.getBiddingByItemId(itemId);
        } else throw new IllegalArgumentException("ItemId in path is zero");
    }

    @GetMapping("/max/bid/{itemId}")
    public Double calculateHighestBidByItemId(@PathVariable("itemId") @NotNull Long itemId) {
        if (itemId != 0) {
            return biddingService.calculateHighestBid(itemId);
        } else throw new IllegalArgumentException("ItemId in path is zero");
    }
}
