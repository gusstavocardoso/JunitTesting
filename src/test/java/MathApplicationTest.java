import org.example.CalculatorService;
import org.example.MathApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InOrder;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {

    private MathApplication mathApplication;
    private CalculatorService calcService;

    @Before
    public void setUp(){
        mathApplication = new MathApplication();
        calcService = mock(CalculatorService.class);
        mathApplication.setCalculatorService(calcService);
    }

    @Test
    public void testAddAndSubtract(){

        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenReturn(30.0);

        //subtract the behavior to subtract numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

        //verify call to calcService is made or not
        verify(calcService).add(20.0,10.0);
        verify(calcService).subtract(20.0,10.0);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionHandling(){
        //add the behavior to throw exception
        doThrow(new RuntimeException("Add operation not implemented"))
                .when(calcService).add(10.0,20.0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
    }

    @Test
    public void testVaryingCalls(){
        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //add the behavior of calc service to subtract two numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

        //check a minimum 1 call count
        verify(calcService, atLeastOnce()).subtract(20.0, 10.0);

        //check if add function is called minimum 2 times
        verify(calcService, atLeast(2)).add(10.0, 20.0);

        //check if add function is called maximum 3 times
        verify(calcService, atMost(3)).add(10.0,20.0);
    }

    @Test
    public void testExpectingCalls(){
        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //add the behavior of calc service to subtract two numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

        //default call count is 1
        verify(calcService).subtract(20.0, 10.0);

        //check if add function is called three times
        verify(calcService, times(3)).add(10.0, 20.0);

        //verify that method was never called on a mock
        verify(calcService, never()).multiply(10.0,20.0);
    }

    @Test @Ignore
    public void testOrderedVerification(){

        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenReturn(30.0);

        //subtract the behavior to subtract numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(calcService);

        //following will make sure that add is first called then subtract is called.
        inOrder.verify(calcService).subtract(20.0,10.0);
        inOrder.verify(calcService).add(20.0,10.0);
    }

    @Test
    public void testBehaviorDrivenDevelopment(){

        //Given
        given(calcService.add(20.0,10.0)).willReturn(30.0);

        //when
        double result = calcService.add(20.0,10.0);

        //then
        Assert.assertEquals(result,30.0,0);
    }

    @Test
    public void tesCallBack(){

        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {

            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                //get the arguments passed to mock
                Object[] args = invocation.getArguments();

                //get the mock
                Object mock = invocation.getMock();

                //return the result
                return 30.0;
            }
        });

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
    }
}

