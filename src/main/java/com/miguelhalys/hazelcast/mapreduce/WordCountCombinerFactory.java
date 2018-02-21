package com.miguelhalys.hazelcast.mapreduce;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

public class WordCountCombinerFactory implements CombinerFactory<String, Long, Long> {

	@Override
	public Combiner<Long, Long> newCombiner(String key) {
		// Create a new Combiner for the given key
		return new WordCountCombiner();
	}

	private class WordCountCombiner extends Combiner<Long, Long> {

		private long sum = 0;

		@Override
		public void combine(Long value) {
			// Increment the per chunk sum
			sum++;
		}

		@Override
		public Long finalizeChunk() {
			// Store the current amount
			long chunk = sum;

			// Reset the per chunk sum
			sum = 0;

			// Return the previously stored sum
			return chunk;
		}

	}

}