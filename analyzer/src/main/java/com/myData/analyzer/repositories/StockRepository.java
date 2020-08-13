package com.myData.analyzer.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myData.analyzer.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long>{
   Optional<Stock> findBySymbol(String symbol);
   
   @Query(value="SELECT * FROM STOCK S WHERE S.SYMBOL LIKE CONCAT('%',:searchText,'%')"+
			 "OR S.NAME LIKE CONCAT('%',:searchText,'%')",nativeQuery = true)
			 Collection<Stock> searchStock(@Param("searchText") String searchText);
}
