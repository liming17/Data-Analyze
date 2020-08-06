package com.myData.analyzer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String text;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User creator;
    
    public Comment(String text, Post post, User creator) {
        this.text = text;
        this.post = post;
        this.creator = creator;
    }

}
