package seo.dale.practice.spring.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author Dale Seo
 */
public class WebAppInitializer extends AbstractHttpSessionApplicationInitializer {

	public WebAppInitializer() {
		super(HttpSessionConfig.class);
	}
}
