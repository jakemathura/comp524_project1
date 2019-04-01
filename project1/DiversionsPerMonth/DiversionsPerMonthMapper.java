
// map function for application to count the number of
// times each unique IP address 4-tuple appears in an
// adudump file.
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.Mapper;

public class DiversionsPerMonthMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] tokens = line.split(",");

		// month = 1, late aircraft = length - 1
		String month = tokens[1];
		int totalDiversions = 0;

		try {
			totalDiversions = Integer.parseInt(tokens[15]);

		} catch (Exception e) {
			
		}

		context.write(new Text(month), new IntWritable(totalDiversions));

	}
}
