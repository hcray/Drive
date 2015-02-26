package com.daoliuhe.drive.bean;

/**
 * @author 21829
 * 路线bean
 */
public class LineBean {
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 路线名称
	 */
	private String lineName;

	/**
	 * 前方请变更车道 纬度
	 */
	private Double changeLanesLat;
	
	/**
	 * 前方请变更车道 经度
	 */
	private Double changeLanesLng;
	/**
	 * 前方请变更车道 方向
	 */
	private Float changeLanesBr;

	/**
	 * 前方路口直行 纬度
	 */
	private Double aheadDirectLineLat;
	
	/**
	 * 前方路口直行 经度
	 */
	private Double aheadDirectLineLng;
	/**
	 * 前方路口直行 方向
	 */
	private Float aheadDirectLineBr;
	
	/**
	 * 前方路口左转 纬度
	 */
	private Double turnLeftLat;
	
	/**
	 * 前方路口左转 经度
	 */
	private Double turnLeftLng;
	/**
	 * 前方路口左转 方向
	 */
	private Float turnLeftBr;
	
	/**
	 * 前方路口右转 纬度
	 */
	private Double turnRightLat; // 纬度
	
	/**
	 * 前方路口右转 经度
	 */
	private Double turnRightLng; // 经度
	/**
	 * 前方路口右转 方向
	 */
	private Float turnRightBr; // 方向

	/**
	 * 前方人形横道 纬度 
	 */
	private Double sidewalkLat;
	
	/**
	 * 前方人形横道 经度
	 */
	private Double sidewalkLng;
	/**
	 * 前方人形横道 方向
	 */
	private Float sidewalkBr;
	
	/**
	 * 通过学校区域 纬度
	 */
	private Double passSchoolLat;
	
	/**
	 * 通过学校区域 经度
	 */
	private Double passSchoolLng;
	/**
	 * 通过学校区域 方向
	 */
	private Float passSchoolBr;

	/**
	 * 通过公共汽车站 纬度
	 */
	private Double passBusStationLat;
	
	/**
	 * 通过公共汽车站 经度
	 */
	private Double passBusStationLng;
	/**
	 * 通过公共汽车站 方向
	 */
	private Float passBusStationBr;
	
	/**
	 * 通过人行横道 纬度
	 */
	private Double passSidewalkLat;
	
	/**
	 * 通过人行横道 经度
	 */
	private Double passSidewalkLng;
	/**
	 * 通过人行横道 方向
	 */
	private Float passSidewalkBr;

	/**
	 * 直线行驶 纬度
	 */
	private Double directLineLat;
	
	/**
	 * 直线行驶 经度
	 */
	private Double directLineLng;
	/**
	 * 直线行驶 方向
	 */
	private Float directLineBr;
	
	/**
	 * 结束直线行驶 纬度
	 */
	private Double endDirectLineLat;
	
	/**
	 * 结束直线行驶 经度
	 */
	private Double endDirectLineLng;
	/**
	 * 结束直线行驶 方向
	 */
	private Float endDirectLineBr;

	/**
	 * 超车 纬度
	 */
	private Double overtakeLat;
	
	/**
	 * 超车 经度
	 */
	private Double overtakeLng;
	/**
	 * 超车 方向
	 */
	private Float overtakeBr;
	
	/**
	 * 前方掉头 纬度
	 */
	private Double turnLat;
	/**
	 * 前方掉头 经度
	 */
	private Double turnLng;
	/**
	 * 前方掉头 方向
	 */
	private Float turnBr;

	/**
	 * 请靠边停车 纬度
	 */
	private Double pullOverLat;
	
	/**
	 * 请靠边停车 经度
	 */
	private Double pullOverLng;
	/**
	 * 请靠边停车 方向
	 */
	private Float pullOverBr;
	/**
	 * 会车 纬度
	 */
	private Double passingLat;
	
	/**
	 * 会车 经度
	 */
	private Double passingLng;
	/**
	 * 会车 方向
	 */
	private Float passingBr;
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLineName() {
		return lineName;
	}



	public void setLineName(String lineName) {
		this.lineName = lineName;
	}



	public Double getChangeLanesLat() {
		return changeLanesLat;
	}



	public void setChangeLanesLat(Double changeLanesLat) {
		this.changeLanesLat = changeLanesLat;
	}



	public Double getChangeLanesLng() {
		return changeLanesLng;
	}



	public void setChangeLanesLng(Double changeLanesLng) {
		this.changeLanesLng = changeLanesLng;
	}



	public Double getAheadDirectLineLat() {
		return aheadDirectLineLat;
	}



	public void setAheadDirectLineLat(Double aheadDirectLineLat) {
		this.aheadDirectLineLat = aheadDirectLineLat;
	}



	public Double getAheadDirectLineLng() {
		return aheadDirectLineLng;
	}



	public void setAheadDirectLineLng(Double aheadDirectLineLng) {
		this.aheadDirectLineLng = aheadDirectLineLng;
	}



	public Double getTurnLeftLat() {
		return turnLeftLat;
	}



	public void setTurnLeftLat(Double turnLeftLat) {
		this.turnLeftLat = turnLeftLat;
	}



	public Double getTurnLeftLng() {
		return turnLeftLng;
	}



	public void setTurnLeftLng(Double turnLeftLng) {
		this.turnLeftLng = turnLeftLng;
	}



	public Double getTurnRightLat() {
		return turnRightLat;
	}



	public void setTurnRightLat(Double turnRightLat) {
		this.turnRightLat = turnRightLat;
	}



	public Double getTurnRightLng() {
		return turnRightLng;
	}



	public void setTurnRightLng(Double turnRightLng) {
		this.turnRightLng = turnRightLng;
	}



	public Double getSidewalkLat() {
		return sidewalkLat;
	}



	public void setSidewalkLat(Double sidewalkLat) {
		this.sidewalkLat = sidewalkLat;
	}



	public Double getSidewalkLng() {
		return sidewalkLng;
	}



	public void setSidewalkLng(Double sidewalkLng) {
		this.sidewalkLng = sidewalkLng;
	}



	public Double getPassSchoolLat() {
		return passSchoolLat;
	}



	public void setPassSchoolLat(Double passSchoolLat) {
		this.passSchoolLat = passSchoolLat;
	}



	public Double getPassSchoolLng() {
		return passSchoolLng;
	}



	public void setPassSchoolLng(Double passSchoolLng) {
		this.passSchoolLng = passSchoolLng;
	}



	public Double getPassBusStationLat() {
		return passBusStationLat;
	}



	public void setPassBusStationLat(Double passBusStationLat) {
		this.passBusStationLat = passBusStationLat;
	}



	public Double getPassBusStationLng() {
		return passBusStationLng;
	}



	public void setPassBusStationLng(Double passBusStationLng) {
		this.passBusStationLng = passBusStationLng;
	}



	public Double getPassSidewalkLat() {
		return passSidewalkLat;
	}



	public void setPassSidewalkLat(Double passSidewalkLat) {
		this.passSidewalkLat = passSidewalkLat;
	}



	public Double getPassSidewalkLng() {
		return passSidewalkLng;
	}



	public void setPassSidewalkLng(Double passSidewalkLng) {
		this.passSidewalkLng = passSidewalkLng;
	}



	public Double getDirectLineLat() {
		return directLineLat;
	}



	public void setDirectLineLat(Double directLineLat) {
		this.directLineLat = directLineLat;
	}



	public Double getDirectLineLng() {
		return directLineLng;
	}



	public void setDirectLineLng(Double directLineLng) {
		this.directLineLng = directLineLng;
	}



	public Double getEndDirectLineLat() {
		return endDirectLineLat;
	}



	public void setEndDirectLineLat(Double endDirectLineLat) {
		this.endDirectLineLat = endDirectLineLat;
	}



	public Double getEndDirectLineLng() {
		return endDirectLineLng;
	}



	public void setEndDirectLineLng(Double endDirectLineLng) {
		this.endDirectLineLng = endDirectLineLng;
	}



	public Double getOvertakeLat() {
		return overtakeLat;
	}



	public void setOvertakeLat(Double overtakeLat) {
		this.overtakeLat = overtakeLat;
	}



	public Double getOvertakeLng() {
		return overtakeLng;
	}



	public void setOvertakeLng(Double overtakeLng) {
		this.overtakeLng = overtakeLng;
	}



	public Double getTurnLat() {
		return turnLat;
	}



	public void setTurnLat(Double turnLat) {
		this.turnLat = turnLat;
	}



	public Double getTurnLng() {
		return turnLng;
	}



	public void setTurnLng(Double turnLng) {
		this.turnLng = turnLng;
	}



	public Double getPullOverLat() {
		return pullOverLat;
	}



	public void setPullOverLat(Double pullOverLat) {
		this.pullOverLat = pullOverLat;
	}



	public Double getPullOverLng() {
		return pullOverLng;
	}



	public void setPullOverLng(Double pullOverLng) {
		this.pullOverLng = pullOverLng;
	}



	public Double getPassingLat() {
		return passingLat;
	}



	public void setPassingLat(Double passingLat) {
		this.passingLat = passingLat;
	}



	public Double getPassingLng() {
		return passingLng;
	}



	public void setPassingLng(Double passingLng) {
		this.passingLng = passingLng;
	}

	
	
	public Float getChangeLanesBr() {
		return changeLanesBr;
	}



	public void setChangeLanesBr(Float changeLanesBr) {
		this.changeLanesBr = changeLanesBr;
	}



	public Float getAheadDirectLineBr() {
		return aheadDirectLineBr;
	}



	public void setAheadDirectLineBr(Float aheadDirectLineBr) {
		this.aheadDirectLineBr = aheadDirectLineBr;
	}



	public Float getTurnLeftBr() {
		return turnLeftBr;
	}



	public void setTurnLeftBr(Float turnLeftBr) {
		this.turnLeftBr = turnLeftBr;
	}



	public Float getTurnRightBr() {
		return turnRightBr;
	}



	public void setTurnRightBr(Float turnRightBr) {
		this.turnRightBr = turnRightBr;
	}



	public Float getSidewalkBr() {
		return sidewalkBr;
	}



	public void setSidewalkBr(Float sidewalkBr) {
		this.sidewalkBr = sidewalkBr;
	}



	public Float getPassSchoolBr() {
		return passSchoolBr;
	}



	public void setPassSchoolBr(Float passSchoolBr) {
		this.passSchoolBr = passSchoolBr;
	}



	public Float getPassBusStationBr() {
		return passBusStationBr;
	}



	public void setPassBusStationBr(Float passBusStationBr) {
		this.passBusStationBr = passBusStationBr;
	}



	public Float getPassSidewalkBr() {
		return passSidewalkBr;
	}



	public void setPassSidewalkBr(Float passSidewalkBr) {
		this.passSidewalkBr = passSidewalkBr;
	}



	public Float getDirectLineBr() {
		return directLineBr;
	}



	public void setDirectLineBr(Float directLineBr) {
		this.directLineBr = directLineBr;
	}



	public Float getEndDirectLineBr() {
		return endDirectLineBr;
	}



	public void setEndDirectLineBr(Float endDirectLineBr) {
		this.endDirectLineBr = endDirectLineBr;
	}



	public Float getOvertakeBr() {
		return overtakeBr;
	}



	public void setOvertakeBr(Float overtakeBr) {
		this.overtakeBr = overtakeBr;
	}



	public Float getTurnBr() {
		return turnBr;
	}



	public void setTurnBr(Float turnBr) {
		this.turnBr = turnBr;
	}



	public Float getPullOverBr() {
		return pullOverBr;
	}



	public void setPullOverBr(Float pullOverBr) {
		this.pullOverBr = pullOverBr;
	}



	public Float getPassingBr() {
		return passingBr;
	}



	public void setPassingBr(Float passingBr) {
		this.passingBr = passingBr;
	}



	@Override
	public String toString() {
		StringBuffer history = new StringBuffer();
		history.append(" id:").append(id)
				.append(" lineName:").append(lineName)
				.append(" changeLanesLat:").append(changeLanesLat)
				.append(" changeLanesLng:").append(changeLanesLng)
				.append(" changeLanesBr:").append(changeLanesBr)
				.append(" aheadDirectLineLat:").append(aheadDirectLineLat)
				.append(" aheadDirectLineLng:").append(aheadDirectLineLng)
				.append(" aheadDirectLineBr:").append(aheadDirectLineBr)
				.append(" turnLeftLat:").append(turnLeftLat)
				.append(" turnLeftLng:").append(turnLeftLng)
				.append(" turnLeftBr:").append(turnLeftBr)
				.append(" turnRightLat:").append(turnRightLat)
				.append(" turnRightLng:").append(turnRightLng)
				.append(" turnRightBr:").append(turnRightBr)
				.append(" sidewalkLat:").append(sidewalkLat)
				.append(" sidewalkLng:").append(sidewalkLng)
				.append(" sidewalkBr:").append(sidewalkBr)
				
				.append(" passSchoolLat:").append(passSchoolLat)
				.append(" passSchoolLng:").append(passSchoolLng)
				.append(" passSchoolBr:").append(passSchoolBr)
				.append(" passBusStationLat:").append(passBusStationLat)
				.append(" passBusStationLng:").append(passBusStationLng)
				.append(" passBusStationBr:").append(passBusStationBr)
				.append(" passSidewalkLat:").append(passSidewalkLat)
				.append(" passSidewalkLng:").append(passSidewalkLng)
				.append(" passSidewalkBr:").append(passSidewalkBr)
				.append(" directLineLat:").append(directLineLat)
				.append(" directLineLng:").append(directLineLng)
				.append(" directLineBr:").append(directLineBr)
				.append(" endDirectLineLat").append(endDirectLineLat)
				.append(" endDirectLineLng").append(endDirectLineLng)
				.append(" endDirectLineBr").append(endDirectLineBr)
				
				.append(" overtakeLat:").append(overtakeLat)
				.append(" overtakeLng:").append(overtakeLng)
				.append(" overtakeBr:").append(overtakeBr)
				.append(" turnLat:").append(turnLat)
				.append(" turnLng:").append(turnLng)
				.append(" turnBr:").append(turnBr)
				.append(" pullOverLat:").append(pullOverLat)
				.append(" pullOverLng:").append(pullOverLng)
				.append(" pullOverBr:").append(pullOverBr)
				.append(" passingLat:").append(passingLat)
				.append(" passingLng:").append(passingLng)	
				.append(" passingBr:").append(passingBr);		
		
		return history.toString();
	}
	
	
}
