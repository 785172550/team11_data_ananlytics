package com.example.dao;

import com.example.model.Liffe;
import com.example.model.Nasdaq_2013;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kenneth on 2017/8/9.
 */
public interface LiffeRepository extends JpaRepository<Liffe,Integer> {


    @Query(value = "select * from  Liffe l where l.ticker = ?1",nativeQuery = true)
    List<Liffe> getLiffeByName(String name);

    @Query(value = "select * from liffe l where l.date between ?1 and ?2",nativeQuery = true)
    List<Liffe> getNasdaqByDate(int start, int end);
}
