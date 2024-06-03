package springDemo.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDemo.dao.UserDao;
import springDemo.entity.User;

@Service
public class LoginService {
    @Autowired
    HttpSession session;
    @Autowired
    UserDao userDao;
    public void login(String username,String password){
        if(username != null && password != null){
            User user = userDao.findUserByUsername(username);
            if(user != null){
                if(user.getPassword().equals(password)){
                    session.setAttribute("user",user);
                    session.setAttribute("tips","");
                    return;
                }
                session.setAttribute("tips","PASSWORD_ERROR");
                return;
            }
            session.setAttribute("tips","USER_EXIST");
            return;
        }
        session.setAttribute("tips","USER_OR_PASSWORD_EMPTY");
    }

}
