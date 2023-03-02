package co.admin.wh.hotel.service;

import java.util.List;

import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.RoomVO;

public interface Crawler {

	public List<HotelVO> hotelCrawling(String url);
	public List<RoomVO> roomCrawling(String url);
}
