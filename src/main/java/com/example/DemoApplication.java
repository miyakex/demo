package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.google.common.util.concurrent.ListenableFuture;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class); 
	 
	 
//	@Autowired
//	@Qualifier("myTemplateBeanId")
	 
	public static void main(String[] args) {
		try { 

			Cluster cluster = Cluster.builder()
			        .addContactPoints("127.0.0.1")
			        // by default, statements will be considered non-idempotent
			        .withQueryOptions(new QueryOptions().setDefaultIdempotence(false))
			        // make your retry policy idempotence-aware
			        //.withRetryPolicy(new IdempotenceAwareRetryPolicy(DefaultRetryPolicy.INSTANCE))
			        .build();
			 
			ListenableFuture<Session> session = cluster.connectAsync("mykeyspace");
			session.get().execute("INSERT INTO users (KEY, age) VALUES ('test', 1)");
			 
			// by default, statements like this one will not be retried
			//session.execute("INSERT INTO user (KEY, age) VALUES ('test', 1)");
			
			 
			// but this one will
			//session.execute("");
	
			//LOG.info(cassandraOps.queryForObject(s, Person.class).getId()); 
			//cassandraOperations.
	
			//cassandraOps.truncate("person");
	
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		
		SpringApplication.run(DemoApplication.class, args);
	}
}
