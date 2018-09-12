package io.spring.billing.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@TableGenerator(
		name="BILL_GEN",
		initialValue= 1,
		pkColumnName="ENTITY",
		pkColumnValue="ID",
		allocationSize=10,
		table="BILL_GEN"
		)

@Table(name="BILL",schema="BILLING",indexes={@Index(name="bill_pk",columnList="id")})
@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Bill implements BillingEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="BILL_GEN")
	private Long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Embedded
	private Audit audit;
	
	@ManyToOne
	private Client client;
	
	@Column(name="OBSERVATION")
	private String observation;
	
	@OneToMany(mappedBy="bill")//Referencia al objeto de la otra clase
	private List<Line> lines;
}
