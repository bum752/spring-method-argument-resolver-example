package io.github.bum752.springmethodargumentresolverexample.after;

import java.util.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class SessionIdToUserIdSwaggerPlugin implements ParameterBuilderPlugin {

  @Override
  public void apply(ParameterContext parameterContext) {
    ResolvedMethodParameter parameter = parameterContext.resolvedMethodParameter();
    Optional<SessionIdToUserId> annotation = parameter.findAnnotation(SessionIdToUserId.class);

    if (annotation.isPresent()) {
      parameterContext
        .requestParameterBuilder()
        .in(ParameterType.HEADER)
        .name("session_id")
        .description("Session ID")
        .required(Boolean.TRUE)
        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
      ;
    }
  }

  @Override
  public boolean supports(DocumentationType delimiter) { // (9)
    return true;
  }
}
