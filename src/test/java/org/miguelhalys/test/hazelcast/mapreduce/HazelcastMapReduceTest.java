package org.miguelhalys.test.hazelcast.mapreduce;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import com.miguelhalys.hazelcast.mapreduce.TokenizerMapper;
import com.miguelhalys.hazelcast.mapreduce.WordCountCombinerFactory;
import com.miguelhalys.hazelcast.mapreduce.WordCountReducerFactory;


public class HazelcastMapReduceTest {
	
	private static final String MAP_NAME = "articles";
	
	static JobTracker jobTracker;
	static HazelcastInstance hazelcastInstance;
	
	private static String testData = "Esto es una prueba prueba 1 1 1";
	
	@BeforeClass
	public static void setUp(){
		hazelcastInstance = Hazelcast.newHazelcastInstance();
		jobTracker= hazelcastInstance.getJobTracker( "default" );
		
		prepareData();
	}
	
	private static void prepareData() {
		hazelcastInstance.getMap(MAP_NAME).put("testData", testData);
		
	}

	@Test
	public void shouldMapReduce() throws InterruptedException, ExecutionException{
		IMap<String, String> map = hazelcastInstance.getMap( MAP_NAME );
		KeyValueSource<String, String> source = KeyValueSource.fromMap( map );
		Job<String, String> job = jobTracker.newJob( source );

		ICompletableFuture<Map<String, Long>> future = job
		    .mapper( new TokenizerMapper() )
		    .combiner( new WordCountCombinerFactory() )
		    .reducer( new WordCountReducerFactory() )
		    .submit();

		// Attach a callback listener
		future.andThen( buildCallback() );

		// Wait and retrieve the result
		Map<String, Long> result = future.get();
		
		result.forEach((k,v) -> {System.out.println(String.format("%s -> %s", k,v));});
	}
	
	private static ExecutionCallback<Map<String, Long>> buildCallback() {
        return new ExecutionCallback<Map<String, Long>>() {
            @Override
            public void onResponse(Map<String, Long> stringLongMap) {
                System.out.println("Calculation finished! :)");
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
    }
}
