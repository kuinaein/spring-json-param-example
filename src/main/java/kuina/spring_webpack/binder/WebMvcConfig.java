package kuina.spring_webpack.binder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	static final String HANDLER_KEY = "kuina.handler";

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MethodDefInterseptor());
	}

	private static class MethodDefInterseptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, final Object handler) {
			if (handler instanceof HandlerMethod) {
				request.setAttribute(HANDLER_KEY, handler);
			}
			return true;
		}
	}
}
