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
	 * 前方路口直行 纬度
	 */
	private Double aheadDirectLineLat;
	
	/**
	 * 前方路口直行 经度
	 */
	private Double aheadDirectLineLng;
	
	/**
	 * 前方路口左转 纬度
	 */
	private Double turnLeftLat;
	
	/**
	 * 前方路口左转 经度
	 */
	private Double turnLeftLng;
	
	/**
	 * 前方路口右转 纬度
	 */
	private Double turnRightLat; // 纬度
	
	/**
	 * 前方路口右转 经度
	 */
	private Double turnRightLng; // 经度

	/**
	 * 前方人形横道 纬度 
	 */
	private Double sidewalkLat;
	
	/**
	 * 前方人形横道 经度
	 */
	private Double sidewalkLng;
	
	/**
	 * 通过学校区域 纬度
	 */
	private Double passSchoolLat;
	
	/**
	 * 通过学校区域 经度
	 */
	private Double passSchoolLng;

	/**
	 * 通过公共汽车站 纬度
	 */
	private Double passBusStationLat;
	
	/**
	 * 通过公共汽车站 经度
	 */
	private Double passBusStationLng;
	
	/**
	 * 通过人行横道 纬度
	 */
	private Double passSidewalkLat;
	
	/**
	 * 通过人行横道 经度
	 */
	private Double passSidewalkLng;

	/**
	 * 直线行驶 纬度
	 */
	private Double directLineLat;
	
	/**
	 * 直线行驶 经度
	 */
	private Double directLineLng;
	
	/**
	 * 结束直线行驶 纬度
	 */
	private Double endDirectLineLat;
	
	/**
	 * 结束直线行驶 经度
	 */
	private Double endDirectLineLng;

	/**
	 * 超车 纬度
	 */
	private Double overtakeLat;
	
	/**
	 * 超车 经度
	 */
	private Double overtakeLng;
	
	/**
	 * 前方掉头 纬度
	 */
	private Double turnLat;
	/**
	 * 前方掉头 经度
	 */
	private Double turnLng;

	/**
	 * 请靠边停车 纬度
	 */
	private Double pullOverLat;
	
	/**
	 * 请靠边停车 经度
	 */
	private Double pullOverLng;
	/**
	 * 会车 纬度
	 */
	private Double passingLat;
	
	/**
	 * 会车 经度
	 */
	private Double passingLng;
	
	

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



	@Override
	public String toString() {
		StringBuffer history = new StringBuffer();
		history.append(" id:").append(id)
				.append(" lineName:").append(lineName)
				.append(" changeLanesLat:").append(changeLanesLat)
				.append(" changeLanesLng:").append(changeLanesLng)
				.append(" aheadDirectLineLat:").append(aheadDirectLineLat)
				.append(" aheadDirectLineLng:").append(aheadDirectLineLng)
				.append(" turnLeftLat:").append(turnLeftLat)
				.append(" turnLeftLng:").append(turnLeftLng)
				.append(" turnRightLat:").append(turnRightLat)
				.append(" turnRightLng:").append(turnRightLng)
				.append(" sidewalkLat:").append(sidewalkLat)
				.append(" sidewalkLng:").append(sidewalkLng)
				
				.append(" passSchoolLat:").append(passSchoolLat)
				.append(" passSchoolLng:").append(passSchoolLng)
				.append(" passBusStationLat:").append(passBusStationLat)
				.append(" passBusStationLng:").append(passBusStationLng)
				.append(" passSidewalkLat:").append(passSidewalkLat)
				.append(" passSidewalkLng:").append(passSidewalkLng)
				.append(" directLineLat:").append(directLineLat)
				.append(" directLineLng:").append(directLineLng)
				.append(" endDirectLineLat").append(endDirectLineLat)
				.append(" endDirectLineLng").append(endDirectLineLng)
				
				.append(" overtakeLat:").append(overtakeLat)
				.append(" overtakeLng:").append(overtakeLng)
				.append(" turnLat:").append(turnLat)
				.append(" turnLng:").append(turnLng)
				.append(" pullOverLat:").append(pullOverLat)
				.append(" pullOverLng:").append(pullOverLng)
				.append(" passingLat:").append(passingLat)
				.append(" passingLng:").append(passingLng);		
		
		return history.toString();
	}
	
	
}
