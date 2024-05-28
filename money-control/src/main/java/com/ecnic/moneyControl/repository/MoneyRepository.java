package com.ecnic.moneyControl.repository;

import com.ecnic.moneyControl.models.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money,Long> {
}
