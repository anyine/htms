package cn.wizool.htms.iwebutil;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class DatabaseNameGenerator extends ImprovedNamingStrategy implements
		NamingStrategy {

	private static final long serialVersionUID = 1L;

	private String prefix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String columnName(String columnName) {

		return addUnderscores(columnName).toUpperCase();

	}

	@Override
	public String tableName(String tableName) {

		return this.getPrefix() + addUnderscores(tableName).toUpperCase();

	}
}
