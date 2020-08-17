package com.myData.analyzer.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String symbol;
	
	private String name;
	
	private int price; //price < 0, then is not a quote in my portfolio
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Stock.class)
	@JoinTable(name = "STOCK_STOCKLIST",
    inverseJoinColumns = @JoinColumn(name = "STOCKLIST_ID",
            nullable = false,
            updatable = false),
    joinColumns = @JoinColumn(name = "STOCK_ID",
            nullable = false,
            updatable = false),
    foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
    inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	@JsonIgnore
	private Set<StockList> stockLists = new HashSet<>();
	
	public Stock() {
		
	}

}
