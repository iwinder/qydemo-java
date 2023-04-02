package DesignPatterns.DesignConcept.IdGenerator;





import Utills.PrintUtill;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * id生成器 示例
 * 整个 ID 由三部分组成。
 * 第一部分是本机名的最后一个字段。
 * 第二部分是当前时间戳，精确到毫秒。
 * 第三部分是 8 位的随机字符串，包含大小写字母和数字。
 *
 * 不足：
 * ### 业务本身无关的、通用的代码质量关注点 ：
 * 1. IdGenerator 设计成了实现类而非接口，调用者直接依赖实现而非接口，
 * 违反基于接口而非实现编程的设计思想；（当需要按不同算法生成ID时，不如接口便于扩展）
 *
 * 2.把 IdGenerator 的 generate() 函数定义为静态函数，
 * 会影响使用该函数的代码的可测试性。
 *
 * 3.generate() 函数的代码实现依赖运行环境（本机名）、时间函数、随机函数，
 * 所以 generate() 函数本身的可测试性也不好，需要做比较大的重构。
 *
 * 4.虽然 IdGenerator 只包含一个函数，并且代码行数也不多，但代码的可读性并不好。
 * 特别是随机字符串生成的那部分代码，
 * 一方面，代码完全没有注释，生成算法比较难读懂，
 * 另一方面，代码里有很多魔法数，严重影响代码的可读性。
 * 在重构的时候，我们需要重点提高这部分代码的可读性
 *
 * ### 对照业务本身的功能和非功能需求
 *
 * 1.代码生成的 ID 并非绝对的唯一
 * 2. 获取 hostName 这部分代码逻辑貌似有点问题，并未处理“hostName 为空”的情况
 * 3. 尽管代码中针对获取不到本机名的情况做了异常处理，但是对异常的处理是在 IdGenerator 内部将其吐掉，然后打印一条报警日志，并没有继续往上抛出
 * 4. 每次生成 ID 都需要获取本机名，获取主机名会比较耗时，所以，这部分可以考虑优化一下
 * 5. 还有，randomAscii 的范围是 0～122，但可用数字仅包含三段子区间（0~9，a~z，A~Z），极端情况下会随机生成很多三段区间之外的无效数字，需要循环很多次才能生成随机字符串，所以随机字符串的生成算法也可以优化一下。
 *
 * 6. 在 generate() 函数的 while 循环里面，三个 if 语句内部的代码非常相似，而且实现稍微有点过于复杂了，实际上可以进一步简化，将这三个 if 合并在一起
 */
@Slf4j
public class IdGenerator1 {
    public static String generate(){
        String id = "";
        try {
            // 1. 本机名的最后一个字段
            String hostName = InetAddress.getLocalHost().getHostName();
            log.info("ALL HostName:",hostName);
            PrintUtill.printlnRule();
            PrintUtill.printlnRule();
            String[] tokens = hostName.split("\\.");
            if (tokens.length>0) {
                hostName = tokens[tokens.length-1];
            }
            // 2. 8位随机数，包含字母和数字
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count<8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <=57 ) {
                    randomChars[count] = (char)('0'+(randomAscii-48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char)('0'+(randomAscii-65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char)('a' + (randomAscii - 97));
                    count++;
                }
            }
            // 最终
            id = String.format("%s-%d-%s",hostName,
                    System.currentTimeMillis(),new String(randomChars));
        } catch (UnknownHostException e) {
            log.error("Failed to get the host name.", e);
//            e.printStackTrace();
        }
        return id;
    }

    public static void main(String[] args) {
        PrintUtill.println(IdGenerator1.generate());
        PrintUtill.println(IdGenerator1.generate());
        PrintUtill.println(IdGenerator1.generate());
    }
}
