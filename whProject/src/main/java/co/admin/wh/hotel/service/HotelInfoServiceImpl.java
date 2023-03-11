package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.mapper.HotelMapper;
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
		// TODO Auto-generated method stub
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
}
