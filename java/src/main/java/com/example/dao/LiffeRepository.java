package com.example.dao;

import com.example.model.Forex_2013;
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

    @Query(value = "select * from liffe l where " +
            "l.date between ?1 and ?2 order by l.ticker",nativeQuery = true)
    List<Liffe> getOrderName(int start, int end); // 查询几天之间的 外汇排序

    @Query(value = "select * from liffe n where " +
            "n.date between ?1 and ?2 and n.ticker = ?3",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByDateAndName(int start,int end,String name); //查询某只股票一段时间信息
}
