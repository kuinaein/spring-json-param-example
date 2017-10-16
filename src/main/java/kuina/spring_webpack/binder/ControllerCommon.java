package kuina.spring_webpack.binder;

import java.io.IOException;

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

	@InitBinder
	public void initBinder(WebDataBinder binder, final HttpServletRequest req)
			throws IOException, ReflectiveOperationException {
		this.bindJson(binder, req);
	}

	private void bindJson(WebDataBinder binder, final HttpServletRequest req)
			throws IOException, ReflectiveOperationException {
		final String json = req.getParameter("json");
		if (null == json || json.isEmpty()) {
			return;
		}

		if (null == binder.getTarget() || null == binder.getObjectName()) {
			return; // not our target - bind() will cause an exception
		}

		final HandlerMethod handler = (HandlerMethod) req.getAttribute(BinderWebConfig.HANDLER_KEY);
		if (null == handler || !handler.hasMethodAnnotation(JsonParamModel.class)) {
			return;
		}
		for (final MethodParameter p : handler.getMethodParameters()) {
			final ModelAttribute modelAttr = p.getParameterAnnotation(ModelAttribute.class);
			if (null == modelAttr) {
				return;
			}
			if (!p.getParameterType().equals(binder.getTarget().getClass())) {
				return; // not our target
			}

			final JavaType t = this.objectMapper.constructType(p.getNestedGenericParameterType());
			final Object o = this.objectMapper.readValue(json, t);
			final MutablePropertyValues mpv = new MutablePropertyValues(PropertyUtils.describe(o));
			binder.bind(mpv);
		}
	}
}
