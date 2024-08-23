package TodoChallengers.BE.config;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.UUID;

@ReadingConverter
public class ReadingUUIDConverter implements Converter<Binary, UUID> {
    @Override
    public UUID convert(Binary source) {
        return UUID.nameUUIDFromBytes(source.getData());
    }
}

