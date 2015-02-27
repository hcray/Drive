package com.daoliuhe.drive.bean;

/**
 * 坐标点 bean
 * @author 21829
 *
 */
public class LocationBean {
	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 声音类型
	 */
	private Integer voiceType;
	
	/**
	 * 线路id
	 */
	private Integer lineId;
	
	/**
	 * 纬度
	 */
	private Double latitude;
	
	/**
	 * 经度
	 */
	private Double longitude;
	
	/**
	 * 方位
	 */
	private Float bearing;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoiceType() {
		return voiceType;
	}

	public void setVoiceType(Integer voiceType) {
		this.voiceType = voiceType;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Float getBearing() {
		return bearing;
	}

	public void setBearing(Float bearing) {
		this.bearing = bearing;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id:").append(id)
		.append(" voiceType:").append(voiceType)
		.append(" lineId:").append(lineId)
		.append(" latitude:").append(latitude)
		.append(" longitude:").append(longitude)
		.append(" bearing:").append(bearing);
		return sb.toString();
	}
}
