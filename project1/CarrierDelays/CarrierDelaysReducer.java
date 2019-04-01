// reducer function for application to count the number of
// times each unique IP address 4-tuple appears in an
// adudump file.
import java.util.*;
import java.io.*;
import java.net.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.Reducer;

public class CarrierDelaysReducer extends Reducer<Text, DoubleWritable, Text, Text> {

        public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
                        throws IOException, InterruptedException {

                double total = 0;
               double avg = 0;
                double count = 0;
                double stdev = 0;
                List<DoubleWritable> list = new ArrayList<DoubleWritable>();

                // iterate through all the values (count == 1) with a common key
                for (DoubleWritable value : values) {
                        list.add(value);
                        total += value.get();
                        count = count + 1;
                }
                avg = total/count;
        for (DoubleWritable value : list) {
            stdev = (value.get() - avg) * (value.get() - avg) + stdev;
        }
        stdev = stdev/count;
        stdev = (double) Math.sqrt(stdev);

                 context.write(key, new Text("Total:" + Double.toString(total) + " Avg:"+Double.toString(avg) + " Stdev:"+ Double.toString(stdev)));
        }
}




