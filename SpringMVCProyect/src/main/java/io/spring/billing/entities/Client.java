package io.spring.billing.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@TableGenerator(
		name="CLIENT_GEN",
		initialValue= 1,
		pkColumnName="ENTITY",
		pkColumnValue="ID",
		allocationSize=10,
		table="CLIENT_GEN"
		)
@Table(name="CLIENT",schema="billing",indexes={@Index(name="client_pk",columnList="id")})
@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Client  implements BillingEntity{
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.TABLE,generator="CLIENT_GEN")
	private Long id;
	
	@Column(name="NAME", nullable=false, length=100)
	private String name;
	
	@Embedded
	private Audit audit;
	
	@Column(name="SURNAME", nullable=false, length=100)
	private String surname;
	
	@Column(name="EMAIL", nullable=false, length=200)
	private String email;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_bills")
	private List<Bill> bills;
}
