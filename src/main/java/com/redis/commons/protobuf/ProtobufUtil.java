package com.redis.commons.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.redis.commons.exception.ProtobufDeserializeException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProtobufUtil {
    
    public static <E extends GeneratedMessageV3> E parse(final Parser<E> parser, final byte[] redisData) {
        try {
            return parser.parseFrom(redisData);
        } catch (final InvalidProtocolBufferException e) {
            throw new ProtobufDeserializeException("Erro ao converter dados do cache", e);
        }
    }
}