package com.revature;

import com.revature.map.CorrelationLoanEmergencyFundsMapper;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Average Increase of Female Education from Year 2000
 *
 */
public class CorrelationLoanEmergencyFunds {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: CorrelationLoanEmergencyFunds <input_dir> <output_dir>");
            System.exit(-1);
        } else {
            /**
             * The MMpReduce object
             */
            Job job = new Job();
            /**
             * The class that contains the main() method
             */
            job.setJarByClass(CorrelationLoanEmergencyFunds.class);

            job.setJobName("Correlation between loans and emergency");
            /**
             * Set input and output paths
             */
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            /**
             * Specify mapper and reducer class
             */
            job.setMapperClass(CorrelationLoanEmergencyFundsMapper.class);
            // job.setReducerClass(AverageIncreaseFemalesReducer.class);
            /**
             * specify
             */
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);
            /**
             * run and check
             */
            boolean jobComplete = job.waitForCompletion(true);
            System.exit(jobComplete ? 0 : 1);
        }
    }
}
