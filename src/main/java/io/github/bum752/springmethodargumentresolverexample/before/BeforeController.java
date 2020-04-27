package io.github.bum752.springmethodargumentresolverexample.before;

import io.github.bum752.springmethodargumentresolverexample.session.SessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeforeController {

  private final SessionRepository sessionRepository;

  public BeforeController(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @GetMapping("/before")
  public Integer getUserId(@RequestHeader("session_id") String sessionId) {
    return sessionRepository.getUserIdBy(sessionId);
  }
}
