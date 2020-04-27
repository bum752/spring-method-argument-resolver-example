package io.github.bum752.springmethodargumentresolverexample.after;

import io.github.bum752.springmethodargumentresolverexample.session.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(AfterController.class)
@DisplayName("@SessionIdToUserID 어노테이션")
class AfterControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  SessionRepository sessionRepository;

  @Test
  @DisplayName("MethodArgumentResolver 이용해 userId 획득")
  public void test() throws Exception {
    final String sessionId = "MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3";
    final Integer userId = 1001;

    given(sessionRepository.getUserIdBy(sessionId)).willReturn(userId);

    mockMvc
        .perform(
            MockMvcRequestBuilders
                .get("/after")
                .header("session_id", sessionId)
        )
        .andExpect(MockMvcResultMatchers.content().string(String.valueOf(userId)));
  }
}