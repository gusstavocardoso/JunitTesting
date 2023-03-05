import org.example.MessageUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalutionMessageTest {
    String message = "Robert";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Robert";
        assertEquals(message,messageUtil.salutationMessage());
    }
}
