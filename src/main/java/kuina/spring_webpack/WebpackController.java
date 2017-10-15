package kuina.spring_webpack;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/foo")
public class WebpackController {
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "Spring Index");
		model.addAttribute("foo", Arrays.asList("index", "&amp;", ">", "]]>", "</script>"));
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("title", "Spring Create");
		model.addAttribute("foo", Arrays.asList("create", "&amp;", ">", "]]>", "</script>"));
		return "a/create";
	}
}
