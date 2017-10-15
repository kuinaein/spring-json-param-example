package kuina.spring_webpack;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/foo")
@RequiredArgsConstructor
public class WebpackController {
	private final ObjectMapper objectMapper;

	@GetMapping("/")
	public String index(Model model) throws IOException {
		model.addAttribute("title", "Spring Index");
		final String fooJson = this.objectMapper.writeValueAsString(
				Arrays.asList("index", "&amp;", ">", "]]>", "</script>", Optional.of(LocalDateTime.now())));
		model.addAttribute("foo", fooJson);
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) throws IOException {
		model.addAttribute("title", "Spring Create");
		final String fooJson = this.objectMapper.writeValueAsString(
				Arrays.asList("index", "&amp;", ">", "]]>", "</script>", Optional.of(LocalDateTime.now())));
		model.addAttribute("foo", fooJson);
		return "a/create";
	}
}
