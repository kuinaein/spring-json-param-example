package kuina.spring_webpack.binder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BinderController {
	private final ObjectMapper objectMapper;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public Object pre(Model model) throws IOException {
		final MyForm myForm = new MyForm("", "'\"&amp;</script>]]>", Optional.of(LocalDateTime.now()));
		model.addAttribute("foo", this.objectMapper.writeValueAsString(myForm));
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/", produces = "text/plain")
	@JsonParamModel
	@ResponseBody
	// @formatter:off
	public String post(@ModelAttribute("foo") @Validated
			final MyForm fooHolder, final BindingResult bindingResult) {
	// @formatter:on
		return fooHolder.toString() + "\n\n" + bindingResult.toString();
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyForm {
		@Length(min = 1)
		private String baz;

		@Length(min = 1)
		private String cux;

		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
		private Optional<LocalDateTime> datetime;
	}
}
