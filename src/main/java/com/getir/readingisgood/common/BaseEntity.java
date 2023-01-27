package com.getir.readingisgood.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuppressWarnings("serial")

@Getter
@Setter
@ToString(of = { "modifyDate" })
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Column(name = "MODIFY_DATE", nullable = false)
	private Date modifyDate;

	@PreUpdate
	@PrePersist
	private void setTimestamp() {
		setModifyDate(new Date());
	}

}
