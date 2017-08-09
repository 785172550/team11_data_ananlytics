package com.example.dao;

import com.example.model.Forex_2013;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kenneth on 2017/8/9.
 */
public interface ForexRepository extends JpaRepository<Forex_2013,Integer> {

    @Query(value = "select * from  forex_2013 f where f.ticker = ?1",nativeQuery = true)
    List<Forex_2013> getForexByName(String name);
}
