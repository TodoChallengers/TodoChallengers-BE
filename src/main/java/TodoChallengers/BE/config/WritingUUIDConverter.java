package TodoChallengers.BE.config;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.UUID;

@WritingConverter
public class WritingUUIDConverter implements Converter<UUID, Binary> {
    @Override
    public Binary convert(UUID source) {
        return new Binary(source.toString().getBytes());
    }
}