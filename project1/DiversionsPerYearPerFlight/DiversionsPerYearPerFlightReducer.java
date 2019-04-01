
// reducer function for application to count the number of
// times each unique IP address 4-tuple appears in an
// adudump file.
import java.util.*;
import java.io.*;
import java.net.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.Reducer;

public class DiversionsPerYearPerFlightReducer extends Reducer<Text, Text, Text, DoubleWritable> {

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		long flightCount = 0;
		long diversionCount = 0;

		// iterate through all the values (count == 1) with a common key
		for (Text value : values) {
			String line = value.toString();
			String[] tokens = line.split(" ");

			flightCount += Integer.parseInt(tokens[0]);
			diversionCount += Integer.parseInt(tokens[1]);
		}

		double diversionsPerFlight = 0;

		try {
			diversionsPerFlight = ((double) diversionCount)/((double) flightCount);
		} catch (Exception e) {

		}

		context.write(key, new DoubleWritable(diversionsPerFlight));
	}
}
