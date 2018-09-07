package io.spring.billing.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@TableGenerator(
		name="PRODUCT_GEN",
		initialValue= 9,
		pkColumnName="ENTITY",
		pkColumnValue="ID",
		allocationSize=10,
		table="PRODUCT_GEN"
		)

@Table(name="PRODUCT",schema="BILLING",indexes={@Index(name="product_pk",columnList="id")})
@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Product  implements BillingEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="PRODUCT_GEN")
	private Long id;
	
	@Embedded
	private Audit audit;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PRICE")
	private Double price;
}
