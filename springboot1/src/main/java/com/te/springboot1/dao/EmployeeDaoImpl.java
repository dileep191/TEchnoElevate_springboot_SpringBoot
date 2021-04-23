package com.te.springboot1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.te.springboot1.beans.EmployeeBean;
import com.te.springboot1.customexceptions.EmployeeException;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@PersistenceUnit
	EntityManagerFactory facory;
	
	@Override
	public EmployeeBean authenticate(int id, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeBean getEmployee(int id) {
		
		EntityManager manager = facory.createEntityManager();
		try {
			EmployeeBean bean = manager.find(EmployeeBean.class, id);

			if (bean != null) {
				return bean;
			} else {
				System.out.println("employee not found ");
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} 
	}//end of getemp

	@Override
	public boolean deleteEmployee(int id) {
		
		EntityManager manager = facory.createEntityManager();

		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();

		EmployeeBean bean = manager.find(EmployeeBean.class, id);
		if (bean != null) {
			manager.remove(bean);
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}//end of deleteemp

	@Override
	public List<EmployeeBean> getAllData() {
		EntityManager manager = facory.createEntityManager();

		String query = "from EmployeeBean";

		javax.persistence.Query query2 = manager.createQuery(query);

		List<EmployeeBean> list = query2.getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
		EntityManager manager = facory.createEntityManager();

		EntityTransaction transaction = manager.getTransaction();

		try {
			

			transaction.begin();
			
			manager.persist(bean);

			boolean isadded = false;

			if (bean!=null) {
				isadded = true;
				transaction.commit();
				return isadded;
			} else {
				isadded = false;
				throw new EmployeeException("employee details already present");
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new EmployeeException("employee details already present");
		}
	}// end of addEmployee method
		
	

	@Override
	public boolean updateEmployee(EmployeeBean bean) {
EntityManager manager = facory.createEntityManager();
		
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();
			
			EmployeeBean info = manager.find(EmployeeBean.class, bean.getId());
			
			
			if(bean.getName()!=null && bean.getName()!="") {
				info.setName(bean.getName());
			}
			
			if(bean.getBirthdate()!=null) {
				info.setBirthdate(bean.getBirthdate());
			}
			
			if(bean.getPassword()!=null && bean.getPassword()!="") {
				info.setPassword(bean.getPassword());
			}
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}//end of updateemp

}
