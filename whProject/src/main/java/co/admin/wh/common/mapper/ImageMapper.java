package co.admin.wh.common.mapper;



import co.admin.wh.common.vo.ImageVO;

public interface ImageMapper {
	
	String imageSelect(ImageVO ivo);

	
	String foodImage(ImageVO ivo);

	String imgNotice(ImageVO ivo);
	
	int imageDelete(ImageVO ivo);

}
