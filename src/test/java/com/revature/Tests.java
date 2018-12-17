package com.revature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.map.AverageIncreaseFemalesMapper;
import com.revature.map.FemaleGradsMapper;
import com.revature.map.PercentChangeFemaleEmploymentMapper;
import com.revature.map.PercentChangeMaleEmploymentMapper;
import com.revature.reduce.AverageIncreaseFemalesReducer;
import com.revature.reduce.FemaleGradsReducer;

public class Tests {
    /*
     * Declare harnesses that let you test a mapper, a reducer, and a mapper and a
     * reducer working together.
     */
    private MapDriver<LongWritable, Text, Text, DoubleWritable> mapDriver;
    private MapDriver<LongWritable, Text, Text, Text> mapDriverMapOnly;
    private ReduceDriver<Text, DoubleWritable, Text, DoubleWritable> reduceDriver;
    /*
     * Set up the test. This method will be called before every test.
     */
    @Before
    public void setUp() {
        /**
         * 1. Female Grads
         */
        FemaleGradsMapper mapper1 = new FemaleGradsMapper();
        mapDriver = new MapDriver<LongWritable, Text, Text, DoubleWritable>();
        mapDriver.setMapper(mapper1);
        /*
         * Set up the reducer test harness.
         */
        FemaleGradsReducer reducer1 = new FemaleGradsReducer();
        reduceDriver = new ReduceDriver<Text, DoubleWritable, Text, DoubleWritable>();
        reduceDriver.setReducer(reducer1);
        /*
         * 2. Average Increase Female Education 
         * Set up the mapper test harness.
         */
        AverageIncreaseFemalesMapper mapper2 = new AverageIncreaseFemalesMapper();
        mapDriver = new MapDriver<LongWritable, Text, Text, DoubleWritable>();
        mapDriver.setMapper(mapper2);
        /*
         * Set up the reducer test harness.
         */
        AverageIncreaseFemalesReducer reducer2 = new AverageIncreaseFemalesReducer();
        reduceDriver = new ReduceDriver<Text, DoubleWritable, Text, DoubleWritable>();
        reduceDriver.setReducer(reducer2);
        /**
         * 3. Percent Change Females
         */
        PercentChangeFemaleEmploymentMapper mapper3 = new PercentChangeFemaleEmploymentMapper();
        mapDriverMapOnly = new MapDriver<LongWritable, Text, Text, Text>();
        mapDriverMapOnly.setMapper(mapper3);
        /**
         * 4. Percent Change Males
         */
        PercentChangeMaleEmploymentMapper mapper4 = new PercentChangeMaleEmploymentMapper();
        mapDriverMapOnly = new MapDriver<LongWritable, Text, Text, Text>();
        mapDriverMapOnly.setMapper(mapper4);
    }
    /*
     * 1. Female Grads
     */
    @Test
    public void FemaleGradsTestMapper() {
        mapDriver.withInput(new LongWritable(1), new Text(
                "\"United States\",\"USA\",\"r, tertiary, female (%)\",\"SE.TER.CMPL.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"35.85857\",\"37.8298\",\"37.43131\""));
        mapDriver.withOutput(new Text("United States"), new DoubleWritable(37.43131));
        /*
         * Run the test.
         */
        mapDriver.runTest();
    }
    @Test
    public void FemaleGradsTestReducer() {
        List<DoubleWritable> values1 = new ArrayList<DoubleWritable>();
        values1.add(new DoubleWritable(35.85857));
        values1.add(new DoubleWritable(37.8298));
        values1.add(new DoubleWritable(37.43131));
        values1.add(new DoubleWritable(38.22037));
        values1.add(new DoubleWritable(39.18913));
        values1.add(new DoubleWritable(39.84185));
        values1.add(new DoubleWritable(40.23865));
        values1.add(new DoubleWritable(41.26198));
        values1.add(new DoubleWritable(42.00725));
        values1.add(new DoubleWritable(42.78946));
        values1.add(new DoubleWritable(43.68347));
        values1.add(new DoubleWritable(46.37914));
        values1.add(new DoubleWritable(47.68032));
        /*
         * For this test, the reducer's input will be "country values".
         */
        reduceDriver.withInput(new Text("United States"), values1);
        reduceDriver.withOutput(new Text(
                "United States(Average Sum Percent of the Gross graduation ratio of Females, that are less than 30%): "),
                new DoubleWritable(2.758));

        reduceDriver.runTest();
    }
    /*
     * 2. AverageIncreaseFemales
     */
    @Test
    public void AverageIncreaseFemalesTestMapper() {
        mapDriver.withInput(new LongWritable(1), new Text("\"United States\",\"USA\",\"r, tertiary, female (%)\",\"SE.TER.CMPL.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"35.85857\",\"37.8298\",\"37.43131\""));
        mapDriver.withOutput(new Text("United States"), new DoubleWritable(37.43131));
        /*
         * Run the test.
         */
        mapDriver.runTest();
    }
    @Test
    public void AverageIncreaseFemalesTestReducer() {
        List<DoubleWritable> values = new ArrayList<DoubleWritable>();
        values.add(new DoubleWritable(35.85857));
        values.add(new DoubleWritable(37.8298));
        values.add(new DoubleWritable(37.43131));
        values.add(new DoubleWritable(38.22037));
        values.add(new DoubleWritable(39.18913));
        values.add(new DoubleWritable(39.84185));
        values.add(new DoubleWritable(40.23865));
        values.add(new DoubleWritable(41.26198));
        values.add(new DoubleWritable(42.00725));
        values.add(new DoubleWritable(42.78946));
        values.add(new DoubleWritable(43.68347));
        values.add(new DoubleWritable(46.37914));
        values.add(new DoubleWritable(47.68032));
        /*
         * For this test, the reducer's input will be "country values".
         */
        reduceDriver.withInput(new Text("United States"), values);
        /*
         * The expected output is
         * "United States(Average Sum Percent of the Gross graduation ratio of Females, that are less than 30%): 3.403"
         */
        reduceDriver.withOutput(new Text("United States(Average Sum Percent of the Gross graduation ratio of Females, that are less than 30%): "), new DoubleWritable(2.758));

        /*
         * Run the test.
         */
        reduceDriver.runTest();
    } 
    /*
     * 3.Percent Change In Female
     */
    @Test
    public void PercentIncreaseFemaleTestMapper() {
        mapDriverMapOnly.withInput(new LongWritable(1), new Text(
                "\"Argentina\",\"ARG\",\"Wage and salaried workers, female (% of female employment)\",\"SL.EMP.WORK.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"74.3399963378906\",\"75.0500030517578\",\"75.5199966430664\",\"75.870002746582\",\"76.4000015258789\",\"76.129997253418\",\"78.0599975585938\",\"78.5599975585938\",\"78.629997253418\",\"79.0400009155273\",\"80.1900024414063\",\"\",\"80.7300033569336\",\"80.4800033569336\",\"79.7699966430664\",\"80.0500030517578\",\"80.620002746582\",\"81.1399993896484\",\"79.870002746582\""));
        mapDriverMapOnly.withOutput(new Text("Argentina: -->"),
                new Text("Female % employment from year 2000 to 2016 INCREASED by: 4.35"));
        /*
         * Run the test.
         */
        mapDriverMapOnly.runTest();
    }
    /*
     * 4.Percent Change In Male
     */
    @Test
    public void PercentIncreaseMaleTestMapper() {
        mapDriverMapOnly.withInput(new LongWritable(1), new Text(
                "\"Argentina\",\"ARG\",\"Wage and salaried workers, female (% of female employment)\",\"SL.EMP.WORK.FE.ZS\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"74.3399963378906\",\"75.0500030517578\",\"75.5199966430664\",\"75.870002746582\",\"76.4000015258789\",\"76.129997253418\",\"78.0599975585938\",\"78.5599975585938\",\"78.629997253418\",\"79.0400009155273\",\"80.1900024414063\",\"\",\"80.7300033569336\",\"80.4800033569336\",\"79.7699966430664\",\"80.0500030517578\",\"80.620002746582\",\"81.1399993896484\",\"79.870002746582\",\"\",\"\","));
        mapDriverMapOnly.withOutput(new Text("Argentina: -->"),
                new Text("Female % employment from year 2000 to 2016 INCREASED by: 4.35"));
        /*
         * Run the test.
         */
        mapDriverMapOnly.runTest();
    }
    
    
    
    
    
    
    
    
    /*
     * Test the mapper and reducer working together.
     */
    // @Test
    // public void testMapReduce() {
    //     /*
    //      * For this test, the mapper's input will be "1 cat cat dog"
    //      */
    //     mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat dog"));

    //     /*
    //      * The expected output (from the reducer) is "cat 2", "dog 1".
    //      */
    //     mapReduceDriver.addOutput(new Text("cat"), new IntWritable(2));
    //     mapReduceDriver.addOutput(new Text("dog"), new IntWritable(1));

    //     /*
    //      * Run the test.
    //      */
    //     mapReduceDriver.runTest();
    // }
}