package com.myData.analyzer.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myData.analyzer.services.StockListService;
import com.myData.analyzer.services.StockService;
import com.myData.analyzer.services.UserService;
import com.myData.analyzer.entities.Post;
import com.myData.analyzer.entities.Stock;
import com.myData.analyzer.entities.StockList;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.pojos.StockListPojo;

@RestController
public class StockController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StockListService stockListService;
	
	@GetMapping(value="/stock/viewAllStocks")
	public List<Stock> getAllStocks(){
		return stockService.getAllStocks();
	}
	
	@GetMapping(value="/stock/findStock/{id}")
	public Stock findByid(@PathVariable Long id) {
		return stockService.findById(id);
	}
	
	@GetMapping(value="/stock/findStockSymbol/{symbol}")
	public Stock findBySymbol(@PathVariable String symbol) {
		return stockService.findBySymbol(symbol);
	}
	
	@GetMapping(value="/stock/searchStock/{text}")
	public List<Stock> searchStock(@PathVariable String text){
		return stockService.searchStock(text);
	}
	
	@PostMapping(value = "/stock/saveStock")
	public String saveStock(@RequestBody Stock stock) {
		stockService.saveStock(stock);
		return "stock saved";
	}
	
	@DeleteMapping(value = "/stock/{id}")
	public String deleteStock(@PathVariable Long id) {
		stockService.deleteStock(id);
		return "stock deleted";
	}
	
	@GetMapping(value="/stockList/viewMyLists")
	public List<StockList> viewMyLists(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return stockListService.findByCreator(userService.getUser(username));
	}
	
	@PostMapping(value = "/stockList/saveStockList")
	public String saveList(@RequestBody StockListPojo list) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.getUser(userDetails.getUsername());
		StockList myList = new StockList();
        
		Long id = list.getId();
		if(id != null) {
			myList.setId(id);
		}
		myList.setListName(list.getListName());
		myList.setCreator(creator);
		
		for(int i=0;i<list.getStockId().size();i++) {
			Stock stock = stockService.findById(list.getStockId().get(i));
			if(stock == null)
				continue;
			myList.getStocks().add(stock);
		}
		stockListService.saveList(myList);
		return "list saved";
		
	}
	
	@DeleteMapping(value = "/stockList/{id}")
	public String deleteThisList(@PathVariable Long id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username  = userDetails.getUsername();
	    return stockListService.deleteList(id,username);
	}
	


}
