package co.admin.wh.bookmark.service;

import java.util.List;
import java.util.Map;

import co.admin.wh.bookmark.vo.BookmarkVO;
import co.admin.wh.common.vo.GreatVO;

public interface BookmarkService {
	
		//호텔 - 즐겨찾기 추가
		public int insertBookHotel(BookmarkVO vo);
		
		//호텔 - 즐겨찾기 리스트 출력
		public List<Map<String,Object>>hotelList(String id);
			
		//호텔 - 즐겨찾기 게시글 상세 내용
		public Map<String,Object> selectBookHotel(BookmarkVO vo);
			
		//-----------------------------------------------------//
		
		//여행지 - 즐겨찾기 추가
		public int insertBookTrip(BookmarkVO vo);
		
		//여행지 - 즐겨찾기 리스트 출력
		public List<Map<String,Object>>tripList(String id);
			
		//여행지 - 즐겨찾기 게시글 상세 내용
		public Map<String,Object> selectBookTrip(BookmarkVO vo);
		
		//------------------------------------------------//
		
		//삭제
		public int bookDel(BookmarkVO vo);
		
		//북마크 단건조회 만들기!! xml에도 추가!
		public int selectBook(BookmarkVO vo);
		
		//북마크 추가여부 확인
		public boolean bookmarkCheck(BookmarkVO vo);
	
}
