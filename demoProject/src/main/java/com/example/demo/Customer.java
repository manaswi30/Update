package com.example.demo;





import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "dummy")

public class Customer {
	@Id
	@Column(name = "localRef", unique = true, columnDefinition = "VARCHAR(64)")
	private String loc_ref;

	private String name;
	@Transient
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date previousDate;


	@Column(name = "Currentdate")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date currentDate;

	public Customer() {
		super();
	}

	public Customer(String loc_ref, String name, Date previousDate, Date currentDate) {
		super();
		this.loc_ref = loc_ref;
		this.name = name;
		this.previousDate = previousDate;
		this.currentDate = currentDate;
	}

	@Override
	public String toString() {
		return "Customer [loc_ref=" + loc_ref + ", name=" + name + ", previousDate=" + previousDate + ", currentDate="
				+ currentDate + "]";
	}

	public String getLoc_ref() {
		return loc_ref;
	}

	public void setLoc_ref(String loc_ref) {
		this.loc_ref = loc_ref;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPreviousDate() {
		return previousDate;
	}

	public void setPreviousDate(Date oldDate) {
		this.previousDate = oldDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}