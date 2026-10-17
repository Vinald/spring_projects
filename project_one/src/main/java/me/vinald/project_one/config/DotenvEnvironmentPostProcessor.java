package me.vinald.project_one.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads a ".env" file (if present) from the working directory into the Spring
 * Environment, so values such as JWT_SECRET and DB credentials can be kept
 * out of application.properties and out of version control.
 */
public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor {

	private static final String DOTENV_FILE = ".env";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		Path envFile = Path.of(DOTENV_FILE);
		if (!Files.exists(envFile)) {
			return;
		}

		Map<String, Object> values = new LinkedHashMap<>();
		try {
			List<String> lines = Files.readAllLines(envFile);
			for (String line : lines) {
				String trimmed = line.trim();
				if (trimmed.isEmpty() || trimmed.startsWith("#")) {
					continue;
				}
				int separatorIndex = trimmed.indexOf('=');
				if (separatorIndex < 0) {
					continue;
				}
				String key = trimmed.substring(0, separatorIndex).trim();
				String value = trimmed.substring(separatorIndex + 1).trim();
				if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
					value = value.substring(1, value.length() - 1);
				}
				values.put(key, value);
			}
		} catch (IOException ex) {
			throw new IllegalStateException("Failed to read " + DOTENV_FILE, ex);
		}

		if (!values.isEmpty()) {
			environment.getPropertySources().addLast(new MapPropertySource("dotenv", values));
		}
	}
}
