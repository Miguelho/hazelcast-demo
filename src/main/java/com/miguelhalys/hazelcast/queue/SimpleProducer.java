package com.miguelhalys.hazelcast.queue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class SimpleProducer {

	private static final String QUEUE_NAME = "queue";
	private final HazelcastInstance _instance;
	
	public SimpleProducer() {
		_instance = Hazelcast.newHazelcastInstance();
	}

	public void start(){
		IQueue<Integer> queue = _instance.getQueue( QUEUE_NAME );
	    try{
	    	for ( int k = 1; k < 100; k++ ) {
	  	      queue.put( k );
	  	      System.out.println( "Producing: " + k );
	  	      Thread.sleep(1000);
	  	    }
	  	    queue.put( -1 );
	  	    System.out.println( "Producer Finished!" );
	    }catch(InterruptedException e){
	    	e.printStackTrace();
	    }
	}
	

	public void stop(){
		_instance.shutdown();
	}

}
