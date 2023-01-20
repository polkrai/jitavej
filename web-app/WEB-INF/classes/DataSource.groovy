dataSource {
	pooled = true
	jmxExport = true
	driverClassName = "org.postgresql.Driver"
	dialect = "org.hibernate.dialect.PostgreSQLDialect"
	username = "neural"
	password = "1q2w3e4r"
	properties {
		jmxEnabled = true
		initialSize = 4
		maxActive = 4
		minIdle = 2
		maxIdle = 2
		maxWait = 10000
		maxAge = 60000
		removeAbandonedTimeout = 60
		removeAbandoned = true
		logAbandoned = true
		timeBetweenEvictionRunsMillis = 30000
		minEvictableIdleTimeMillis = 60000
		validationQuery = "SELECT 1"
		validationQueryTimeout = 3
		validationInterval = 15000
		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false
		jdbcInterceptors = "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer"
		defaultTransactionIsolation = 2
	}
}

hibernate {

    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'com.opensymphony.oscache.hibernate.OSCacheProvider'
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory'
    singleSession = true
    flush.mode = "manual"
}

//environment specific settings

environments {

	test {
		
		dataSource {						
			//url = "jdbc:postgresql://192.168.44.30:5432/jvkk_2"
			url = "jdbc:postgresql://localhost:5432/jvkk_2"	
		}
	}
	
	development {
		
		dataSource {
			//url = "jdbc:postgresql://192.168.44.30:5432/jvkk_2"
			url = "jdbc:postgresql://localhost:5432/jvkk_2"
		}
	}
	
	production {
		
		dataSource {
			//url = "jdbc:postgresql://192.168.44.30:5432/jvkk_2"
			url = "jdbc:postgresql://localhost:5432/jvkk_2"
		}
	}
}