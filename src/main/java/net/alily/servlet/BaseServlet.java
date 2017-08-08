package net.alily.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.alily.factory.BeanFactory;

import net.alily.service.IAdminService;
import net.alily.service.IUserService;

import net.alily.service.impl.AdminServiceImpl;
import net.alily.service.impl.UserServiceImpl;
import net.alily.utils.WebUtils;

/**
 * 项目中通用的Servlet，希望所有的servlet都继承此类
 * 
 * @author Mr.Zhao
 *
 */
public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 动态创建Service
	protected IUserService userService = BeanFactory.getInstance("userService", UserServiceImpl.class);
	protected IAdminService adminService = BeanFactory.getInstance("adminService", AdminServiceImpl.class);
	protected HttpSession session;

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (保存跳转的资源) 方法返回值
		Object uri = null;

		// 获取操作类型; 【约定 > 俗成： 操作类型的值，必须对应servlet中的方法名称】
		String methodName = request.getParameter("method");

		try {
			// 1. 获取当前运行类的字节码
			@SuppressWarnings("rawtypes")
			Class clazz = this.getClass();
			// 2. 获取当前执行的方法的Method类型
			@SuppressWarnings("unchecked")
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 3. 执行方法
			uri = method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}

		// 跳转
		WebUtils.goTo(request, response, uri);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
