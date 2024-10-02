package cn.itheima.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob2 implements Job {

    private static final Logger logger = LoggerFactory.getLogger(HelloJob2.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        //定义工作任务内容
        logger.info("HelloJob222 当前任务执行的时间："+dateStr);

        logger.info((String) jobExecutionContext.getJobDetail().getJobDataMap().get("message"));
        logger.info((String) jobExecutionContext.getTrigger().getJobDataMap().get("message"));
    }
}
