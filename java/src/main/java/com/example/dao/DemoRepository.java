package com.example.dao;

import com.example.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Kenneth on 2017/8/4.
 *
 */
public interface DemoRepository extends JpaRepository<Stock,Integer> {

    @Query(value = "select * from stock s where s.stockname = ?1",nativeQuery = true)
    List<Stock> getStockByName(@Param("stockname") String name);

    @Query(value = "select * from stock s where s.date between ?1 and ?2 and time = 0",nativeQuery = true)
    List<Stock> getStockByDateNoTime(int start,int end);

    @Query(value = "select * from stock s where s.date between ?1 and ?2 and time <> 0",nativeQuery = true)
    List<Stock> getStockDate(int start,int end);
}
