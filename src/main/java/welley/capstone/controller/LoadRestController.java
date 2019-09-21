package welley.capstone.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/load-batch")
public class LoadRestController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> timeMap = new HashMap<>();
        timeMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(timeMap);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution Status: " + jobExecution.getStatus());

        System.out.println("Batch is running...");
        while(jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }
}
