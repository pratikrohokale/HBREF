package com.nt.test;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.domain.BankAccount;
import com.nt.utility.HibernateUtil;

public class VersionLoadAndUpdateTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		boolean flag=false;
		BankAccount account=null;
		long idVal=0;
		float amount=0;
		long acno=0;
		Scanner sc=null;
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter Account no:");
			acno=sc.nextLong();
			System.out.println("Enter amount::");
			amount=sc.nextFloat();
		}
	
		
        try{		
		//get Session 
		ses=HibernateUtil.getSession();
		//Load object
		account=ses.get(BankAccount.class,acno);
		if(account==null){
			System.out.println("Record not found");
			return;
		}
		 tx=ses.beginTransaction();
		  //modify object
		  account.setBalance(account.getBalance()-amount);
		 flag=true;
			
		}
		catch(HibernateException he){
			flag=false;
			he.printStackTrace();
		}
		catch(Exception e){
			flag=false;
			e.printStackTrace();
		}
		finally{
			if(flag==true){
				tx.commit();
				System.out.println("Money withdrawn");
				System.out.println("Withdraw transaction is done for "+account.getVersion()+" times");
			}
			else{
				tx.rollback();
				System.out.println("Account not found");
			}
		 //close objects
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally
	}//main
}/// class
