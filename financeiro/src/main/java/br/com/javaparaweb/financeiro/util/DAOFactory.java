package br.com.javaparaweb.financeiro.util;

import br.com.javaparaweb.financeiro.usuario.UsuarioDAO;
import br.com.javaparaweb.financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		/*Objeto do tipo usuarioDAOhibernate - instancia de UsuarioDAOHibernate*/
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		/*Setando a sessao passando sessionFactory e pegando a sessao corrente*/
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		//retorna o usuarioDAO
		return usuarioDAO;
	}

}
