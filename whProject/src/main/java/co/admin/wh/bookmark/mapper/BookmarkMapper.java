package co.admin.wh.bookmark.mapper;

import java.util.List;

import co.admin.wh.bookmark.vo.BookmarkVO;

public interface BookmarkMapper {
		
	//전체조회
		List<BookmarkVO> bookList();
			
		
		//즐겨찾기 추가 여부
		public boolean bookCheck(BookmarkVO vo);
		
		//즐겨찾기 추가
		public int bookInsert(BookmarkVO vo);
		
		//즐겨찾기 취소
		public int bookDelete(BookmarkVO vo);
		
		
		
}
