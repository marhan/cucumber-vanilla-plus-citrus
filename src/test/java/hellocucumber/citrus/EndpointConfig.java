package hellocucumber.citrus;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.context.annotation.Bean;

public class EndpointConfig {

    @Bean
    public HttpClient todoListClient() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://todo-app.paas.consol.de")
                .build();
    }
}
