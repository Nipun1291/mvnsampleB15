package parallelscripts;

import org.testng.annotations.Test;

public class ClassOneTest {

	@Test
	public void methodOne()
	{
		long id = Thread.currentThread().getId();
		System.out.println("MethodOne Test from ClassOne : "+id);
	}

    @Test
    public void methodTwo()
    {
    	long id = Thread.currentThread().getId();
    	System.out.println("MethodTwo Test from ClassOne : "+id);
    }

}
