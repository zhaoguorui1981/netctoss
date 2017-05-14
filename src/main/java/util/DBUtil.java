package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * ʹ�õ�ǰ�����������ݿ�����
 * @author Administrator
 *
 */
public class DBUtil {
	/*
	 * ThreadLocal�ڲ���һ��Map
	 * key��ĳ���̣߳���value��Ҫ�����ֵ��
	 * �������Խ�ĳ��ֵ�󶨵�һ���߳��ϡ���ʱͨ��
	 * ���̻߳�ȡ���ֵ��
	 */
	private static ThreadLocal<Connection> tl;
	/*
	 * ���ݿ����ӳ�
	 */
	private static BasicDataSource ds;
	
	static{
		try {
			tl = new ThreadLocal();
			
			//���������ļ�
			Properties prop = new Properties();
			prop.load(DBUtil.class.getClassLoader()
				.getResourceAsStream("config.properties"));
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			//���ӳص����������
			int maxActive = Integer.parseInt(
				prop.getProperty("maxActive")
			);
			//��û�п�������ʱ�����ȴ�ʱ��
			int maxWait = Integer.parseInt(
				prop.getProperty("maxWait")	
			);
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			System.out.println(maxActive);
			System.out.println(maxWait);
			
			//��ʼ�����ӳ�
			ds = new BasicDataSource();
			//Class.forName()
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			//���ӳص����������(�������ӵ��������)
			ds.setMaxActive(maxActive);
			//��ȡ����ʱ�����ȴ�ʱ��
			ds.setMaxWait(maxWait);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡһ�����ݿ�����
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
//		//1��������
//		Class.forName(driver);
//		
//		//2��ȡ����
//		Connection conn 
//			= DriverManager.getConnection(
//				url,username,password
//			);
		/*
		 * �����ӳ�Ҫ���ӡ������ӳ�û�п�������ʱ��
		 * �÷������������״̬������ʱ����"maxWait"
		 * ���õ����ȴ�ʱ��������ڵȴ�������һ����
		 * �������ӣ���ô���ӳػ����̷��ء����ȴ�ʱ��
		 * ������Ȼû�п�������ʱ���÷����׳���ʱ�쳣��
		 */
		Connection conn = ds.getConnection();
		/*
		 * ���������õ�ThreadLocal��
		 * ���õ�ʱ���ǽ����õ�ǰ�������߳� 
		 * ��Ϊkey,set��������Ĳ�����Ϊvalue
		 * ���뵽ThreadLocal�ڲ���Map�б��档
		 */
		tl.set(conn);
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(){
		/*
		 * �ĸ��̵߳���threadlocal��get��������
		 * ������߳���Ϊkeyȥthreadlocal�ڲ���
		 * Map��ȡ����Ӧ��value.
		 * 
		 */
		Connection conn = tl.get();
		if(conn != null){
			try {
				conn.close();
				tl.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConnection();
		System.out.println("��ȡ�ɹ�!");
	}
}






