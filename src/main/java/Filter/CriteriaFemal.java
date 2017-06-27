package Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-26
 * Time: 10:57 上午
 */
public class CriteriaFemal implements Criteria {
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for(Person person : persons){
            if (person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
