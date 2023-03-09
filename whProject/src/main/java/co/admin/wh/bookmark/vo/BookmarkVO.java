package co.admin.wh.bookmark.vo;

import lombok.Data;

@Data
public class BookmarkVO {
	String id; //회원 아이디
	String bookFlag; //좋아요 구분(여행지, 호텔...)
	int bookCode; // 북마크 번호
	int bookNcode; //게시글 번호
}
