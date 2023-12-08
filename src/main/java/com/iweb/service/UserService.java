package com.iweb.service;

import com.iweb.dao.UserDao;
import com.iweb.dao.impl.UserDaoImpl;
import com.iweb.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午12:51
 */
public class UserService {
    private final static UserDao USER_DAO = new UserDaoImpl();

    public static boolean register(String username, String password) {
        User user = USER_DAO.findByUsername(username);
        if (user == null) {
            USER_DAO.save(new User(username, password));
            return true;
        }
        return false;
    }

    /**
     * 用户登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户对象，否则返回null
     */
    public static User login(String username, String password) {
        User user = USER_DAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            // 验证成功
            return user;
        } else {
            return null;
        }
    }

    public static User findById(int id) {
        return USER_DAO.findById(id);
    }

    public static boolean update(User user, HttpServletRequest request) {
        String username = request.getParameter("username");
        User u = USER_DAO.findByUsername(username);
        if (u != null && !u.getId().equals(user.getId())) {
            return false;
        }
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        try {
            Part filePart = request.getPart("headSculpture");
            InputStream fileContent = filePart.getInputStream();
            if (fileContent.available() > 0) {
                // 用户上传了头像
                byte[] headSculptureBytes = readInputStream(fileContent);
                user.setHeadSculpture(headSculptureBytes);
            }
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            USER_DAO.update(user);
            return true;
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        return outputStream.toByteArray();
    }
}
