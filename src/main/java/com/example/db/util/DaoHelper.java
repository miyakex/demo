package com.example.db.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.example.db.interfaces.DBColumns;
import com.example.db.interfaces.DBEntity;

public class DaoHelper<E extends DBColumns<T>, T extends DBEntity> {

	private final String tableName;
	private final  Class<E> col;
	private final QueryBuilder queryBuilder;
	private final Session session;
	
	public DaoHelper(String tableName, Class<E> col) {
		this.tableName = tableName;
		this.col = col;
		Cluster cluster = Cluster.builder()
		        .addContactPoints("127.0.0.1")
		        .withQueryOptions(new QueryOptions().setDefaultIdempotence(false))
		        .build();
		this.queryBuilder = new QueryBuilder(cluster);
		this.session = cluster.connect("mykeyspace");
		//TODO closeはどうしようか
	}
	
	public List<T> selectAll(Supplier<T> newInstance) {
		String[] cols = Arrays.stream(col.getEnumConstants())
				.map(DBColumns::name)
				.toArray(String[]::new);
		
		Statement statement = queryBuilder.select(cols).from("mykeyspace", tableName);
		ResultSet res = session.execute(statement);
		List<T> result = new ArrayList<>();
		for (Row row : res) {
			T entity = newInstance.get();
			Arrays.stream(col.getEnumConstants())
			.forEach(c -> c.bind(entity, row));
			result.add(entity);
		}
		return result;
	}
	
	public void insert(T entity) {
		E[] cols = col.getEnumConstants();
		Insert insert = queryBuilder.insertInto(tableName);
		Arrays.stream(cols)
		.forEach(col -> insert.value(col.name(), col.getValueSupplier().getValue(entity)));
		session.execute(insert);
	}
}
