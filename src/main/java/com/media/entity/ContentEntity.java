package com.media.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "content")
public class ContentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contentid;

	private String title;

	private String contentPath;

	private String endPoint;

	private Date publishedDate;

	private Date lastModifiedDate;

	private String publishedBy;

	private Integer status;

}
