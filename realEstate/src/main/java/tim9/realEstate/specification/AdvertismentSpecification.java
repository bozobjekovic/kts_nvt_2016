package tim9.realEstate.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.RealEstate;

public class AdvertismentSpecification implements Specification<Advertisment>{
	
	private SearchCriteria criteria;

	public AdvertismentSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Advertisment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
        	if (criteria.getKey().equals("type") || criteria.getKey().equals("heatingType")) {
        		Join<Advertisment, RealEstate> country = root.join("realEstate");
            	return builder.like(
            			country.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				if (root.get(criteria.getKey()).getJavaType() == String.class) {
					return builder.like(
			                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
	            }  else {
	                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	            }
			}
        }
        return null;
	}

}
