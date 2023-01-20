dataSource {
	pooled = false
	driverClassName = "org.postgresql.Driver"
	dialect = org.hibernate.dialect.PostgreSQLDialect
	username = "neural"
	password = "1q2w3e4r"
}

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
	//cache.provider_class='org.hibernate.cache.EhCacheProvider'
}

// environment specific settings

environments {

	development {
		dataSource {
			//dbCreate = "none" // one of 'create', 'create-drop','update'
			//url = "jdbc:postgresql://192.168.0.197:5432/jvkk_2"						
			url = "jdbc:postgresql://localhost:5432/jvkk_2"			
		}
	}
	
	test {
		dataSource {
			//dbCreate = "create-drop"
			//url = "jdbc:postgresql://192.168.0.197:5432/jvkk_2"
			url = "jdbc:postgresql://localhost:5432/jvkk_2"
		}
	}
	
	production {
		dataSource {
			//dbCreate = "create-drop"
			//url = "jdbc:postgresql://192.168.0.197:5432/jvkk_2"
			url = "jdbc:postgresql://localhost:5432/jvkk_2"
			
		}
	}
}