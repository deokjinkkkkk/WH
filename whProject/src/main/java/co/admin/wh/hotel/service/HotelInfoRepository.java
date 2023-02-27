package co.admin.wh.hotel.service;

import org.springframework.data.jpa.repository.JpaRepository;

import co.admin.wh.hotel.vo.HotelVO;

public interface HotelInfoRepository extends JpaRepository<HotelVO, Integer> {

}
