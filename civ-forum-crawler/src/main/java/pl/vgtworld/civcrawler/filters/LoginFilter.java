package pl.vgtworld.civcrawler.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.services.UsersService;

@WebFilter(urlPatterns={"/*"})
public class LoginFilter implements Filter {
	
	@Inject
	UsersService usersService;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
		throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Cookie[] cookies = httpRequest.getCookies();
		
		Cookie userCookie = findCookie(cookies, "user");
		Cookie tokenCookie = findCookie(cookies, "token");
		
		if (userCookie != null && tokenCookie != null) {
			User validatedUser = usersService.validateLoginCookies(userCookie, tokenCookie);
			request.setAttribute("user", validatedUser);
		}
		
		filterChain.doFilter(request, response);
	}

	private Cookie findCookie(Cookie[] cookies, String name) {
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}
}
