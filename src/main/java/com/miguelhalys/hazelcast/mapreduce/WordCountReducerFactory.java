package com.miguelhalys.hazelcast.mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class WordCountReducerFactory implements ReducerFactory<String, Long, Long> {

	@Override
	public Reducer< Long, Long> newReducer(String key) {
		// Create a new Reducer for the given key
		return new WordCountReducer();
	}

	private class WordCountReducer extends Reducer<Long, Long> {

		private volatile long sum = 0;

		@Override
		public void reduce(Long value) {
			// Just increment the sum by the pre combined chunk value
			sum += value.longValue();
		}

		@Override
		public Long finalizeReduce() {
			// Return the final reduced sum
			return sum;
		}
	}

}