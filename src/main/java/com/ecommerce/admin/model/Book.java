package com.ecommerce.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		
		private Integer id;
		@NotBlank(message = "filed Must not empty")
		private String title;
		private String author;
		private String publisher;
		private String publicationDate;
		private String language;
		private String category;
		@Min(value = 1,message = "must have minimum value 1")
		private int numberOfPages;
		private String format;
		private int isbn;
		private double shippingWeight;
		private double listPrice;
		private double ourPrice;
		private boolean active=true;
		
		@Column(columnDefinition="text")
		private String description;
		private int inStockNumber;
		
		@Transient
		private MultipartFile bookImage;
		
	

	}

