package com.revature.map;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Contains logic of Mapper for average increase in female education in the U.S.
 * from the year 2000.
 */
public class CorrelationLoanEmergencyFundsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        int index = 0;
        double percent;
        String 
            indicator = "",
            country = "";
        boolean flag = false;
        /**
         * Fetch outstanding loans
         */
        if (line.matches("(.*)Outstanding loan to pay school fees, male(.*)") || line.matches("(.*)Coming up with emergency funds: not at all possible, male(.*)")) {
            for (String word : line.split(",")) {
                word = word.replace("\"", "");
                if (index == 0) {
                        country = word;
                        flag = true;
                } 
                if (index == 2) {
                    indicator = word;
                }
               
                /**
                 * Fetch percentages from year 2000 onwards
                 */
                if (index > 46 && !word.equals("") && flag == true) {
                    percent = Double.parseDouble(word);
                    context.write(new Text(country +"("+ indicator+ ")"), new DoubleWritable(percent));
                }
                
                index += 1;
            }
        }
    }
}