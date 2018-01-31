package org.miguelhalys.test.hazelcast.server;

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
	
	

}
