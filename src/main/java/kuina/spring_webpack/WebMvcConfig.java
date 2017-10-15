package kuina.spring_webpack;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> c : converters) {
			if (!(c instanceof MappingJackson2HttpMessageConverter)) {
				continue;
			}
			MappingJackson2HttpMessageConverter jc = (MappingJackson2HttpMessageConverter) c;
			ObjectMapper om = jc.getObjectMapper();
			om.getFactory().setCharacterEscapes(new SlashEscapes());
		}
	}
}
