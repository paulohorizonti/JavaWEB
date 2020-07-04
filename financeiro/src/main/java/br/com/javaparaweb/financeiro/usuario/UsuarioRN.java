package br.com.javaparaweb.financeiro.usuario;
/*Clase que conterá as regras de negocio, é a unica que vai comunicar com a camada 
 * de dados
 * 
 * -> Os metodos dessa classe so fazem o repassa para a clase DAO, alguns antes
 * implementam regras de negocio especificas, como o caso do metodo salvar
 * onde ele verifica se o usuario passado no parametro tem um codigo, caso tenha
 * ele vai atualizar e nao salvar.
 * 
 * */
import java.util.List;

import br.com.javaparaweb.financeiro.util.DAOFactory;

public class UsuarioRN {
	
	/*Objeto do tipo UsuarioDAO que vai receber uma instancia
	 * de DAOFactory
	 * 
	 * */
	private UsuarioDAO usuarioDAO;
	
	/*Construtor da classe, o objeto criado acima ira receber uma instancia de
	 * DAOFactory executando o metodo criarUsuarioDAO, que por sua vez
	 * retorna uma instancia de getSessionFactory do hibernateUtil*/
	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	/*Carrega um usuario baseado no codigo*/
	public Usuario carregar(Integer codigo) {
		
		return this.usuarioDAO.carregar(codigo);
	}
	/*Metodo busca por login*/
	public Usuario buscarPorLogin(String login) {
		
		return this.usuarioDAO.buscarPorLogin(login);
	}

	/*Metodo Salvar: Aqui ele implementa o metodo salvar do dao, mas ja com o objeto DaoFActory*/
	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo(); //pega o codigo do usuario e atribui à variavel local
		
		/*Verifica o conteudo da variavel*/
		if(codigo == null || codigo ==0) {
			this.usuarioDAO.salvar(usuario);
		}else {
			this.usuarioDAO.atualizar(usuario);//se o codigo vier preenchido quer dizer que é pra alterar (atualizar)
		}
		
	}

	//Metodo para excluir usuario
	public void excluir(Usuario usuario) {
		this.usuarioDAO.excluir(usuario); //chama o metodo excluir passando o usuario como parametro

	}

	
	
	public List<Usuario> listar() {
		return this.usuarioDAO.listar(); //retorna uma lista de todos os usuarios
	}

}
