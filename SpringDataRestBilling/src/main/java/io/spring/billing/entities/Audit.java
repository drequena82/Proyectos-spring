package io.spring.billing.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class Audit {

	@Column(name = "updateOn")
	private Date updateOn;

	@Column(name = "createOn")
	@NotEmpty
	private Date createOn;

	@PrePersist
	public void prePersist() {
		this.createOn = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateOn = new Date();
	}
}
