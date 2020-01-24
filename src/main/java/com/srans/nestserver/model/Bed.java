package com.srans.nestserver.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bed")
public class Bed extends AuditModel implements Serializable {

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	Long id;

	@Column
	Long hostelId;

	@Column
	Long floorId;

	@Column
	Long roomId;

	@Column
	Character alloted;

	@Column
	String position;

	@Column
	Date vacatedDate;

	@Column
	Integer bedNo;

	public Bed() {
		super();
		this.hostelId = -1L;
		this.floorId = -1L;
		this.roomId = -1L;
		this.alloted = 'N';
		this.position = "L";
		this.vacatedDate = null;
		this.bedNo = 1;

	}

	public Bed(Long id, Long hostelId, Long floorId, Long roomId, Character alloted, String position, Date vacatedDate,
			Integer bedNo) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.floorId = floorId;
		this.roomId = roomId;
		this.alloted = alloted;
		this.position = position;
		this.vacatedDate = vacatedDate;
		this.bedNo = bedNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Character getAlloted() {
		return alloted;
	}

	public void setAlloted(Character alloted) {
		this.alloted = alloted;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getVacatedDate() {
		return vacatedDate;
	}

	public void setVacatedDate(Date vacatedDate) {
		this.vacatedDate = vacatedDate;
	}

	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bed [id=").append(id).append(", hostelId=").append(hostelId).append(", floorId=")
				.append(floorId).append(", roomId=").append(roomId).append(", alloted=").append(alloted)
				.append(", position=").append(position).append(", vacatedDate=").append(vacatedDate).append(", bedNo=")
				.append(bedNo).append("]");
		return builder.toString();
	}

}
