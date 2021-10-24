package com.bits.cpad.auctionsystem.service.impl;

import com.bits.cpad.auctionsystem.model.BiddingDetails;
import com.bits.cpad.auctionsystem.repository.BiddingRepository;
import com.bits.cpad.auctionsystem.service.BiddingService;
import com.bits.cpad.auctionsystem.vo.BiddingDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BiddingServiceImpl implements BiddingService {
    @Autowired
    private BiddingRepository biddingRepository;

    @Override
    public BiddingDetailsVO getBiddingById(Long id) {
        Optional<BiddingDetails> biddingDetails = biddingRepository.findById(id);
        if (biddingDetails.isPresent()) {
            return constructResponse(biddingDetails.get());
        } else throw new NoSuchElementException("No record found for Id" + id);
    }

    @Override
    public List<BiddingDetailsVO> getBiddingByItemId(Long itemId) {
        Optional<List<BiddingDetails>> biddingDetails = biddingRepository.findByItemId(itemId);
        if (biddingDetails.isPresent()) {
            List<BiddingDetailsVO> biddingDetailsVOS = new ArrayList<>();
            biddingDetails.get().forEach(biddingDetail -> {
                BiddingDetailsVO biddingDetailsVO = constructResponse(biddingDetail);
                biddingDetailsVOS.add(biddingDetailsVO);
            });
            return biddingDetailsVOS;
        } else throw new NoSuchElementException("No record found for itemId" + itemId);
    }

    @Override
    public List<BiddingDetailsVO> getBiddingByUserId(Long userId) {
        Optional<List<BiddingDetails>> biddingDetails = biddingRepository.findByBidderId(userId);
        if (biddingDetails.isPresent()) {
            List<BiddingDetailsVO> biddingDetailsVOS = new ArrayList<>();
            biddingDetails.get().forEach(biddingDetail -> {
                BiddingDetailsVO biddingDetailsVO = constructResponse(biddingDetail);
                biddingDetailsVOS.add(biddingDetailsVO);
            });
            return biddingDetailsVOS;
        } else throw new NoSuchElementException("No record found for userId" + userId);
    }

    @Override
    public List<BiddingDetailsVO> getAllBidding() {
        Iterable<BiddingDetails> biddingDetails = biddingRepository.findAll();
        List<BiddingDetailsVO> biddingDetailsVOS = new ArrayList<>();
        biddingDetails.forEach(biddingDetail -> {
            BiddingDetailsVO biddingDetailsVO = constructResponse(biddingDetail);
            biddingDetailsVOS.add(biddingDetailsVO);
        });
        return biddingDetailsVOS;
    }

    @Override
    public Long addNewBid(BiddingDetailsVO biddingDetailsVO) {
        BiddingDetails biddingRequest = constructRequest(biddingDetailsVO,0L);
        BiddingDetails biddingDetails = biddingRepository.save(biddingRequest);
        return biddingDetails.getBiddingId();
    }

    @Override
    public Double calculateHighestBid(Long itemId) {
        List<BiddingDetailsVO> biddingDetailsVOS = getBiddingByItemId(itemId);
        BiddingDetailsVO biddingDetailsVO = biddingDetailsVOS.stream().max(Comparator.comparingDouble(BiddingDetailsVO::getBiddingPrice))
                .orElseThrow(NoSuchElementException::new);
        return biddingDetailsVO.getBiddingPrice();
    }

    private BiddingDetailsVO constructResponse(BiddingDetails biddingDetails) {
        return BiddingDetailsVO.builder()
                .biddingId(biddingDetails.getBiddingId())
                .itemId(biddingDetails.getItemId())
                .bidderId(biddingDetails.getBidderId())
                .bidDateTm(biddingDetails.getBidDateTm())
                .biddingPrice(biddingDetails.getBiddingPrice())
                .build();
    }

    private BiddingDetails constructRequest(BiddingDetailsVO biddingDetails, Long id) {
        BiddingDetails details = new BiddingDetails();
        if (id != 0L) {
            details.setBiddingId(id);
        }
        details.setItemId(biddingDetails.getItemId());
        details.setBidderId(biddingDetails.getBidderId());
        details.setBidDateTm(biddingDetails.getBidDateTm());
        details.setBiddingPrice(biddingDetails.getBiddingPrice());
        return details;
    }
}
