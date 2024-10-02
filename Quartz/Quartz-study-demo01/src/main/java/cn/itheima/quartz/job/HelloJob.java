package cn.itheima.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        //定义工作任务内容
        System.out.println("当前任务执行的时间："+dateStr);

        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("message"));
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("message"));
    }
}
