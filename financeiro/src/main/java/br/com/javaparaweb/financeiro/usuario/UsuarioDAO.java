package br.com.javaparaweb.financeiro.usuario;

import java.util.List;

/*Conterá todas as assinaturas de metodos reposnsveis por manipular o banco*/
public interface UsuarioDAO {
	public void salvar           (Usuario usuario); //Salva usando um objeto do tipo Usuario
	public void atualizar        (Usuario usuario); //Atualiza/edita usando um objeto do tipo Usuario
	public void excluir          (Usuario usuario); //Deleta usando um objeto do tipo Usuario
	public Usuario carregar      (Integer codigo);  //Carrega um usuario pelo seu codigo
	public Usuario buscarPorLogin(String login);    //Busca um usuario pelo seu login
	public List<Usuario> listar();                  //Metodo para listar todos os usuarios
}
