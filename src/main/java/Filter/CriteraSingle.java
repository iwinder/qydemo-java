package Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-26
 * Time: 10:59 上午
 */
public class CriteraSingle implements Criteria {
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person:persons){
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
