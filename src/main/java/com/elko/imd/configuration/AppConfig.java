/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.configuration;

import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 
 * AppConfig is class for setting up configuration of application.
 * This class is responsible for organizing mapping classes, session management, security, database connections and transactions management.
 * Annotating a class with the {@code @Configuration } indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 * The {@code @Bean} annotation tells Spring that a method annotated with {@code @Bean } will return an object that should be registered as a bean in the Spring application context.
 * 
 * 
 * @author elko 
 * @see  <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html"> Configuration </a>
 * @see  <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html"> EnableWebMvc</a>
 * @see  <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/annotation/EnableTransactionManagement.html"> EnableTransactionManagement</a>
 * @see  <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html">ComponentScan</a>
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({"com.elko.imd.*"})
public class AppConfig {
    
    /**
     * This method will create sessions for access database.
     * The SessionFactory is the concept that is a single data store and thread safe. 
     * Because of this feature, many threads can access this concurrently and the sessions are requested, 
     * and also the cache that is immutable of compiled mappings for a specific database. 
     *  A SessionFactory will be built only at the time of its startup. In order to access it in the application code, 
     * it should be wrapped in singleton. This wrapping makes the easy accessibility to it in an application code.
     * This object are contains the connection information, 
     * hibernate configuration information and mapping files,location path. 
     * 
     * @return SessionFactory (Creates a Sessions).
     * @see <a href="https://docs.jboss.org/hibernate/orm/5.1/javadocs/org/hibernate/SessionFactory.html"> SessionFactory </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/orm/hibernate5/LocalSessionFactoryBuilder.html"> LocalSessionFactoryBuilder </a>
     */
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.elko.imd.model").addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }
        
        /**
         * This method for setting up hibernate properties such as sql format, hibernate dialect and etc.. 
         * 
         * @return Properties of hiberbate
         * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html"> Properties </a>
         * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/session-configuration.html">Hibernate Configuration </a>
         * 
         */
	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }
	/**
         * dataSource is method for setting configuration database such as database Driver, URL database,
         * username and password. In this application we use MySql for database.
         * 
         * @return object of BasicDataSource
         * @see <a href="https://commons.apache.org/proper/commons-dbcp/api-1.4/org/apache/commons/dbcp/BasicDataSource.html"> BasicDatasource </a>
         * 
         */
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
                ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/imdsocmed?zeroDateTimeBehavior=convertToNull");
		ds.setUsername("root");
                ds.setPassword("");
		return ds;
	}
	
        /**
         * this method is build to manage transaction of the sessions. This transaction manager is appropriate 
         * for applications that use a single Hibernate SessionFactory for transactional data access,
         * 
         * @return HibernateTransactionManager (Create new transaction of the session)
         * @see <a href="https://docs.spring.io/spring-framework/docs/3.2.5.RELEASE/javadoc-api/org/springframework/orm/hibernate3/HibernateTransactionManager.html"> HibernateTransactionManager </a> 
         */
	@Bean
        public HibernateTransactionManager txManager() {
            return new HibernateTransactionManager(sessionFactory());
        }
}
