package kuina.spring_webpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/foo")
public class WebpackController {
	@GetMapping("/")
	public String index() {
		return "compiled/index";
	}
	
	@GetMapping("/create")
	public String create() {
		return "compiled/a/create";
	}
}
