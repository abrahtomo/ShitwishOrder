package com.codecool.shitwishorder.repository;

import com.codecool.shitwishorder.model.ShitWishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public interface ShitWishOrderRepository extends JpaRepository<ShitWishOrder, Long> {


}
