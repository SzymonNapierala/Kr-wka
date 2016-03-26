package pl.org.napierala.budget.krowka.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
public class InternationalizationAndLocalizationConfig {

	@Bean
	public LocaleResolver localeResolver(){
		LocaleResolver localeResolver = new FixedLocaleResolver(new Locale("pl"));
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/app");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
