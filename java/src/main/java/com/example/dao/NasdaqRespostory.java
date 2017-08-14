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

    @Query(value = "select * from nasdaq_2013 n where " +
            "n.ticker = ?1",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByName(String name);// 按名查询股票 所有信息

    @Query(value = "select * from nasdaq_2013 n where " +
            "n.date between ?1 and ?2 and n.ticker = ?3",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByDateAndName(int start,int end,String name); //查询某只股票一段时间信息

    @Query(value = "select * from nasdaq_2013 n where " +
            "n.date between ?1 and ?2",nativeQuery = true)
    List<Nasdaq_2013> getNasdaqByDate(int start,int end); //查询一段时间的信息

    @Query(value = "select * from nasdaq_2013 n where " +
            "n.date between ?1 and ?2 order by n.ticker",nativeQuery = true)
    List<Nasdaq_2013> getOrderName(int start,int end); // 查询几天之间的 股票
}
