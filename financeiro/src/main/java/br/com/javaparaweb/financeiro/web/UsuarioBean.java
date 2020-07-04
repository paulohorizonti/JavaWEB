package br.com.javaparaweb.financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.usuario.UsuarioRN;

/*Classe que atende as telas de visualização
 * é um backbean do jsf. Guarda todos os dados e funcionalidades para atender a tela que
 * sera criada
 * Tera, logicamente, uma intancia de usuario
 * 
 * */
@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;

	/*
	 * FAz a entrada dos dados no formulario, é iniciado aqui a trajetoria do objeto
	 */
	public String novo() {
		this.destinoSalvar = "usuariosucesso"; // no novo ele vai mandar para essa pagina
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/usuario"; /* Retorna a pagina usuario da pasta publica */

	}

	/* Metodo para alterar um registro */
	public String editar() {
		this.confirmarSenha = this.usuario.getSenha(); // pega a senha digitada pelo usuario
		return "/publico/usuario"; // retorna a pagina de cadasrtro de usuario
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha(); // pega a senha e coloca numa variavel
		if (!senha.equals(this.confirmarSenha)) { // compara a senha com a senha digitada
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);// coloca a mensagem no contexto
		}

		/*
		 * Aqui ele instancia um objeto de usuarioRN e passa o objeto dessa classe para
		 * que ele seja salvo
		 */
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		return this.destinoSalvar; // agora retorna o destino salvar
	}

	/* Metodo para excluir */
	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null; // força o reload da lista
		return null; // nao retorna nada
	}

	/* Metodo para ativar um registro */
	public String ativar() {
		if (this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		} else {
			this.usuario.setAtivo(true);
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}
	
	public List<Usuario> getLista(){
		if(this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista; //retorna a lista carregada
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	
	
	

}
