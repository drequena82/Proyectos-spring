package io.spring.billing.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@TableGenerator(
		name="LINE_GEN",
		initialValue= 15,
		pkColumnName="ENTITY",
		pkColumnValue="ID",
		allocationSize=10,
		table="LINE_GEN"
		)

@Table(name="LINE",schema="BILLING",indexes={@Index(name="line_pk",columnList="id")})
@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Line  implements BillingEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="LINE_GEN")
	private Long id;
	
	@Embedded
	private Audit audit;
	
	@ManyToOne
	@JoinColumn(name="bill_id")
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="QUANTITY")
	private Integer quantity;
}
