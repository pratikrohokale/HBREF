package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.domain.EmpDetails;
import com.nt.utility.HibernateUtil;

public class TestClient1 {

	public static void main(String[] args) {
		Session ses=null;
		boolean flag=false;
		EmpDetails details=null;
		int idVal=0;
		//get Session 
		ses=HibernateUtil.getSession();
		
		try{
		 details=(EmpDetails)ses.get(EmpDetails.class,1001);
		 System.out.println(details.getNo()+" "+details.getLname()+" "+details.getFname()+" "+details.getMail());
		}
		catch(HibernateException he){
			he.printStackTrace();
			flag=false;
		}
		catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		finally{
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally
	}//main
}//class
