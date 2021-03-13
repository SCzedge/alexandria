package com.alexandria.hadoop.wordCount;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordCount {
	 public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
	        Configuration conf = new Configuration();
	        if(args.length != 2){
	            System.out.println("Usage : WordCount <input> <output>");
	            System.exit(2);
	        }

	        Job job  = new Job(conf, "WordCount");
	        // jar 파일 실행 클래스 설정
	        job.setJarByClass(WordCount.class);
	        // 매퍼 클래스 설정
	        job.setMapperClass(WordCountMapper.class);
	        // 리듀스 클래스 설정
	        job.setReducerClass(WordCountReducer.class);

	        // 입출력 데이터 포맷 설정
	        job.setInputFormatClass(TextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);

	        //매퍼와 리듀스 클래스의 출력 데이터의 키와  값 타입을 설정
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(IntWritable.class);

	        // 입출력 데이터를 어떤 경로로 전달 받은 것인지 설정.
	        FileInputFormat.addInputPath(job, new Path(args[0]));
	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
	        
	        
	        // 잡 객체의 waitForCompletion메서드를 호출해 잡을 실행
	        job.waitForCompletion(true);

	    }
	
	
}
