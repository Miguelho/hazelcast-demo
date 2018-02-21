package org.miguelhalys.test.hazelcast.server;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.query.impl.getters.ReflectionHelper;

public abstract class HazelcastTestBase {
	
	
	protected void __GIVEN(String message){
		System.out.println();
		
		System.out.println(" [GIVEN]  ");
		System.out.println(message);
		
		System.out.println();
	}
	
	protected void __WHEN(String message){
		System.out.println();
		
		System.out.println(" [WHEN]  ");
		System.out.println(message);
		
		System.out.println();
	}
	
	protected void __THEN(String message){
		System.out.println();
		
		System.out.println(" [THEN]  ");
		System.out.println(message);
		
		System.out.println();
	}
	
	@BeforeClass
	public static void printStart(){
		System.out.println("***************************");
		System.out.println("**   TEST IS STARTING    **");
		System.out.println("***************************");
	}
	
	@AfterClass
	public static void tearUp(){
		Hazelcast.shutdownAll();
	}
	
	

}
