package com.srans.nestserver.model;

import java.io.Serializable;

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
	long id;

	@Column
	long hostelId;

	@Column
	long floorId;

	@Column
	long roomId;

	@Column
	String position;

	@Column
	Character alloted;

	@Column
	String vacatedDate;

	@Column
	Integer bedNo;

	public Bed() {

		super();
		this.id = 0L;
		this.hostelId = 0L;
		this.floorId = 0L;
		this.roomId = 0L;
		this.alloted = ' ';
		this.position = "";
		this.vacatedDate = "";
		this.bedNo = 0;

	}

	public Bed(long id, long hostelId, long floorId, long roomId, String position, Character alloted,
			String vacatedDate, Integer bedNo) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.floorId = floorId;
		this.roomId = roomId;
		this.position = position;
		this.alloted = alloted;
		this.vacatedDate = vacatedDate;
		this.bedNo = bedNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHostelId() {
		return hostelId;
	}

	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	public long getFloorId() {
		return floorId;
	}

	public void setFloorId(long floorId) {
		this.floorId = floorId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Character getAlloted() {
		return alloted;
	}

	public void setAlloted(Character alloted) {
		this.alloted = alloted;
	}

	public String getVacatedDate() {
		return vacatedDate;
	}

	public void setVacatedDate(String vacatedDate) {
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
				.append(floorId).append(", roomId=").append(roomId).append(", position=").append(position)
				.append(", alloted=").append(alloted).append(", vacatedDate=").append(vacatedDate).append(", bedNo=")
				.append(bedNo).append("]");
		return builder.toString();
	}

}
