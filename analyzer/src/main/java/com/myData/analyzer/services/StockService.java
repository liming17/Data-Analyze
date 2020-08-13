package com.myData.analyzer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myData.analyzer.repositories.StockRepository;
import com.myData.analyzer.entities.Stock;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getAllStocks(){
		return stockRepository.findAll();
	}
	
	public void saveStock(Stock stock) {
		stockRepository.saveAndFlush(stock);
	}
	
	public Stock findById(Long id) {
		Optional<Stock> stock = stockRepository.findById(id);
		return stock.orElse(null);
	}
	
	public Stock findBySymbol(String symbol){
		Optional<Stock> stock = stockRepository.findBySymbol(symbol);
		return stock.orElse(null);
	}
	
	public List<Stock> searchStock(String name){
		return (List<Stock>) stockRepository.searchStock(name);
	}
	
	public String deleteStock(Long id) {
		stockRepository.deleteById(id);
		return "stock deleted!";
	}
	

}
