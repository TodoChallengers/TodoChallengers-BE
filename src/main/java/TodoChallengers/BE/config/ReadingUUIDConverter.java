package TodoChallengers.BE.config;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.nio.ByteBuffer;
import java.util.UUID;

@ReadingConverter
public class ReadingUUIDConverter implements Converter<Binary, UUID> {
    @Override
    public UUID convert(Binary source) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(source.getData());
        long mostSigBits = byteBuffer.getLong();
        long leastSigBits = byteBuffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }
}

