package br.com.tresemeia.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@WebFilter("/entrada")*/
public class AutorizacaoFilter implements Filter {  
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		System.out.println("AutorizacaoFilter");
		
		//Cast: A referência mais genérica de "servletRequest" aponta para um objeto do tipo "HttpServletRequest"
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		//Cast: A referência mais genérica de "servletResponse" aponta para um objeto do tipo "HttpServletResponse"
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
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
