package br.com.pcsist.demo.produto;

import static org.mockito.Mockito.when;

import javax.sql.DataSource;

import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pcsist.winthor.core.test.AbstractShiroTest;

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestTestConfig.class })
public abstract class AbstractResourceTest extends AbstractShiroTest {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private DataSource dataSource;
  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  public DataSource dataSource() {
    return dataSource;
  }

  @Before
  public void initMock() {
    Subject subject = Mockito.mock(Subject.class);
    setSubject(subject);

    when(subject.isAuthenticated()).thenReturn(true);

    mockMvc = MockMvcBuilders.webAppContextSetup(context()).build();
  }

  public WebApplicationContext context() {
    return context;
  }

  protected MockMvc mockMvc() {
    return mockMvc;
  }

  protected String json(Object dto) throws JsonProcessingException {
    return objectMapper.writeValueAsString(dto);
  }

}
