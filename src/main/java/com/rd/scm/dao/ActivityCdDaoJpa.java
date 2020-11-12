package com.rd.scm.dao;

import java.util.Optional;

import com.rd.scm.model.ActivityCd;
import com.rd.scm.model.ActivityCdPK;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "activitycds", path = "activitycds")
//public interface  ActivityCdDaoJpa extends PagingAndSortingRepository<ActivityCd, ActivityCdPK> {
public interface  ActivityCdDaoJpa extends CrudRepository<ActivityCd, ActivityCdPK> {
    
    //.count()
    //.delete(pk)
    //.delete(<Iterable<? extends _activityCd> entities)
    //.delete(entity)
    //.deleteAll()
    //.exists(pk)
    //.findAll()
    //.findAll(Iterable<pk> pks)
    //.findOne(pk)    ---> findById(pk)
    //.save(pk)
    //.save(Iterable<S> entities) 

    Optional<ActivityCd> findByDescr(String descr);

    // List<ActivityCd> = you can do And (String1, String2), In (List<String>), LessThan, Contains(String)
    // String1AndString2Like(String1, searchString)


    //This makes the search pagable
    // ?size=3, default= 20
    // ?page=2, default=0
    // ?sort=title, default=entity id
    // ?sort=title,asc default=asc
    Page<ActivityCd> findByBillRate(long billRate, Pageable pageable);


    // You can override the rest exposure like so:
    // @RepositoryRestResource is at the class level
    // @RestResource is at the method level
    // @RepositoryRestResource can also be used to override the endpoint name
    @Override
    @RestResource(exported = false)
    <S extends ActivityCd> S save(S s);

    
}