package br.com.javaparaweb.financeiro.web.filter;
/*Mantem a conexao durante toda o uso do sistmema*/

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import br.com.javaparaweb.financeiro.util.HibernateUtil;

@WebFilter(urlPatterns = {"*.jsf"})  //Tipo de requisição que sera interceptada
public class ConexaoHibernateFilter implements Filter { //interface filter exite a implementacçaõ dos metodos abaixo
	
	private SessionFactory sf;
	
	/*Executado somente quando um aplicativo web em questao é colocado no ar
	 * Tb é aqui que iniciamos a getSessionFactory a cada requisição
	 * */
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
		
	}
	
	/*Onde a requisição é interceptada*/

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		Session currentSession = this.sf.getCurrentSession();
		
		Transaction transaction = null;
		
		try {
			transaction = currentSession.beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			transaction.commit();
			if(currentSession.isOpen()) {
				currentSession.close();
			}
		}catch(Throwable ex) {
			try {
				if(transaction.isActive()) {
					transaction.rollback();
				}
			}catch(Throwable t) {
				t.printStackTrace();
				
			}
			
			throw new ServletException(ex);
			
		}
		
	}
	/*Quando o aplicativo é desativado ou o servidor é retirado do ar*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
