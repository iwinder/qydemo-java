package DesignPatterns.DesignConcept.IdGenerator.demo1;

import DesignPatterns.DesignConcept.IdGenerator.demo3.IdGenerationFailureException;
import Utills.PrintUtill;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

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
            String[] tokens = hostName.split("\\.");
            substrOfHostName = tokens[tokens.length-1];
            return substrOfHostName;
        } catch (UnknownHostException e) {
            log.error("Failed to get the host name.", e);
        }
        return substrOfHostName;
    }

    private String generateRandomAlphameric(int length) {
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
