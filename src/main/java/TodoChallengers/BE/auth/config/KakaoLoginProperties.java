package TodoChallengers.BE.auth.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Builder
@ConfigurationProperties(prefix = "oauth2.kakao")
@AllArgsConstructor
public class KakaoLoginProperties {
    private final String clientId;
    private final String redirectUri;
    private final String tokenUri;
    private final String metadataUri;
    private final String publicKeyUri;
}