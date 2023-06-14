package com.media.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

	private String contentType;

	private String language;

	@Transient
	private List<String> browse;

	private String content;

}
