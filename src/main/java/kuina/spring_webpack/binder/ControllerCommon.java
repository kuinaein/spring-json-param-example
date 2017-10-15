package kuina.spring_webpack.binder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.HandlerMethod;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerCommon {
	private final ObjectMapper objectMapper;

	private Optional<MethodParameter> getJsonModelParam(final HttpServletRequest req) {
		final HandlerMethod handler = (HandlerMethod) req.getAttribute(WebMvcConfig.HANDLER_KEY);
		if (null == handler || !handler.hasMethodAnnotation(JsonParamModel.class)) {
			return Optional.empty();
		}
		return Arrays.stream(handler.getMethodParameters()).filter(p -> {
			return p.hasParameterAnnotation(ModelAttribute.class);
		}).findFirst();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, final HttpServletRequest req) throws IOException {
		final String json = req.getParameter("json");
		if (null == json || json.isEmpty()) {
			return;
		}

		this.getJsonModelParam(req).ifPresent(p -> {
			final JavaType t = this.objectMapper.constructType(p.getNestedGenericParameterType());
			try {
				final Object o = this.objectMapper.readValue(json, t);
				final MutablePropertyValues mpv = new MutablePropertyValues(PropertyUtils.describe(o));
				binder.bind(mpv);
			} catch (IOException | ReflectiveOperationException ex) {
				throw new RuntimeException(ex);
			}
		});
	}
}
