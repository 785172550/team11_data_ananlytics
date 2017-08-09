package com.example.algor;

import com.example.model.Stock;
import com.example.model.StocksSort;
import com.example.service.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.utils.DemoUtils.GenerateStock;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */
public class AlgorTest {
    Logger logger = LoggerFactory.getLogger(AlgorTest.class);

    @Before
    public void setUp() {

    }

    @Test
    public void AlgorTest1(){
//        int N=100;
//        List<Stock> resource = GenerateStock(N);
//        for (int i=0;i<N;i++){
//            resource.get(i).setId(i);
//            resource.get(i).setClose(Random()*0.5);
//        }
//        List<List<Stock>> list = new ArrayList<List<Stock>>();
//        for(int i=0;i<N-2;i+=2){
//            List<Stock> tmp = new ArrayList<Stock>();
//            tmp.add(resource.get(i));
//            tmp.add(resource.get(i+1));
//            list.add(tmp);
//        }
//
//        Calculator calculator = Calculator.getCalculator();
//        List<StocksSort> res = calculator.CalculateIncre(list);
//        logger.info(list.toString());
    }
}
