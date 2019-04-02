// main function for application to count the number of
// times each unique IP address 4-tuple appears in an
// adudump file.
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LateAircraftDelays {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: HostCount <input path> <output path>");
      System.exit(-1);
    }

    //define the job to the JobTracker
    Job job = Job.getInstance();
    job.setJarByClass(LateAircraftDelays.class);
    job.setJobName("Total Late Aircraft Delays");

    // set the input and output paths (passed as args)
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // set the Mapper and Reducer classes to be called
    job.setMapperClass(LateAircraftDelaysMapper.class);
    job.setReducerClass(LateAircraftDelaysReducer.class);

    // set the format of the keys and values
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(LongWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);

    // submit the job and wait for its completion
    job.waitForCompletion(true);

  }
}
