package com.codecool.shitwishorder.repository;

import com.codecool.shitwishorder.model.ShitWishOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShitWishOrderRepository extends JpaRepository<ShitWishOrder, Long> {
}
