package io.github.bum752.springmethodargumentresolverexample.after;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AfterController {

  @GetMapping("/after")
  public Integer getUserId(@SessionIdToUserId Integer userId) {
    return userId;
  }
}
