package org.miguelhalys.test.hazelcast.queue;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.miguelhalys.test.hazelcast.server.HazelcastTestBase;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.miguelhalys.hazelcast.queue.SimpleConsumer;
import com.miguelhalys.hazelcast.queue.SimpleProducer;

public class QueueTest extends HazelcastTestBase {

	private static final String HZ_CONFIG_FILE_NAME = "hazelcast-client.xml";
	private static final String HZ_CONFIG_FILE_NAME_2 = "hazelcast-client2.xml";
	static ClientConfig consumerConfig;
	static ClientConfig consumerConfig_2;
	static ExecutorService executorService;
	
	@BeforeClass
	public static void setsUp() throws IOException{
		consumerConfig = new XmlClientConfigBuilder(HZ_CONFIG_FILE_NAME).build();
		consumerConfig = new XmlClientConfigBuilder(HZ_CONFIG_FILE_NAME_2).build();
		executorService = Executors.newFixedThreadPool(3);
	}
	
	
	@Test
	public void consumerShouldUseProducer() throws InterruptedException{
		__GIVEN("A producer and a consumer");
		SimpleProducer producer = new SimpleProducer();//Config file is gotten directly from classpath
		SimpleConsumer consumer = new SimpleConsumer(consumerConfig, "Consumer1");
		SimpleConsumer consumer2 = new SimpleConsumer(consumerConfig_2, "Consumer2");
		
		__WHEN("They start operating");
		executorService.submit( () -> producer.start());
		executorService.submit( () -> consumer.start());
		executorService.submit( () -> consumer2.start());
		__THEN("The action must be displayed on console");
		
		executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
		Hazelcast.shutdownAll();
	}
	
}
