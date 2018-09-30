
//reasobale login and logout 
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Domain.User;
import Domain.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
/**
 *
 * @author 707114
 */
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            HttpSession session = request.getSession();
            String logout = request.getParameter("logout");
            
            if (logout != null)
            {
                session.removeAttribute("user");
                request.setAttribute(logout, this);
            }
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie: cookies)
            {
                if(cookie.getName().equals("betty") ||cookie.getName().equals("adam") ){
                request.setAttribute("username", cookie.getName());
                request.setAttribute("check", true);
                }
            }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
//             getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
            String url = "/Login.jsp";
            
            String username = request.getParameter("name");
            String password = request.getParameter("pass");
            UserService userService = new UserService();
            User user = userService.login(username, password);
            
            HttpSession session = request.getSession();            
           
            if (user !=null)
            {
                response.sendRedirect("home");
                session.setAttribute("user", user.getUsername());
                return;
            }
               getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);       

            if (request.getParameter("remember").equals(true))
                {
                    Cookie cookie = new Cookie(username, session.getId());
                    response.addCookie(cookie);
                }
    }
}
