package co.admin.wh.notice.mapper;

import java.util.List;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;


public interface FoodMapper {

	List<FoodVO> getFoodList(FoodVO vo); //전체조회ㅇ
	
	int getCountTotal(FoodSearchVO svo);//총게시글ㅇ
	
	FoodVO detailSelect(FoodVO fvo); //상세보기페이지ㅇ
	
	int foodInsert(FoodVO vo); //게시글 등록ㅇ
	
	int imgInsert(ImageVO ivo);//이미지 넣기ㅇ
	
	int hitUpdate(int foodCode);//조회수ㅇ
	
	int foodDelete(FoodVO vo); //삭제ㅇ
	
	int foodUpdate(FoodVO vo); //수정ㅇ
	
}
