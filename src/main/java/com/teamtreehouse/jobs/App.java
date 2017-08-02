package com.teamtreehouse.jobs;

import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.io.IOException;
import java.util.List;

public class App {

  public static void main(String[] args) {
    JobService service = new JobService();
    boolean shouldRefresh = false;
    try {
      if (shouldRefresh) {
        service.refresh();
      }
      List<Job> jobs = service.loadJobs();
      System.out.printf("Total jobs:  %d %n %n", jobs.size());
      explore(jobs);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void explore(List<Job> jobs) {
      printPortlandJobsStream(jobs);
  }

    private static void printPortlandJobsStream(List<Job> jobs) {
    /*
    The stream below is more efficient than the imperative method. It isn't piling
    up all the Oregon jobs, and then filtering those to see which of those equal
    "Portland". Instead, they are both run in a pipeline as a "single fused method".
     */

        jobs.stream()
                .filter(job -> job.getState().equals("OR"))
                .filter(job -> job.getCity().equals("Portland"))
                .forEach(System.out::println);
    }

    private static void printPortlandJobsImperatively(List<Job> jobs) {
        for (Job job : jobs) {
          if (job.getState().equals("OR") && job.getCity().equals("Portland")) {
              System.out.println(job);
          }
        }
    }
}
