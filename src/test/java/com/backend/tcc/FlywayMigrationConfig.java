package com.backend.tcc;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayMigrationConfig
{
	@Bean
	public static FlywayMigrationStrategy cleanMigrateStrategy()
	{
		return flyway ->
		{
			flyway.clean();
			flyway.migrate();
		};
	}
}