package co.admin.wh.common.service;

import org.springframework.web.multipart.MultipartFile;

import co.admin.wh.common.vo.ImageVO;

public interface ImageService {
	
	String imageSelect(ImageVO ivo);

	String saveImage(MultipartFile[] file, String saveFolder);
	

	String foodImage(ImageVO ivo);

	String imgNotice(ImageVO ivo);

	
}
