package com.myData.analyzer.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ConstraintMode;

import lombok.Data;

@Entity
@Data
public class StockList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String listName;
	
	@ManyToOne
	private User creator;
	
	@ManyToMany(fetch = FetchType.EAGER,
			cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Stock.class)
	@JoinTable(name = "STOCK_STOCKLIST",
    inverseJoinColumns = @JoinColumn(name = "STOCK_ID",
            nullable = false,
            updatable = false),
    joinColumns = @JoinColumn(name = "STOCKLIST_ID",
            nullable = false,
            updatable = false),
    foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
    inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Stock> stocks = new HashSet<>();
	
	public StockList(){
		
	}
	

}
