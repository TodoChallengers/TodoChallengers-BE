package TodoChallengers.BE.config;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.ByteBuffer;
import java.util.UUID;

@WritingConverter
public class WritingUUIDConverter implements Converter<UUID, Binary> {
    @Override
    public Binary convert(UUID source) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(source.getMostSignificantBits());
        byteBuffer.putLong(source.getLeastSignificantBits());
        return new Binary(byteBuffer.array());
    }
}