package kuina.spring_webpack.binder;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BinderController {
	@GetMapping("/")
	public static String pre(Model model) {
		model.addAttribute("foo", new MyForm("", "'\"&amp;</script>]]>"));
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/", produces = "text/plain")
	@JsonParamModel
	@ResponseBody
	// @formatter:off
	public static String post(@ModelAttribute("foo") @Validated
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
	}
}
