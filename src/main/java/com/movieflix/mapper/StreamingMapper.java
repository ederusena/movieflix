package com.movieflix.mapper;

import com.movieflix.dto.StreamingRequest;
import com.movieflix.dto.StreamingResponse;
import com.movieflix.model.Streaming;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {
    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }
}
