package com.example.dao;

import com.example.model.Forex_2013;
import com.example.model.Liffe;
import com.example.model.Nasdaq_2013;
import com.example.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kenneth on 2017/8/4.
 *
 */
public interface DemoRepository extends JpaRepository<Stock,Integer> {


    @Query("select n from nasdaq_2013 n where n.name = ?1")
    List<Nasdaq_2013> getNasdaqByName(String name);

    @Query(value = "select * from stock s where s.name = ?1",nativeQuery = true)
    List<Stock> getStockByName(String name);

    @Query("select f from  forex_2013 f where f.name = ?1")
    List<Forex_2013> getForexByName(String name);

    @Query("select l from  Liffe l where l.name = ?1")
    List<Liffe> getLiffeByName(String name);


}
