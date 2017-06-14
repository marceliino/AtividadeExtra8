package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="paciente", catalog="SCJPER4", uniqueConstraints = {
		@UniqueConstraint(columnNames="cpf")
})
public class Paciente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8887552699500377568L;

	@Id
	@Column(name="cpf", unique=true, nullable=false, length=11)
	private String cpf;
	
	@Column(name="nome", length=45)
	private String nome;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="dataNasc")
	private Date dataNascimento;
	
	@Column(name="telefone", length=20)
	private String telefone;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pacientes")
	private Set<Agenda> agendas = new HashSet<Agenda>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="paciente")
	private Set<Procedimento> procedimentos = new HashSet<Procedimento>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="paciente")
	private Set<MatMed> matMeds = new HashSet<MatMed>();

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Set<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Set<MatMed> getMatMeds() {
		return matMeds;
	}

	public void setMatMeds(Set<MatMed> matMeds) {
		this.matMeds = matMeds;
	}
}