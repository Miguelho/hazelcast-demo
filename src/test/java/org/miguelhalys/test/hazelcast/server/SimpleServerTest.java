package org.miguelhalys.test.hazelcast.server;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SimpleServerTest extends HazelcastTestBase{

	@Ignore
	@Test
	public void shouldBootUp(){
		__GIVEN("A Hazelcast server must boot up without any other configuration.");
		HazelcastInstance hazelcastInstance;
		__WHEN("A HazelcastInstance is required");
		hazelcastInstance = Hazelcast.newHazelcastInstance();
		__THEN("A Hazelcast server is launched with name: " + hazelcastInstance.getName());
		hazelcastInstance.shutdown();
	}
	
	@Test
	public void shouldBootUpTwoInstances(){
		__GIVEN("A Hazelcast cluster is created by instantiating two HazelcastInstances.");
		int numNodes = 2;
		HazelcastInstance hazelcastInstance;
		HazelcastInstance hazelcastInstance_1;
		__WHEN("Two HazelcastInstances are instantiated");
		hazelcastInstance = Hazelcast.newHazelcastInstance();
		hazelcastInstance_1 = Hazelcast.newHazelcastInstance();
		__THEN(String.format("A Hazelcast cluster is formed by %s nodes",numNodes));
		assertEquals(numNodes, hazelcastInstance.getCluster().getMembers().size());
		
		__WHEN("A HZ Node is shutdown");
		try{
			hazelcastInstance_1.shutdown();
		}finally{
			numNodes--;
		}
		
		__THEN(String.format("The Hazelcast cluster is formed by %s nodes",numNodes));
		assertEquals(numNodes, hazelcastInstance.getCluster().getMembers().size());
		hazelcastInstance.shutdown();
		hazelcastInstance_1.shutdown();
	}

}
