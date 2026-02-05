package com.arya.repo;

import com.arya.entity.Employee;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;;

// JpaRepository extends PagingAndSortingRepository, which give us pagination out of the box
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    //filtering 1: derived query methods(Spring writes the sql)
    //select * from employees where department =?
    Page<Employee> findByDepartment(String department, Pageable pageable);

    //filtering 2: custom jpql
    //useful for complex logic, we select e(object)
    //@Query("SELECT e FROM Employee e WHERE e.email LIKE %:domain%")
    // Page<Employee> findByEmailDomain(String domain, Pageable pageable);

}



// "Give me the second page (index 1), with 5 employees, sorted by name"
// Pageable firstPage = PageRequest.of(1, 5, Sort.by("name"));
//internally in sql it uses, limit offset, and count(*)
//Page<Employee>
// will contain: actual data,total rows,total pages,current page,page size

//but limit offset slow, hence, cursor, seeking, keyset pagination
//leveraging id index and query be like:
//SELECT * FROM employee 
// WHERE id > 100000 
// ORDER BY id ASC 
// LIMIT 10;
// @Query("SELECT e FROM Employee e WHERE e.id > :lastId ORDER BY e.id ASC")
// List<Employee> findNextPage(@Param("lastId") Long lastId, Pageable pageable);