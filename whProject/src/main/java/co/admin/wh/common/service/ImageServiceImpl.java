package co.admin.wh.common.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.admin.wh.common.mapper.ImageMapper;
import co.admin.wh.common.vo.ImageVO;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageMapper imageMapper;

	@Override
	public String imageSelect(ImageVO ivo) {
		// TODO Auto-generated method stub
		return imageMapper.imageSelect(ivo);
	}

	public String saveImage(MultipartFile[] file, String saveFolder) {
		String fileName = null;
		for (MultipartFile file1 : file) {
			try {
				if (!file1.isEmpty()) {
					fileName = UUID.randomUUID().toString() + file1.getOriginalFilename();
					File uploadFile = new File(saveFolder, fileName);
					file1.transferTo(uploadFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

	@Override

	public String foodImage(ImageVO ivo) {
		return imageMapper.foodImage(ivo);
	}


	public String imgNotice(ImageVO ivo) {
		return imageMapper.imgNotice(ivo);
	}
	
	
	


}
