package com.daoliuhe.drive.bean;

/**
 * 路线bean
 * @author 21829
 * 
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
	 * 备注
	 */
	private String comment;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		StringBuffer history = new StringBuffer();
		history.append(" id:").append(id)
				.append(" lineName:").append(lineName)
				.append(" comment:").append(comment);		
		return history.toString();
	}
	
	
}
