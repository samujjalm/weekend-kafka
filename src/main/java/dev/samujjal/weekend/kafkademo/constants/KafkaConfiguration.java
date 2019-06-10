package dev.samujjal.weekend.kafkademo.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "mykafka")
public class KafkaConfiguration {
    private String bootstrapServers;
}
