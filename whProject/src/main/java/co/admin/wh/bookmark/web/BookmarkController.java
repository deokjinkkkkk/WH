package co.admin.wh.bookmark.web;



import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.service.BookmarkService;
import co.admin.wh.bookmark.vo.BookmarkVO;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class BookmarkController {
   @Autowired
   private BookmarkMapper bMapper;
   @Autowired
   private BookmarkService bService;

   // bookmark 전체 페이지 ->마이페이지에서 확인 가능
   @RequestMapping("/bookmark")
   public String bookmark(Model model, Principal principal) {
       String id = principal.getName();
       
       model.addAttribute("bookHotel", bMapper.hotelList(id));
       
       return "bookmark/bookmark";
   }
   
// bookmark 전체 페이지 ->마이페이지에서 확인 가능
   @RequestMapping("/bookmarkTr")
   public String bookmarktr(Model model, Principal principal) {
       String id = principal.getName();
       
       model.addAttribute("bookTrip", bMapper.tripList(id));
       
       return "bookmark/bookmarkTr";
   }
   
   //즐겨찾기 했는지 안했는지 확인
   @RequestMapping("/bookmarkCheck/{bookNcode}/{id}")
   @ResponseBody
   public boolean bookmarkCheck(@PathVariable("bookNcode") int bookNcode, BookmarkVO vo,
         @PathVariable("id")String id,MemberVO mvo , Principal principal) {
      
      
       vo.setId(id);
       vo.setBookNcode(bookNcode);
       vo.setBookFlag("HT"); // 호텔/여행지 여부에 따라 bookFlag 값 지정
       vo.setBookFlag("TR");
       return bService.bookmarkCheck(vo);
      
   
   }

   //hotel - 즐겨찾기 추가
   @PostMapping("/insertBookHotel/{bookNcode}")
   @ResponseBody
   public BookmarkVO insertBookHotel(BookmarkVO vo, @PathVariable int bookNcode, Principal principal) {
       vo.setId(principal.getName());
       vo.setBookNcode(bookNcode);
       vo.setBookFlag("HT");
       bMapper.insertBookHotel(vo);
       return vo;
   }

   //trip - 즐겨찾기 추가
   @PostMapping("/insertBookTrip/{bookNcode}")   
   @ResponseBody  //리턴하는 bookmarkVO 객체가 직접 포함되어있음..(post에서는 붙여줘야함)
   public BookmarkVO insertBookTrip(BookmarkVO vo, @PathVariable int bookNcode, Principal principal) {
       vo.setId(principal.getName());
       vo.setBookNcode(bookNcode);
       vo.setBookFlag("TR");
       bMapper.insertBookTrip(vo);
       return vo;
   }

   //즐겨찾기 삭제
   @PostMapping("/bookDel/{bookCode}")
   @ResponseBody
   public int bookDel(BookmarkVO vo, @PathVariable int bookCode, Principal principal) {
       vo.setBookCode(bookCode);
       vo.setId(principal.getName());
       return bMapper.bookDel(vo);
   }
   }
   
 
