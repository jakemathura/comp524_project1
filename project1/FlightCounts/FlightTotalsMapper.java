
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

public class FlightTotalsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] tokens = line.split(",");

		String airlineCarrier = tokens[2];
		String year = tokens[0];
		int totalFlights = 0;

		try {
			totalFlights = Integer.parseInt(tokens[7]);

		} catch (Exception e) {
			
		}

		String yearPlusCarrier = year + " " + airlineCarrier;

		context.write(new Text(yearPlusCarrier), new IntWritable(totalFlights));

	}
}
