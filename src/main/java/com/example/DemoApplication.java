package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.data.Person;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class); 
	 
	private static Cluster cluster; 
	private static Session session;
	 
//	@Autowired
//	@Qualifier("myTemplateBeanId")
//	private CassandraOperations cassandraOperations;
	 
	public static void main(String[] args) {
		try { 
			 
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build(); 
			 
			session = cluster.connect("mykeyspace"); 
			 
			CassandraOperations cassandraOps = new CassandraTemplate(session); 
			 
			cassandraOps.insert(new Person("1234567890", "David", 40)); 
			 
			Select s = QueryBuilder.select().from("person"); 
			s.where(QueryBuilder.eq("id", "1234567890")); 
	
			LOG.info(cassandraOps.queryForObject(s, Person.class).getId()); 
	
			cassandraOps.truncate("person"); 
	
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		SpringApplication.run(DemoApplication.class, args);
	}
}
