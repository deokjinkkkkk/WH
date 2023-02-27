package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.notice.vo.FoodImgVO;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;


public interface FoodMapper {

	List<FoodVO> getFoodList(FoodVO vo); //페이
	
	int getCountTotal(FoodSearchVO svo);//총게시글
	
	FoodVO detailSelect(FoodVO fvo); //상세보기페이지
	
	FoodVO getFoodSelect(FoodVO vo); //글쓰기
	
	int foodInsert(FoodVO vo); //게시글 등록
	
	int imgInsert(FoodImgVO ivo);
	
	int hitUpdate(int foodCode);
}
