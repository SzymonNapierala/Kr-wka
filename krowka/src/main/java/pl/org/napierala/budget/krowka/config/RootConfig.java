package pl.org.napierala.budget.krowka.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {
		"pl.org.napierala.budget.krowka.service"
})
@Import(value = {
		MvcConfig.class,
		SecurityConfig.class
})
@ImportResource(value = "classpath:/spring/data-context.xml")
public class RootConfig {

}
