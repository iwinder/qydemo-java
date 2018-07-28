package DesignPatterns.Structural.Filter;

import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-26
 * Time: 12:35 下午
 */
public class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria,Criteria otherCriteria){
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    public List<Person> meetCriteria(List<Person> persions) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persions);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persions);
        for (Person person : otherCriteriaItems){
            if (!firstCriteriaItems.contains(person)){
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
