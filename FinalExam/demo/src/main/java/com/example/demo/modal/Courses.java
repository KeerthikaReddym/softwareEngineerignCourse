package com.example.demo.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String semester;
	private String course;
	private String instructor;
	private String location;
	
	
}
