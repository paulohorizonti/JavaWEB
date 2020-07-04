package br.com.javaparaweb.financeiro.usuario;
/*Implementa a clase  UsuarioDAO, que tem os metodos para manipulacao do banco*/
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO {
	/*Obrigatorio para toda DAOHibernate, é pelo setSession que injetamos a sessao do hibernate
	 * na classe que vai implementar os metodos CRUD
	 * */
	private Session session; 
	
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	

	/*Metodo que ira salvar um usuario no banco passando o objeto Usuario como parametros*/
	@Override
	public void salvar(Usuario usuario) {
		this.session.save(usuario);

	}

	/*Metodo que ira atualizar um usuario no banco passando o objeto Usuario como parametros*/
	@Override
	public void atualizar(Usuario usuario) {
		this.session.update(usuario);

	}
	/*Metodo que ira exlcuir um usuario no banco passando o objeto Usuario como parametros*/
	@Override
	public void excluir(Usuario usuario) {
		this.session.delete(usuario);

	}
	/*Metodo que ira carregar um usuario passando seu codigo como parametro*/
	@Override
	public Usuario carregar(Integer codigo) {
		
		return (Usuario) this.session.get(Usuario.class, codigo);//retorno
	}

	/*Metodo que ira buscar um usuario no banco pelo seu login */
	@Override
	public Usuario buscarPorLogin(String login) {
		String hql = "select u from Usuario u where u.login = :login";
		Query consulta = this.session.createQuery(hql); //cria uma query com o comando sql
		consulta.setString("login", login);             //passa o parametro
		return (Usuario) consulta.uniqueResult();       //retorna um unico usuario, pois o login é unico
	}
	/*Metodo que ira retornar uma lista com todos os usuarios do banco*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}

}
