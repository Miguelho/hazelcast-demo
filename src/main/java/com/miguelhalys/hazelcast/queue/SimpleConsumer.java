package com.miguelhalys.hazelcast.queue;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class SimpleConsumer {

	private static final String QUEUE_NAME = "queue";
	ClientConfig config;
	private String name;
	private final HazelcastInstance _instance;
	

	public SimpleConsumer(ClientConfig consumerConfig) {
		_instance = HazelcastClient.newHazelcastClient(this.config);
	}

	public SimpleConsumer(ClientConfig consumerConfig, String name) {
		_instance = HazelcastClient.newHazelcastClient(this.config);
		this.name=name;
	}

	public void start() {
		IQueue<Integer> queue = _instance.getQueue(QUEUE_NAME);

		try {
			while (true) {

				int item = queue.take();
				System.out.println(String.format("%s consumed: " + item, this.name));
				if (item == -1) {
					queue.put(-1);
					break;
				}
				Thread.sleep(5000);
			}
			System.out.println("Consumer Finished!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stop() {
		_instance.shutdown();
	}

}
