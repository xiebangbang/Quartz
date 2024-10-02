package cn.finedo.controller;

import cn.finedo.domain.User;
import cn.finedo.mapper.UserMapper;
import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(username);
        user.setPassword(password);


        if(username  == null || "".equals(username)){
            request.setAttribute("msg","用户名不能为空！");
            request.setAttribute("user",user);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if(password  == null || "".equals(password)){
            request.setAttribute("msg","密码不能为空！");
            request.setAttribute("user",user);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userByName = mapper.getUserByName(username);

        if(userByName ==null || !userByName.getPassword().equals(password.trim())){
            request.setAttribute("msg","登录失败！");
            request.setAttribute("user",user);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user",user);
        response.sendRedirect("index.jsp");
        return;
    }

    public static SqlSession getSqlSession() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        return session;
    }
}
