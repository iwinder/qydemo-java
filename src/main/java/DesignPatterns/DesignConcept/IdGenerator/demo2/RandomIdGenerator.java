package DesignPatterns.DesignConcept.IdGenerator.demo2;

import DesignPatterns.DesignConcept.IdGenerator.demo1.IdGenerator;
import DesignPatterns.DesignConcept.IdGenerator.demo1.LogTraceIdGenerator;
import DesignPatterns.DesignConcept.IdGenerator.demo3.IdGenerationFailureException;
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
    @Override
    public String generator() {
        String substrOfHostName = getLastfieldOfHostName();
        long currentTimeMills = System.currentTimeMillis();
        String randomString = generateRandomAlphameric(8);
        String id = String.format("%s-%d-%s",substrOfHostName,currentTimeMills,randomString);
        return id;
    }

    private String getLastfieldOfHostName(){
        String substrOfHostName = null;
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
           substrOfHostName = getLastSubstrSplittedByDot(hostName);
            return substrOfHostName;
        } catch (UnknownHostException e) {
            log.error("Failed to get the host name.", e);
        }
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
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length-1];
        return  substrOfHostName;
    }

    @Test
    protected String generateRandomAlphameric(int length) {
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
