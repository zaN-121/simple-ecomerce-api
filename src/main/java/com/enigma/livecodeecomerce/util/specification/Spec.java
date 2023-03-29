package com.enigma.livecodeecomerce.util.specification;

import com.enigma.livecodeecomerce.model.Category;
import com.enigma.livecodeecomerce.model.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class Spec<T> {
    public Specification<T> findBy(SearchCriteria searchCriteria) {
        switch (searchCriteria.getOperator()) {
            case LIKE:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%"));
            case EQUAL:
                return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue())));
            case NOT_EQUAL:
                return ((root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
            case JOIN_EQUAL:
                return ((root, query, criteriaBuilder) -> {
                    Join<T, Category> categoryJoin = root.join(searchCriteria.getKey()).join("Prodcut");
                    return criteriaBuilder.equal(categoryJoin.get(searchCriteria.getKey() + "Id"), searchCriteria.getValue());
                });
            default:
                throw new RuntimeException("Operator not supported");
        }
    }
}
