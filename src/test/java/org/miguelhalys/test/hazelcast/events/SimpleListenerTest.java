package org.miguelhalys.test.hazelcast.events;

import org.junit.Test;
import org.miguelhalys.test.hazelcast.server.HazelcastTestBase;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.listener.EntryAddedListener;

public class SimpleListenerTest extends HazelcastTestBase {

	final static String MAP_NAME = "cats";

	@Test
	public void listenToChanges() {
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
		HazelcastInstance hazelcastInstance_1 = Hazelcast.newHazelcastInstance();

		__GIVEN("A hazelcast cluster formed by 2 nodes, " + "a listener is set and prints whatever event is received.");
		hazelcastInstance.getMap(MAP_NAME).addEntryListener(new EntryAddedListener<Object, Object>() {

			public void entryAdded(EntryEvent<Object, Object> arg0) {
				System.out.println(arg0.toString());

			}

		}, true);

		__WHEN("one node writes to the cats map");

		hazelcastInstance_1.getMap(MAP_NAME).put("Whiskers", "Siberian cat");

		__THEN("The listener is triggered and prints out the event");

	}
}
