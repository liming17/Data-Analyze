package com.myData.analyzer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myData.analyzer.entities.StockList;

@Repository
public interface StockListRepository extends JpaRepository<StockList,Long> {
	List<StockList> findByListName(String listName);
	
	List<StockList> findByCreatorId(Long id);

}
