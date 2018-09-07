package io.spring.billing.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class Audit {
	
	@Column(name="update_on")
	private Date updateOn;
	
	@Column(name="create_on")
	private Date createOn;
}
