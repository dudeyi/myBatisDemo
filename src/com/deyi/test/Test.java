package com.deyi.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.deyi.model.User;

public class Test {
	
	static String resource = "Configuration.xml";
	static SqlSessionFactory sqlSessionFactory ;
	
	static{
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
//		testGetUserById();
//		testGetUserListByName();
//		testAddUser();
//		testUpdateUserById();
		testDeleteUserById();
	}

	/**
	 * ���Բ�ѯ�����ص������������
	 * 
	 */
	public static void testGetUserById() {
		System.out.println("���Բ�ѯ�����ص������������ ");
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			User user = (User) session.selectOne("com.deyi.model.mapping.userMapper.getUserById", 2);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * ���Բ�ѯ�����ض������������
	 */
	public static void testGetUserListByName() {
		System.out.println("���Բ�ѯ�����ض������������ ");
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<User> list = session.selectList("com.deyi.model.mapping.userMapper.getUserListByName", "aa");
			for(User user:list){
				System.out.println(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	/**
	 * ���Բ��루���ʣ����� 
	 */
	public static void testAddUser() {
		System.out.println("���Բ��루���ʣ����� ");
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			User user = new User();
			user.setId(6);
			user.setName("С��");
			user.setAge(20);
			user.setAddress("����");
			session.insert("com.deyi.model.mapping.userMapper.addUser", user);

			User user2 = (User) session.selectOne("com.deyi.model.mapping.userMapper.getUserById", user.getId()); 
			System.out.println(user2);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	/**
	 * ���Ը��£����ʣ�����
	 */
	public static void testUpdateUserById() {
		System.out.println("���Ը��£����ʣ����� ");
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			User user = new User();
			user.setId(3);
			user.setName("С��");
			user.setAge(22);
			user.setAddress("����");
			session.insert("com.deyi.model.mapping.userMapper.updateUserById", user);

			User user2 = (User) session.selectOne("com.deyi.model.mapping.userMapper.getUserById", user.getId()); 
			System.out.println(user2);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	/**
	 * ����ɾ������
	 */
	public static void testDeleteUserById() {
		System.out.println("����ɾ������ ");
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			User user = new User();
			user.setId(1);
			session.insert("com.deyi.model.mapping.userMapper.deleteUserById", user);

			User user2 = (User) session.selectOne("com.deyi.model.mapping.userMapper.getUserById", user.getId());
			System.out.println(user2);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
