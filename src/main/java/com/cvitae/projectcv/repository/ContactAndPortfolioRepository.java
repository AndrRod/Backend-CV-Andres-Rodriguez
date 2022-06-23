package com.cvitae.projectcv.repository;

import com.cvitae.projectcv.model.ContactAndPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactAndPortfolioRepository extends JpaRepository<ContactAndPortfolio, Long> {
}
