package com.miguelhalys.hazelcast.mapreduce;

import java.util.StringTokenizer;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class TokenizerMapper
        implements Mapper<String, String, String, Long> {

    private static final Long ONE = Long.valueOf(1L);

    @Override

    public void map(String key, String document, Context<String, Long> context) {
        // Just splitting the text by whitespaces
        StringTokenizer tokenizer = new StringTokenizer(document.toLowerCase());

        // For every token in the text (=> per word)
        while (tokenizer.hasMoreTokens()) {
            // Emit a new value in the mapped results
            context.emit(tokenizer.nextToken(), ONE);
        }
    }

}