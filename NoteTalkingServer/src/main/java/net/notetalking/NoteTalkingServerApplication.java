package net.notetalking;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import net.notetalking.service.NoteService;
import net.notetalking.service.cronjob.NoteScanHourlyJob;
import net.notetalking.service.impl.NoteServiceImpl;

@SpringBootApplication
public class NoteTalkingServerApplication {
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(NoteTalkingServerApplication.class, args);
		runCronJob();
	}
	
	private static void runCronJob() {
		try {
			JobDetail job = JobBuilder.newJob(NoteScanHourlyJob.class).withIdentity("job1", "group1").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1")
					.startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * * *")).build();//run every hour: 0 0 * ? * * *. Every minute: 0 * 0 ? * * *
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
