package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.example" }, useDefaultFilters = false, 
//includeFilters = {  @Filter(type = FilterType.CUSTOM, value = BeanTypeFilter.class) })
public class DemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class); 
	
	 
//	@Autowired
//	@Qualifier("myTemplateBeanId")
	 
	public static void main(String[] args) {
//		try { 

//			Cluster cluster = Cluster.builder()
//			        .addContactPoints("127.0.0.1")
//			        // by default, statements will be considered non-idempotent
//			        .withQueryOptions(new QueryOptions().setDefaultIdempotence(false))
//			        // make your retry policy idempotence-aware
//			        //.withRetryPolicy(new IdempotenceAwareRetryPolicy(DefaultRetryPolicy.INSTANCE))
//			        .build();
//非同期での接続			 
//			ListenableFuture<Session> session = cluster.connectAsync("mykeyspace");
//			session.get().execute("INSERT INTO users (KEY, age) VALUES ('test', 1)");
//			ResultSet res = session.get().execute("SELECT * FROM users");
			
//			Session session = cluster.connect("mykeyspace");
//			session.execute("INSERT INTO users (name, age) VALUES ('test', 2)");
//			ResultSet res = session.execute("SELECT * FROM users");
//		
//			List<Row> rows = res.all();
//			for (Row row : rows) {
//				String key = row.getString("KEY");
//				int age = row.getInt("age");
//				LOG.info("KEY:" + key + ", " + "age:" + age);
//			}
			 
			// by default, statements like this one will not be retried
			//session.execute("INSERT INTO user (KEY, age) VALUES ('test', 1)");
			
			 
			// but this one will
			//session.execute("");
	
			//LOG.info(cassandraOps.queryForObject(s, Person.class).getId()); 
			//cassandraOperations.
	
			//cassandraOps.truncate("person");
	
//		} catch (Exception e) {
//			e.printStackTrace(); 
//		} 
		
		SpringApplication.run(DemoApplication.class, args);
	}
}
