package com.bits.cpad.auctionsystem.repository;

import com.bits.cpad.auctionsystem.model.BiddingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BiddingRepository extends CrudRepository<BiddingDetails, Long> {

    Optional<List<BiddingDetails>> findByItemId(@Param("itemId") Long itemId);

    Optional<List<BiddingDetails>> findByBidderId(@Param("bidderId") Long bidderId);

}
