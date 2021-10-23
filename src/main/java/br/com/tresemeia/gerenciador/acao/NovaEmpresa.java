package br.com.tresemeia.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tresemeia.gerenciador.modelo.Banco;
import br.com.tresemeia.gerenciador.modelo.Empresa;

public class NovaEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//"nome" � a vari�vel pega da url(http://localhost:8080/gerenciador/novaEmpresa?nome=Atos)
				String nomeEmpresa = request.getParameter("nome");
				String paramDataEmpresa = request.getParameter("data");
				
				Date dataAbertura = null;
				
				try {
					//Formata de string para date
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					dataAbertura = sdf.parse(paramDataEmpresa);
				} catch (ParseException e) {
					throw new ServletException(e);
				}
				
				Empresa empresa = new Empresa();
				empresa.setNome(nomeEmpresa);
				empresa.setDataAbertura(dataAbertura);
				
				Banco banco = new Banco();
				banco.adiciona(empresa);
				
				/*
				 * Exemplo de c�digo para imprmir diretamente o c�digo, n�o � o recomendado.
				 * PrintWriter out = response.getWriter();
				 * out.println("<html><body><h1>Empresa " + nomeEmpresa +
				 * " cadastrada com sucesso!!!</h1></body><html>");
				 */
				
				//Manda para o navegador o novo endere�o da p�gina, redireciona.
				return "redirect:entrada?acao=ListaEmpresas";
				
				//Chamar o JSP
				/* RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas"); */
				//"empresa" � o nome que ser� chamado no outro lado
				/* request.setAttribute("empresa", empresa.getNome()); */
				//Envia o request e o response
				/* rd.forward(request, response); */
		
		
	}
}
