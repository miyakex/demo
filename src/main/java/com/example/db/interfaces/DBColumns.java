package com.example.db.interfaces;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

import com.datastax.driver.core.Row;

public interface DBColumns<T extends DBEntity> {
	String name();
	
	DBColType getColType();
	
	ValueSupplier<T> getValueSupplier();
	
	ValueBinder<T> getValueBinder();
	
	default Object getValue(Row row) {
		switch(getColType()) {
		case INT:
			return row.getInt(name());
		case LONG:
			return row.getLong(name());
		case FLOAT:
			return row.getFloat(name());
		case DOUBLE:
			return row.getDouble(name());
		case STRING:
			return row.getString(name());
		case BOOLEAN:
			return row.getBool(name());
		case DATE:
			return row.getTimestamp(name());
		case DATETIME:
			Date val = row.getTimestamp(name());
			return val == null ? null : new DateTime(val);
		case BIGDECIMAL:
			String val1 = row.getString(name());
			return val1 == null ? BigDecimal.ZERO : new BigDecimal(val1);
		case ENUM:
			return row.getString(name());
		case JSON:
			return row.getString(name());
		default:
			throw new UnsupportedOperationException();
		
		}
	}
	
	default void bind(T entity, Row row) {
		getValueBinder().bind(entity, getValue(row));
	}
	
	@FunctionalInterface
	public interface ValueSupplier<T extends DBEntity> {
		Object getValue(T entity);
	}
	@FunctionalInterface
	public interface ValueBinder<T extends DBEntity> {
		void bind(T entity, Object obj);
	}
	
	public enum DBColType {
		INT,
		LONG,
		FLOAT,
		DOUBLE,
		STRING,
		BOOLEAN,
		DATE,
		DATETIME,
		BIGDECIMAL,
		ENUM,
		JSON,
		;
	}
	
}
