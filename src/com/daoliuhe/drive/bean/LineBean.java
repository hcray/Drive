package com.daoliuhe.drive.bean;

/**
 * @author 21829
 * ·��bean
 */
public class LineBean {
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * ·������
	 */
	private String lineName;

	/**
	 * ǰ��·����ת γ��
	 */
	private Double turnRightLat; // γ��
	
	/**
	 * ǰ��·����ת ����
	 */
	private Double turnRightLng; // ����

	/**
	 * ǰ�����κ�� γ�� 
	 */
	private Double sidewalkLat;
	
	/**
	 * ǰ�����κ�� ����
	 */
	private Double sidewalkLng;

	/**
	 * ͨ�����к�� γ��
	 */
	private Double passSidewalkLat;
	
	/**
	 * ͨ�����к�� ����
	 */
	private Double passSidewalkLng;

	/**
	 * ǰ��·����ת γ��
	 */
	private Double turnLeftLat;
	
	/**
	 * ǰ��·����ת ����
	 */
	private Double turnLeftLng;

	/**
	 * ǰ��·��ֱ�� γ��
	 */
	private Double aheadDirectLineLat;
	
	/**
	 * ǰ��·��ֱ�� ����
	 */
	private Double aheadDirectLineLng;

	/**
	 * ͨ����������վ γ��
	 */
	private Double passBusStationLat;
	
	/**
	 * ͨ����������վ ����
	 */
	private Double passBusStationLng;

	/**
	 * ֱ����ʻ γ��
	 */
	private Double directLineLat;
	
	/**
	 * ֱ����ʻ ����
	 */
	private Double directLineLng;

	/**
	 * ͨ��ѧУ���� γ��
	 */
	private Double passSchoolLat;
	
	/**
	 * ͨ��ѧУ���� ����
	 */
	private Double passSchoolLng;

	/**
	 * ǰ���������� γ��
	 */
	private Double changeLanesLat;
	
	/**
	 * ǰ���������� ����
	 */
	private Double changeLanesLng;

	/**
	 * ��������� γ��
	 */
	private Double slowdownLat;
	
	/**
	 * ��������� ����
	 */
	private Double slowdownLng;

	/**
	 * ·��������� γ��
	 */
	private Double speedLimitLat;
	
	/**
	 * ·��������� ����
	 */
	private Double speedLimitLng;

	/**
	 * ͨ��ѧУ-����վ γ��
	 */
	private Double passSchoolStationLat;
	
	/**
	 * ͨ��ѧУ-����վ ����
	 */
	private Double passSchoolStationLng;

	/**
	 * ǰ����ͷ γ��
	 */
	private Double turnLat;
	
	/**
	 * ǰ����ͷ ����
	 */
	private Double turnLng;

	/**
	 * �뿿��ͣ�� γ��
	 */
	private Double pullOverLat;
	
	/**
	 * �뿿��ͣ�� ����
	 */
	private Double pullOverLng;

	/**
	 * ע��������� γ��
	 */
	private Double backCarLat;
	
	/**
	 * ע��������� ����
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
