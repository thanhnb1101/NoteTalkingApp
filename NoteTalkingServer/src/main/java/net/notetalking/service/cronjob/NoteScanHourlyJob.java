package net.notetalking.service.cronjob;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.notetalking.NoteTalkingServerApplication;
import net.notetalking.service.NoteService;

public class NoteScanHourlyJob implements Job {
	NoteService noteService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("DownloadFileJob --->>> Time is " + new Date());
		noteService = NoteTalkingServerApplication.context.getBean(NoteService.class);
		noteService.deleteHourly();
	}

}
