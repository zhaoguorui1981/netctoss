package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.CostDao;
import entity.Admin;
import entity.Cost;

public class MainServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		if("/findCost.do".equals(path)) {
			//��ѯ�����ʷ�
			findCost(req, res);
		} else if("/toAddCost.do".equals(path)) {
			//�������ʷ�ҳ
			toAddCost(req, res);
		} else if("/addCost.do".equals(path)) {
			//�����ʷ�
			addCost(req, res);
		} else if("/toUpdateCost.do".equals(path)) {
			//���޸��ʷ�ҳ
			toUpdateCost(req, res);
		} else if("/toLogin.do".equals(path)) {
			//�򿪵�¼ҳ
			toLogin(req, res);
		} else if("/toIndex.do".equals(path)) {
			//����ҳ
			toIndex(req, res);
		} else if("/login.do".equals(path)) {
			//��¼
			login(req, res);
		} else if("/logout.do".equals(path)){
			logout(req,res);
		}else {
			throw new RuntimeException(
				"��Ч�ķ���·��.");
		}
	}
	
	protected void login(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//���ձ�����
		String adminCode = 
			req.getParameter("adminCode");
		String password = 
			req.getParameter("password");
		//��֤�˺�����
		AdminDao dao = new AdminDao();
		Admin admin = dao.findByCode(adminCode);
		if(admin == null) {
			//�˺Ŵ���ת������¼ҳ
			req.setAttribute("error", "�˺Ŵ���");
			req.getRequestDispatcher(
				"WEB-INF/main/login.jsp")
				.forward(req, res);
		} else if(!admin.getPassword().equals(password)) {
			//�������ת������¼ҳ
			req.setAttribute("error", "�������");
			req.getRequestDispatcher(
				"WEB-INF/main/login.jsp")
				.forward(req, res);
		} else {
			Cookie cookie=new Cookie("adminCode",adminCode);
			res.addCookie(cookie);
			//��¼�ɹ����ض�����ҳ
			HttpSession session=req.getSession();
			session.setAttribute("adminCode", adminCode);
			res.sendRedirect("toIndex.do");
		}
	}
	
	protected void toIndex(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher(
			"WEB-INF/main/index.jsp")
			.forward(req, res);
	}
	
	protected void toLogin(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher(
			"WEB-INF/main/login.jsp")
			.forward(req, res);
	}
	
	protected void toUpdateCost(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//���մ���Ĳ���
		String id = req.getParameter("id");
		//��ѯҪ�޸ĵ�����
		CostDao dao = new CostDao();
		Cost cost = 
			dao.findById(new Integer(id));
		//ת�����޸�ҳ��
		req.setAttribute("cost", cost);
		req.getRequestDispatcher(
			"WEB-INF/cost/update.jsp")
			.forward(req, res);
	}
	
	protected void addCost(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//���ձ�����
		String name = req.getParameter("name");
		String baseDuration = 
			req.getParameter("baseDuration");
		String baseCost = 
			req.getParameter("baseCost");
		String unitCost = 
			req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		String costType = req.getParameter("costType");
		//�洢����
		Cost c = new Cost();
		c.setName(name);
		if(baseDuration != null
			&& !baseDuration.equals("")) {
			c.setBaseDuration(
				new Integer(baseDuration));
		}
		if(baseCost != null
			&& !baseCost.equals("")) {
			c.setBaseCost(
				new Double(baseCost));
		}
		if(unitCost != null
			&& !unitCost.equals("")) {
			c.setUnitCost(
				new Double(unitCost));
		}
		c.setDescr(descr);
		c.setCostType(costType);
		CostDao dao = new CostDao();
		dao.save(c);
		//�ض��򵽲�ѯ����
		//��ǰ��/netctoss/addCost.do
		//Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}
	
	protected void toAddCost(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//ת��������ҳ��
		//��ǰ��/netctoss/toAddCost.do
		//Ŀ�꣺/netctoss/WEB-INF/cost/add.jsp
		req.getRequestDispatcher(
			"WEB-INF/cost/add.jsp")
			.forward(req, res);
	}
	
	protected void findCost(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//��ѯ���е��ʷ�
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		//ת������ѯҳ��
		//��ǰ��/netctoss/findCost.do
		//Ŀ�꣺/netctoss/WEB-INF/cost/find.jsp
		req.setAttribute("costs", list);
		req.getRequestDispatcher(
			"WEB-INF/cost/find.jsp")
			.forward(req, res);
	}
	protected void logout(
			HttpServletRequest req, 
			HttpServletResponse res) throws ServletException, IOException {
		req.getSession().invalidate();
		res.sendRedirect("toLogin.do");
	}
		
}







