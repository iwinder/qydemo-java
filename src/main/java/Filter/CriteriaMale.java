package Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-26
 * Time: 10:50 上午
 */
public class CriteriaMale implements Criteria{
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> melePersons = new ArrayList<Person>();
        for(Person person:persons){
            if (person.getGender().equalsIgnoreCase("MALE")){
                melePersons.add(person);
            }
        }
        return melePersons;
    }
}
