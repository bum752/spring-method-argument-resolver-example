package io.github.bum752.springmethodargumentresolverexample.after;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

  private final SessionIdToUserIdResolver sessionIdToUserIdResolver;

  public WebMvcConfigurerImpl(SessionIdToUserIdResolver sessionIdToUserIdResolver) {
    this.sessionIdToUserIdResolver = sessionIdToUserIdResolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(sessionIdToUserIdResolver);
  }
}
