package co.admin.wh.hotel.service;

import java.util.List;

import co.admin.wh.hotel.vo.HotelVO;

public interface Crawler {

	public List<HotelVO> crawling(String url);
}
