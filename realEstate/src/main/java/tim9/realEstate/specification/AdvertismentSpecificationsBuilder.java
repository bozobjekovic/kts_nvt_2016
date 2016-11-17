package tim9.realEstate.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import tim9.realEstate.model.Advertisment;

public class AdvertismentSpecificationsBuilder {
	
	private final List<SearchCriteria> params;
	
	public AdvertismentSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
	
	public AdvertismentSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
	
	public Specification<Advertisment> build() {
		
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Advertisment>> specs = new ArrayList<Specification<Advertisment>>();
        for (SearchCriteria param : params) {
            specs.add(new AdvertismentSpecification(param));
        }
 
        Specification<Advertisment> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        
        return result;
    }

}
