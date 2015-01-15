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
	 * 通过人行横道 纬度
	 */
	private Double passSidewalkLat;
	
	/**
	 * 通过人行横道 经度
	 */
	private Double passSidewalkLng;

	/**
	 * 前方路口左转 纬度
	 */
	private Double turnLeftLat;
	
	/**
	 * 前方路口左转 经度
	 */
	private Double turnLeftLng;

	/**
	 * 前方路口直行 纬度
	 */
	private Double aheadDirectLineLat;
	
	/**
	 * 前方路口直行 经度
	 */
	private Double aheadDirectLineLng;

	/**
	 * 通过公共汽车站 纬度
	 */
	private Double passBusStationLat;
	
	/**
	 * 通过公共汽车站 经度
	 */
	private Double passBusStationLng;

	/**
	 * 直行行驶 纬度
	 */
	private Double directLineLat;
	
	/**
	 * 直行行驶 经度
	 */
	private Double directLineLng;

	/**
	 * 通过学校区域 纬度
	 */
	private Double passSchoolLat;
	
	/**
	 * 通过学校区域 经度
	 */
	private Double passSchoolLng;

	/**
	 * 前方请变更车道 纬度
	 */
	private Double changeLanesLat;
	
	/**
	 * 前方请变更车道 经度
	 */
	private Double changeLanesLng;

	/**
	 * 请减速让行 纬度
	 */
	private Double slowdownLat;
	
	/**
	 * 请减速让行 经度
	 */
	private Double slowdownLng;

	/**
	 * 路段最低限速 纬度
	 */
	private Double speedLimitLat;
	
	/**
	 * 路段最低限速 经度
	 */
	private Double speedLimitLng;

	/**
	 * 通过学校-汽车站 纬度
	 */
	private Double passSchoolStationLat;
	
	/**
	 * 通过学校-汽车站 经度
	 */
	private Double passSchoolStationLng;

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
	 * 注意左后方来车 纬度
	 */
	private Double backCarLat;
	
	/**
	 * 注意左后方来车 经度
	 */
	private Double backCarLng;

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

	public Double getSlowdownLat() {
		return slowdownLat;
	}

	public void setSlowdownLat(Double slowdownLat) {
		this.slowdownLat = slowdownLat;
	}

	public Double getSlowdownLng() {
		return slowdownLng;
	}

	public void setSlowdownLng(Double slowdownLng) {
		this.slowdownLng = slowdownLng;
	}

	public Double getSpeedLimitLat() {
		return speedLimitLat;
	}

	public void setSpeedLimitLat(Double speedLimitLat) {
		this.speedLimitLat = speedLimitLat;
	}

	public Double getSpeedLimitLng() {
		return speedLimitLng;
	}

	public void setSpeedLimitLng(Double speedLimitLng) {
		this.speedLimitLng = speedLimitLng;
	}

	public Double getPassSchoolStationLat() {
		return passSchoolStationLat;
	}

	public void setPassSchoolStationLat(Double passSchoolStationLat) {
		this.passSchoolStationLat = passSchoolStationLat;
	}

	public Double getPassSchoolStationLng() {
		return passSchoolStationLng;
	}

	public void setPassSchoolStationLng(Double passSchoolStationLng) {
		this.passSchoolStationLng = passSchoolStationLng;
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

	public Double getBackCarLat() {
		return backCarLat;
	}

	public void setBackCarLat(Double backCarLat) {
		this.backCarLat = backCarLat;
	}

	public Double getBackCarLng() {
		return backCarLng;
	}

	public void setBackCarLng(Double backCarLng) {
		this.backCarLng = backCarLng;
	}
}
