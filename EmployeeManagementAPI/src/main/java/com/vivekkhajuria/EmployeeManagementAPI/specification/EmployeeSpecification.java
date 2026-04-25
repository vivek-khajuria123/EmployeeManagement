package com.vivekkhajuria.EmployeeManagementAPI.specification;

import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {
    public static Specification<Employee> getSpecification(Long id, String name, String email, String dept){
        return new Specification<Employee>() {

            @Override
            public @Nullable Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(name != null && !name.isEmpty()){
                    list.add(criteriaBuilder.equal(root.get("name"),name));
                }if(email != null && !email.isEmpty()){
                    list.add(criteriaBuilder.equal(root.get("email"),email));
                }if(dept != null && !dept.isEmpty()){
                    list.add(criteriaBuilder.equal(root.get("dept"),dept));
                }if(id != null){
                    list.add(criteriaBuilder.equal(root.get("id"),id));
                }
                return criteriaBuilder.or(list.toArray(list.toArray(new Predicate[0])));
            }
        };
    }
}
