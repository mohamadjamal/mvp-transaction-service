package com.demo.mvp.transactions.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5109905503081678862L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@JsonIgnore
	@Column(name = "ID", updatable = false)
	private String id;

	@CreatedDate
	@JsonIgnore
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@LastModifiedDate
	@JsonIgnore
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

}
