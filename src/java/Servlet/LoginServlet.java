
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
            //when logout is not login in remove the username + password
            if (logout != null)
            {
                session.removeAttribute("name");
                request.setAttribute(logout, this);
            }
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie: cookies)
            {
                if(cookie.getName().equals("betty") ||cookie.getName().equals("adam") ){
                request.setAttribute("name", cookie.getName());
                request.setAttribute("check", true);
                }
            }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserService userService = new UserService();
            User user = userService.login(username, password);
            
            HttpSession session = request.getSession();            
           
            if (user !=null)
            {
                response.sendRedirect("home");
                session.setAttribute("name", user.getUsername());
                return;
            }else{
                request.setAttribute("error", "Please enter the right username and password");
            }
               getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);       

//            if (request.getParameter("remember").equals(true))
        String action = request.getParameter("action");

              if (action.equals("remember"))
                {
                    Cookie usercookie = new Cookie(username, session.getId());
                    Cookie passCookie = new Cookie(password, session.getId());
                    response.addCookie(usercookie);
                    response.addCookie(passCookie);

                } 
        
    }
}
