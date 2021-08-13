package parallelscripts;

import org.testng.annotations.Test;

public class ClassTwoTest {
	
	
	@Test
	public void methodOne()
	{
		long id = Thread.currentThread().getId();
		System.out.println("MethodOne Test from ClassTwo : "+id);
	}

    @Test
    public void methodTwo()
    {
    	long id = Thread.currentThread().getId();
    	System.out.println("MethodTwo Test from ClassTwo : "+id);
    }

}


