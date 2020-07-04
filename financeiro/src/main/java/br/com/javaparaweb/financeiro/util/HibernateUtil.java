package br.com.javaparaweb.financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/*Classe ponte entre o arquivo de configuração e a conexao com o banco
 * 
 * Fica responsavel por instanciar a SessionFactory do Hibernate e retorna-la sempre que necessário
 * */
public class HibernateUtil {
	//Objeto que recebe o retorno de buildSessionFactory (a factory em si)
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration(); //hibernate procura pelo hibernate.properties
			cfg.configure("hibernate.cfg.xml"); //Leitura do arquvo de configuração (procura por ele e nao mais o properties acima
			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(cfg.getProperties());
			StandardServiceRegistry servico = registradorServico.build();
			return cfg.buildSessionFactory(servico);
		}catch(Throwable e) {
			System.out.println("Criação inical do objeto SessionFactory falhou. Erro "+e);
			throw new ExceptionInInitializerError(e);
			
			
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
