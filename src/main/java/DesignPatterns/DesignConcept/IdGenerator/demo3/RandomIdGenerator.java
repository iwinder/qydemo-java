package DesignPatterns.DesignConcept.IdGenerator.demo3;

import DesignPatterns.DesignConcept.IdGenerator.demo1.IdGenerator;
import DesignPatterns.DesignConcept.IdGenerator.demo1.LogTraceIdGenerator;
import Utills.PrintUtill;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 提高代码可测试性
 *
 * 为了提高代码的可测试性，我们已经将这两个部分代码跟不可控的组件（本机名、随机函数、时间函数）进行了隔离
 */
@Slf4j
public class RandomIdGenerator implements IdGenerator {

    /**
     *
     * @return
     * @throws IdGenerationFailureException
     */
    @Override
    public String generator() throws IdGenerationFailureException {

        String substrOfHostName =  null;
        try {
            substrOfHostName = getLastfieldOfHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerationFailureException("host name is empty");
        }
        long currentTimeMills = System.currentTimeMillis();
        String randomString = generateRandomAlphameric(8);
        String id = String.format("%s-%d-%s",substrOfHostName,currentTimeMills,randomString);
        return id;
    }

    /**
     * 现在的处理方式是当主机名获取失败的时候，getLastFieldOfHostName() 函数返回 NULL 值。
     * 我们前面讲过，是返回 NULL 值还是异常对象，要看获取不到数据是正常行为，还是异常行为。
     * 获取主机名失败会影响后续逻辑的处理，并不是我们期望的，所以，它是一种异常行为。这里最好是抛出异常，而非返回 NULL 值。
     *
     *
     * 调用者在使用 generate() 函数的时候，只需要知道它生成的是随机唯一 ID，并不关心 ID 是如何生成的。
     * 也就说是，这是依赖抽象而非实现编程。如果 generate() 函数直接抛出 UnknownHostException 异常，实际上是暴露了实现细节。
     * 从代码封装的角度来讲，我们不希望将 UnknownHostException 这个比较底层的异常，暴露给更上层的代码，也就是调用 generate() 函数的代码。
     * 而且，调用者拿到这个异常的时候，并不能理解这个异常到底代表了什么，也不知道该如何处理。
     * UnknownHostException 异常跟 generate() 函数，在业务概念上没有相关性。
     * @return
     * @throws UnknownHostException
     */
    private String getLastfieldOfHostName() throws UnknownHostException {
        String substrOfHostName = null;
        String hostName = InetAddress.getLocalHost().getHostName();
        if (hostName == null || hostName.isEmpty()) {
            throw new UnknownHostException("...");
        }
        substrOfHostName = getLastSubstrSplittedByDot(hostName);
        return substrOfHostName;

    }

    /***
     * Google Guava 的 @VisibleForTesting,只起标识作用，告诉人们该函数本应是private权限，之所以提升为protected，只是用于测试，只能用于单元测试中。
     * 这里用@Test代替。
     * @param hostName
     * @return
     */
    @Test
    protected String getLastSubstrSplittedByDot(String hostName){
        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("...");
        }
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length-1];
        return  substrOfHostName;
    }

    @Test
    protected String generateRandomAlphameric(int length) {
        if(length <= 0) {
            throw new IllegalArgumentException("...");
        }

        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii =  'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char)(randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }

    public static void main(String[] args) {
//        LogTraceIdGenerator logTraceIdGenerator = new RandomIdGenerator();
//        PrintUtill.println(logTraceIdGenerator.generator());

        LogTraceIdGenerator logTraceIdGenerator = new LogTraceIdGenerator(new RandomIdGenerator());
        try {
            PrintUtill.println(logTraceIdGenerator.generator());
        } catch (IdGenerationFailureException e) {
            e.printStackTrace();
        }


    }
}
