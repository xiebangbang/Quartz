package cn.itheima.quartz.job.test;

import cn.itheima.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class HelloSchedulerDemo {

    public static void main(String[] args) throws Exception {
        //1、从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("message","打印日志")
                .build();

        System.out.println("JobDetail实例的属性："+job.getKey().getName()+";"+job.getKey().getGroup()+";"+job.getJobClass().getName());

        //3、定义触发器，马上执行，然后重复执行
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()
                .usingJobData("message","simple触发器")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(3))
                .build();

        //4、使用触发器调度任务的执行
         scheduler.scheduleJob(job, trigger);

        //5、开启
        scheduler.start();

        //关闭
        //scheduler.shutdown(true);

    }

}
