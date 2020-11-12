package com.rd.scm.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

/*

https://www.callicoder.com/spring-boot-quartz-scheduler-email-scheduling-example/

Note that, Gmail’s SMTP access is disabled by default. To allow this app to send emails using your Gmail account -

Go to https://myaccount.google.com/security?pli=1#connectedapps
Set ‘Allow less secure apps’ to YES

http://localhost:8080/api/scheduleEmail
{
  "email": "vbrahimi@yahoo.com",
  "subject": "Hello",
  "body": "Hello there",
  "dateTime": "2020-08-26T20:20:00",
  "timeZone": "America/Los_Angeles"
}
Content-Type: application/json

1. Scheduler
The Primary API for scheduling, unscheduling, adding, and removing Jobs.

2. Job
The interface to be implemented by classes that represent a ‘job’ in Quartz. It has a single method called execute() where you write the work that needs to be performed by the Job.

3. JobDetail
A JobDetail represents an instance of a Job. It also contains additional data in the form of a JobDataMap that is passed to the Job when it is executed.

Every JobDetail is identified by a JobKey that consists of a name and a group. The name must be unique within a group.

4. Trigger
A Trigger, as the name suggests, defines the schedule at which a given Job will be executed. A Job can have many Triggers, but a Trigger can only be associated with one Job.

Every Trigger is identified by a TriggerKey that comprises of a name and a group. The name must be unique within a group.

Just like JobDetails, Triggers can also send parameters/data to the Job.

5. JobBuilder
JobBuilder is a fluent builder-style API to construct JobDetail instances.

6. TriggerBuilder
TriggerBuilder is used to instantiate Triggers.
*/

@Component
public class EmailJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;
    
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");

        sendMail(mailProperties.getUsername(), recipientEmail, subject, body);
    }

    private void sendMail(String fromEmail, String toEmail, String subject, String body) {
        try {
            logger.info("Sending Email to {}", toEmail);
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);

            mailSender.send(message);
        } catch (MessagingException ex) {
            logger.error("Failed to send email to {}", toEmail);
        }
    }
}