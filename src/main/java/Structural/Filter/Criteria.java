package Structural.Filter;

import java.util.List;

/**
 * Description:为标准（Criteria）创建一个接口。
 * User: wind
 * Date: 2017-06-26
 * Time: 10:48 上午
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persions);
}
