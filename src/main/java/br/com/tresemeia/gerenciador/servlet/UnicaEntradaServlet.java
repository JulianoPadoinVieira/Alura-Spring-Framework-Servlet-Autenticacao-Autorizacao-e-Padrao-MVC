package br.com.tresemeia.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tresemeia.gerenciador.acao.Acao;
import br.com.tresemeia.gerenciador.acao.AlteraEmpresa;
import br.com.tresemeia.gerenciador.acao.ListaEmpresas;
import br.com.tresemeia.gerenciador.acao.MostraEmpresa;
import br.com.tresemeia.gerenciador.acao.NovaEmpresaForm;
import br.com.tresemeia.gerenciador.acao.NovaEmpresa;
import br.com.tresemeia.gerenciador.acao.RemoveEmpresa;

/**
 * Servlet implementation class UnicaEntradaServlet
 */
/* @WebServlet("/entrada") */
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Lê o parâmetro que define a ação
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.tresemeia.gerenciador.acao." + paramAcao;
		
		String nome;
		try {
			//Carrega a classe com seu nome
			Class classe = Class.forName(nomeDaClasse); 		
			//Cria a instância da ação já fazendo um cast para a interface
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEEndereco =  nome.split(":");			
		if(tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
				response.sendRedirect(tipoEEndereco[1]);
		}
		
		/*
		 * String nome = null; if (paramAcao.equals("ListaEmpresas")) { ListaEmpresas
		 * acao = new ListaEmpresas(); nome = acao.executa(request, response); } else
		 * if(paramAcao.equals("RemoveEmpresa")) { RemoveEmpresa acao = new
		 * RemoveEmpresa(); nome = acao.executa(request, response); } else
		 * if(paramAcao.equals("MostraEmpresa")) { MostraEmpresa acao = new
		 * MostraEmpresa(); nome = acao.executa(request, response); } else
		 * if(paramAcao.equals("AlteraEmpresa")) { AlteraEmpresa acao = new
		 * AlteraEmpresa(); nome = acao.executa(request, response); } else
		 * if(paramAcao.equals("NovaEmpresa")) { NovaEmpresa acao = new NovaEmpresa();
		 * nome = acao.executa(request, response); } else
		 * if(paramAcao.equals("NovaEmpresaForm")) { NovaEmpresaForm acao = new
		 * NovaEmpresaForm(); nome = acao.executa(request, response); }
		 */
		
		
	}
}	

