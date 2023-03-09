package co.admin.wh.common.mapper;

import java.util.List;

import co.admin.wh.common.vo.ReportVO;

public interface ReportMapper {
	
List<ReportVO> reportCompanion(ReportVO rvo);
	
	List<ReportVO> reportFree(ReportVO rvo);
	
	List<ReportVO> reportReview(ReportVO rvo);
	
	List<ReportVO> reportComment(ReportVO rvo);
	
	ReportVO reportCode(ReportVO rvo);
	
	int reportInsert(ReportVO rvo);
	
	int durationUpdate(ReportVO rvo);
}
