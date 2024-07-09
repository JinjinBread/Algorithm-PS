package codingtest_java.decimalToBinary;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void test() {
        Assert.assertEquals(Solution.solution(10), "1010");
        Assert.assertEquals(Solution.solution(27), "11011");
        Assert.assertEquals(Solution.solution(12345), "11000000111001");
    }

}
