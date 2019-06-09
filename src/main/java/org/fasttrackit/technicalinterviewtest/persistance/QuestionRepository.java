package org.fasttrackit.technicalinterviewtest.persistance;

import org.fasttrackit.technicalinterviewtest.domanin.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
