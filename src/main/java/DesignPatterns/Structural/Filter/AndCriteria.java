package DesignPatterns.Structural.Filter;

import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-26
 * Time: 12:31 下午
 */
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria,Criteria otherCriteria){
        this.criteria =criteria;
        this.otherCriteria = otherCriteria;
    }
    public List<Person> meetCriteria(List<Person> persions) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persions);
        return  otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
