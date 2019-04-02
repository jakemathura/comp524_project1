// reducer function for application to count the number of
// times each unique IP address 4-tuple appears in an
// adudump file.
import java.util.*;
import java.io.*;
import java.net.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.Reducer;

public class LateAircraftDelaysReducer extends Reducer<Text, LongWritable, Text, Text> {

        public void reduce(Text key, Iterable<LongWritable> values, Context context)
                        throws IOException, InterruptedException {

                long total = 0;
                long avg = 0;
                long count = 0;
                long stdev = 0;
                List<LongWritable> list = new ArrayList<LongWritable>();

                // iterate through all the values (count == 1) with a common key
                for (LongWritable value : values) {
                        list.add(value);
                        total += value.get();
                        count = count + 1;
                }
                avg = total/count;
        for (LongWritable value : list) {
            stdev = (value.get() - avg) * (value.get() - avg) + stdev;
        }
        stdev = stdev/count;
        stdev = (long) Math.sqrt(stdev);

                 context.write(key, new Text("Total:" + Long.toString(total) + " Avg:"+Long.toString(avg) + " Stdev:"+ Long.toString(stdev)));
        }
}
