package com.group.cast.diffator.repository;

import com.group.cast.diffator.domain.Diff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiffRepository extends CrudRepository<Diff, Long> {
}
