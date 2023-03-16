package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.mapper.HotelMapper;
import co.admin.wh.hotel.vo.CancelVO;
import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {
	
	@Autowired
	private HotelMapper hotelMapper;

	@Override
	public void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException {
		hotelMapper.insertHotelInfo(hotelVO);
	}
	
	@Override
	public List<HotelVO> hotelList(HotelSearchVO vo) {
		return hotelMapper.hotelList(vo);
	}

	@Override
	public List<HotelVO> CrawlingList(HotelSearchVO vo) {
		return hotelMapper.CrawlingList(vo);
	}

	@Override
	public HotelVO detailSelect(HotelVO vo) {
		return hotelMapper.detailSelect(vo);
	}

	@Override
	public void insertReservInfo(ReservationVO vo) {
		hotelMapper.insertReservInfo(vo);
	}

	@Override
	public List<ReservationVO> readReservInfo(String sessionId) {
		return hotelMapper.readReservInfo(sessionId);
	}

	@Override
	public void hotelCancel(ReservationVO vo) {
		hotelMapper.hotelCancel(vo);
	}

	@Override
	public void ReservUpdate(ReservationVO vo) {
		hotelMapper.ReservUpdate(vo);
	}

	@Override
	public List<ReservationVO> readCancelReservInfo(String sessionId) {
		return hotelMapper.readCancelReservInfo(sessionId);
	}

	@Override
	public int getCountTotal(HotelSearchVO vo) {
		return hotelMapper.getCountTotal(vo);
	}

	@Override
	public List<HotelVO> hotelSearchList(HotelSearchVO vo) {
		return hotelMapper.hotelSearchList(vo);
	}

	@Override
	public List<Map<String, Object>>autocomplete(Map<String, Object> paramMap) throws Exception{
		return hotelMapper.autocomplete(paramMap);
	}

	@Override
	public List<HotelVO> priceList(HotelSearchVO vo) {
		return hotelMapper.priceList(vo);
	}

	@Override
	public List<HotelVO> priceListDesc(HotelSearchVO vo) {
		return hotelMapper.priceListDesc(vo);
	}

	@Override
	public List<HotelVO> starRatingList(HotelSearchVO vo) {
		return hotelMapper.starRatingList(vo);
	}

	@Override
	public List<HotelVO> goodRatingList(HotelSearchVO vo) {
		return hotelMapper.goodRatingList(vo);
	}

	@Override
	public List<HotelVO> hotelNameSearchList(HotelSearchVO vo) {
		return hotelMapper.hotelNameSearchList(vo);
	}

	@Override
	public List<ReservationVO> readFinReservInfo(String sessionId) {
		return hotelMapper.readFinReservInfo(sessionId);
	}

//	@Override
//	public void minusRoomCount(int hotelId) {
//		hotelMapper.minusRoomCount(hotelId);
//	}

	@Override
	public List<CancelVO> adminReservList() {
		return hotelMapper.adminReservList();
	}

	@Override
	public List<HotelVO> mainList(HotelSearchVO vo) {
		return hotelMapper.mainList(vo);
	}

	@Override
	public List<CancelVO> adminSearch(String option, String content) {
		return hotelMapper.adminSearch(option, content);
	}

	@Override
	public void insertCancelInfo(ReservationVO vo) {
		hotelMapper.insertCancelInfo(vo);
	}

	@Override
	public void updateCancelInfo(CancelVO vo) {
		hotelMapper.updateCancelInfo(vo);
		
	}

}
