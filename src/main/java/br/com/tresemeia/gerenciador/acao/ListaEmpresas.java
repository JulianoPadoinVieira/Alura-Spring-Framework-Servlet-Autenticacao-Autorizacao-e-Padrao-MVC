package br.com.tresemeia.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tresemeia.gerenciador.modelo.Banco;
import br.com.tresemeia.gerenciador.modelo.Empresa;

public class ListaEmpresas implements Acao{
	
	//Metodo para listar as empresas, não é um servlet
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Listando empresas");
		
		Banco banco = new Banco();
		List<Empresa> lista = banco.getEmpresas();
		
		request.setAttribute("empresas", lista);
		
		return "forward:listaEmpresas.jsp";
		
	}

}
