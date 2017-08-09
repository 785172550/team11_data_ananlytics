package com.example.dao;

import com.example.model.Nasdaq_2013;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kenneth on 2017/8/9.
 *
 */
public interface NasdaqRespostory extends JpaRepository<Nasdaq_2013,Integer>{

    @Query(value = "select * from nasdaq_2013 n where n.ticker = ?1",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByName(String name);

    @Query(value = "select * from nasdaq_2013 n where n.date between ?1 and ?2",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByDate(int start,int end);
}
