package br.com.fiap.aplicacao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import br.com.fiap.dao.Dao;
import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.MatMed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;

public class Main {

	public static void main(String[] marcelo) {
		
		incluirAgenda();
		listarPacientes();
		pesquisarPaciente("28567221803");
	}

	public static String geraCPF() {
		Integer cpf = new Random().nextInt(Integer.MAX_VALUE)+100;
		return cpf.toString();
	}
	
	public static String formataData(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	private static void pesquisarPaciente(String cpf) {
		Dao dao = new Dao();
		Paciente paciente = dao.pesquisar(cpf);
		System.out.println("##############################################################");
		System.out.println("PESQUISAR PACIENTE:");
		System.out.println(">> Nome do Paciente: " + paciente.getNome() + " - CPF: " + paciente.getCpf() + " - Data de Nascimento: " + formataData(paciente.getDataNascimento()));

		Set<Procedimento> procedimentos = paciente.getProcedimentos();
		System.out.println(">> LISTA DE PROCEDIMENTOS:");
		for (Procedimento procedimento : procedimentos) {
			System.out.println(">>> " + procedimento.getId() + " - " + procedimento.getDescricao() + " - R$ " + procedimento.getPreco());
		}

		Set<MatMed> matMeds = paciente.getMatMeds();
		System.out.println("LISTA DE MATERIAIS:");
		for (MatMed matMed : matMeds) {
			System.out.println(">>> " + matMed.getId() + " - " + matMed.getDescricao() + " - R$ " + matMed.getPreco() + " - " + matMed.getFabricante());
		}
		System.out.println("##############################################################");
		System.out.println("");
	}

	private static void listarPacientes() {
		Dao dao = new Dao();
		List<Paciente> pacientes = dao.listar();
		System.out.println("##############################################################");
		System.out.println("LISTAR PACIENTES:");
		for (Paciente paciente : pacientes) {
			System.out.println(">> Nome do Paciente: " + paciente.getNome() + " - CPF: " + paciente.getCpf());
		}
		System.out.println("##############################################################");
		System.out.println("");
	}//
	
	private static void incluirAgenda() {
		
		Dao dao = new Dao();
		boolean hasCPF = dao.isEmpty();
		
		Set<Agenda> agendas = new HashSet<Agenda>();
		Set<Paciente> pacientes = new HashSet<>();
		Set<MatMed> matMeds = new HashSet<MatMed>();
		Set<Procedimento> procedimentos = new HashSet<Procedimento>();
		
		Agenda agenda = new Agenda();
		agenda.setData(new Date());
		agenda.setDescricao("Tratamento Odontologico");
		agenda.setHora(new Date());
		agendas.add(agenda);
		
		Paciente paciente = new Paciente();
		paciente.setAgendas(agendas);
		if(hasCPF) {
			paciente.setCpf(geraCPF());
		} else {
			paciente.setCpf("28567221803");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(1979,7,16);
		paciente.setDataNascimento(calendar.getTime());
		paciente.setNome("Marcelo SiLva");
		paciente.setTelefone("456-6556");
		pacientes.add(paciente);
		
		MatMed matMed = new MatMed();
		matMed.setDescricao("Enxaguante Bocal");
		matMed.setFabricante("Quimica Brasil");
		matMed.setPaciente(paciente);
		matMed.setPreco(123.23d);
		matMeds.add(matMed);
		paciente.setMatMeds(matMeds);
		
		MatMed matMed2 = new MatMed();
		matMed2.setDescricao("Aparelho de Modelar Dente");
		matMed2.setFabricante("Protetico Protese Dentaria");
		matMed2.setPaciente(paciente);
		matMed2.setPreco(501.73d);
		matMeds.add(matMed2);
		paciente.setMatMeds(matMeds);
		
		Procedimento procedimento = new Procedimento();
		procedimento.setDescricao("Limpeza dos Dentes");
		procedimento.setPaciente(paciente);
		procedimento.setPreco(121.38d);
		procedimentos.add(procedimento);
		paciente.setProcedimentos(procedimentos);
		
		Procedimento procedimento2 = new Procedimento();
		procedimento2.setDescricao("Instalacao de Aparelho");
		procedimento2.setPaciente(paciente);
		procedimento2.setPreco(704.91d);
		procedimentos.add(procedimento2);
		paciente.setProcedimentos(procedimentos);
		
		agenda.setPacientes(pacientes);
		dao.adicionar(agenda);
	}
}