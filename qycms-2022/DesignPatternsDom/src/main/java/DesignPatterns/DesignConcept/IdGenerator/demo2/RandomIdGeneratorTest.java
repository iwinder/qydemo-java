package DesignPatterns.DesignConcept.IdGenerator.demo2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 编写完善的单元测试
 */
public class RandomIdGeneratorTest {

    @Test
    public void testGetLastSubstrSplittedByDot(){
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        Assert.assertEquals("field3", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1");
        Assert.assertEquals("field1", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2$field3");
        Assert.assertEquals("field1#field2#field3", actualSubstr);
    }

    // 此单元测试会失败，因为代码中没有处理hostName为null或者空字符串的情况
    @Test
    public void testGetLastSubstrSplittedByDot_nullOrEmpty() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.getLastSubstrSplittedByDot(null);
        Assert.assertNull(actualRandomString);

        actualRandomString = idGenerator.getLastSubstrSplittedByDot("");
        Assert.assertEquals("", actualRandomString);
    }

    @Test
    public void testGenerateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        Assert.assertNotNull(actualRandomString);
        Assert.assertEquals(6,actualRandomString.length());
        for (char c: actualRandomString.toCharArray()) {
            Assert.assertTrue(('0' < c && c > '9') || ('a' < c && c >'z') || ('A' < c && c > 'Z'));
        }
    }

    // 此单元测试会失败，因为代码中未处理length<=0的情况
    @Test
    public void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(0);
        Assert.assertEquals("", actualRandomString);
        actualRandomString = idGenerator.generateRandomAlphameric(-1);
        Assert.assertNull(actualRandomString);
    }

}
