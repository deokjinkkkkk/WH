package co.admin.wh.common.service;

import java.util.List;

import co.admin.wh.common.vo.ReportVO;

public interface ReportService {
	
	List<ReportVO> reportCompanion(ReportVO rvo);
	
	List<ReportVO> reportFree(ReportVO rvo);
	
	List<ReportVO> reportReview(ReportVO rvo);
	
	List<ReportVO> reportComment(ReportVO rvo);
	
	int reportInsert(ReportVO rvo);
}
