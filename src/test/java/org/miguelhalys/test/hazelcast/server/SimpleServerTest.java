package org.miguelhalys.test.hazelcast.server;

import org.junit.Test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SimpleServerTest {

	
	@Test
	public void shouldBootUp(){
		__GIVEN("A Hazelcast server must boot up without any other configuration.");
		HazelcastInstance hazelcastInstance;
		__WHEN("A HazelcastInstance is required");
		hazelcastInstance = Hazelcast.newHazelcastInstance();
		__THEN("A Hazelcast server is launched with name: ", hazelcastInstance.getName());
		
		
		
	}
	
	private void __GIVEN(String message){
		System.out.println();
		
		System.out.println(" [GIVEN]  ");
		System.out.println(message);
		
		System.out.println();
	}
	
	private void __WHEN(String message, String... args ){
		System.out.println();
		
		System.out.println(" [WHEN]  ");
		System.out.println(message);
		
		System.out.println();
	}
	
	private void __THEN(String message, String... args ){
		System.out.println();
		
		System.out.println(" [THEN]  ");
		System.out.println(message + args[0]);
		
		System.out.println();
	}
}
