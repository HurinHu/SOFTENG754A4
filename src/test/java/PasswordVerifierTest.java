import org.junit.Test;
import org.mockito.*;
import static org.junit.Assert.*;

public class PasswordVerifierTest {

  @Test
  public void testPasswordMeetsMinimumCharacters() {
    PasswordVerifier passwordVerifier = new PasswordVerifier();
    boolean output = passwordVerifier.checkMinimumCharacters("password");
    assertTrue(output);
  }

  @Test
  public void testPasswordDoesNotContainUsername() {
    PasswordVerifier passwordVerifier = new PasswordVerifier();
    User mockedUser = Mockito.mock(User.class);
    Mockito.when(mockedUser.getUserName()).thenReturn("janedoe");
    boolean output = passwordVerifier.checkDoesNotContainUsername("password",mockedUser);
    assertTrue(output);
  }
}