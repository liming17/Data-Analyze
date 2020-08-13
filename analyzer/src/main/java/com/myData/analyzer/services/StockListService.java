package com.myData.analyzer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myData.analyzer.entities.StockList;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.repositories.StockListRepository;

@Service
public class StockListService {
	@Autowired
	private StockListRepository stockListRepository;
	
	public List<StockList> getAllStockList(){
		return stockListRepository.findAll();
	}
	
	public StockList findById(Long id) {
		Optional<StockList> list = stockListRepository.findById(id);
		return list.orElse(null);
	}
	
	public List<StockList> findByListName(String name){
		return stockListRepository.findByListName(name);
	}
	
	public List<StockList> findByCreator(User user){
		return stockListRepository.findByCreatorId(user.getId());
	}
	
	public String saveList(StockList list) {
		stockListRepository.saveAndFlush(list);
		return "List saved";
	}
	
	public String deleteList(Long id, String username) {
		StockList list = stockListRepository.findById(id).orElse(null);
		if(list == null) {
			return "list not found";
		}else if(!list.getCreator().getUsername().equals(username)) {
			return "You don't have the authority";
		}else {
			stockListRepository.deleteById(id);
			return "List deleted";
		}
	}

}
