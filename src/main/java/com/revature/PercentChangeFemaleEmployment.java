package com.revature;

import com.revature.map.PercentChangeFemaleEmploymentMapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * PercentChangeFemaleEmployment
 */
public class PercentChangeFemaleEmployment {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: PercentChangeFemaleEmployment <input_dir> <output_dir>");
            System.exit(-1);
        } else {
            /**
             * The MMpReduce object
             */
            Job job = new Job();
            /**
             * The class that contains the main() method
             */
            job.setJarByClass(PercentChangeFemaleEmployment.class);

            job.setJobName("Percent Change of Female Employment");
            /**
             * Set input and output paths
             */
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            /**
             * Specify mapper and reducer class
             */
            job.setMapperClass(PercentChangeFemaleEmploymentMapper.class);
            /**
             * specify
             */
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            /**
             * run and check
             */
            boolean jobComplete = job.waitForCompletion(true);
            System.exit(jobComplete ? 0 : 1);
        }
    }
}