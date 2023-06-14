package com.media.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaModel {

	private String title;
    private String contentType;
    private String language;
    private List<MultipartFile> browse;
    private String content;


}
