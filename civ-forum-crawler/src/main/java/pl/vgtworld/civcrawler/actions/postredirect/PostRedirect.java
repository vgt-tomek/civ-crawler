package pl.vgtworld.civcrawler.actions.postredirect;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.entities.Post;
import pl.vgtworld.civcrawler.services.PostsService;

@WebServlet("/post-redirect")
public class PostRedirect extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostRedirect.class);
	
	@Inject
	private PostsService postsService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String messageIdentifier = req.getParameter("messageId");
		int messageId = parseMessageId(messageIdentifier);
		Post post = postsService.findById(messageId);
		if (post == null) {
			//TODO Display proper error page.
			return;
		}
		
		resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		resp.setHeader("Location", "http://forums.civ.org.pl/misc.php?action=gotomsg&MsgID=" + messageId);
	}

	private int parseMessageId(String messageIdentifier) {
		try {
			return Integer.parseInt(messageIdentifier);
		} catch (NumberFormatException e) {
			LOGGER.debug("Invalid messageId: {}", messageIdentifier);
			return 0;
		}
	}
}
