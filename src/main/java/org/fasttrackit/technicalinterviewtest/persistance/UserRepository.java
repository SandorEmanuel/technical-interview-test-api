package org.fasttrackit.technicalinterviewtest.persistance;

import org.fasttrackit.technicalinterviewtest.domanin.User;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}