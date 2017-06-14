package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name ="agenda", catalog = "SCJPER4", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class Agenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4975930519181379135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="data")
	private Date data;
	
	@Temporal(value=TemporalType.TIME)
	private Date hora;
	
	@Column(name = "descricao", unique = false, nullable = false, length = 45)
	private String descricao;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name="agenda_paciente", catalog="SCJPER4", joinColumns = {
			@JoinColumn(name="agenda_id", nullable=false, updatable=false)}, inverseJoinColumns = {
					@JoinColumn(name = "paciente_cpf", nullable = false, updatable = false) })
	
	private Set<Paciente> pacientes = new HashSet<Paciente>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
}