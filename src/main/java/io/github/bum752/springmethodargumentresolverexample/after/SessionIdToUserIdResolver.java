package io.github.bum752.springmethodargumentresolverexample.after;

import io.github.bum752.springmethodargumentresolverexample.session.SessionRepository;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class SessionIdToUserIdResolver implements HandlerMethodArgumentResolver {

  private final SessionRepository sessionRepository;

  public SessionIdToUserIdResolver(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Integer.class.equals(parameter.getGenericParameterType());
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    SessionIdToUserId annotation = parameter.getParameterAnnotation(SessionIdToUserId.class);

    assert annotation != null;

    String sessionIdHeaderName = annotation.value();
    String sessionId = webRequest.getHeader(sessionIdHeaderName);

    return sessionRepository.getUserIdBy(sessionId);
  }
}
