package seo.dale.practice.spring.session;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dale Seo
 */
@RestController
public class SessionController {

	@RequestMapping(value = "/rest/session", produces = "application/json")
	public Map<String, Object> session(@RequestParam(required = false) String name, @RequestParam(required = false) String value, HttpServletRequest request) {
		HttpSession session = request.getSession();

		Map<String, Object> result = new HashMap<>();
		result.put("id", session.getId());
		result.put("new", session.isNew());
		result.put("maxInactiveInterval", session.getMaxInactiveInterval());
		result.put("creationTime", new Date(session.getCreationTime()));
		result.put("lastAccessedTime", new Date(session.getLastAccessedTime()));

		if (session.isNew()) {
			session.setAttribute(name, value);
		} else {
			name = (String) session.getAttribute("name");
		}
		result.put("name", name);

		return result;
	}

	@RequestMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
