package br.com.tresemeia.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tresemeia.gerenciador.acao.Acao;

/*@WebFilter("/entrada")*/
public class ControladorFilter implements Filter {  
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		System.out.println("ControladorFilter");
		
		//Cast: A referência mais genérica de "servletRequest" aponta para um objeto do tipo "HttpServletRequest"
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		//Cast: A referência mais genérica de "servletResponse" aponta para um objeto do tipo "HttpServletResponse"
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
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
		
		HttpSession sessao = request.getSession();
		boolean usuarioNaoLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
		
		if(ehUmaAcaoProtegida && usuarioNaoLogado ) {
			response.sendRedirect("entrada?acao=LoginForm");
			//Para sair, não entrar no try abaixo
			return;
		}
		
		chain.doFilter(request, response);
	}	

}
