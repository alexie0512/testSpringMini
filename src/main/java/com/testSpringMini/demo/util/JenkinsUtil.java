package com.testSpringMini.demo.util;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Job;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Jenkins 工具类
 *
 * @author Alexie on 2021/4/26
 * @ClassName JenkinsUtil
 * @Description TODO
 * @Version 1.0
 */
public class JenkinsUtil {

    public static void build(String jobName,String userId,String remark,String testCommand) throws IOException, URISyntaxException {

        ClassPathResource classPathResource= new ClassPathResource("JenkinsConfigDir/jenkins_test.xml");
        InputStream inputStream=classPathResource.getInputStream();
        String jobConfigXml = FileUtil.getText(inputStream);
        String baseUrl= "http://stuq.ceshiren.com:8080/";
        String userName="hogwarts";
        String passWord="hogwarts123";
        //String jobName="Alexie_test01";

        JenkinsHttpClient jenkinsHttpClient= new JenkinsHttpClient(new URI(baseUrl),userName,passWord);
        //String jenkinsVesion=jenkinsHttpClient.getJenkinsVersion();

        JenkinsServer jenkinsServer = new JenkinsServer(jenkinsHttpClient);
        Map<String, Job> jobMap= jenkinsServer.getJobs(); //获取所有的Jenkins Job
        jenkinsServer.updateJob(jobName,jobConfigXml,true); //更新Jenkins配置
        //jenkinsServer.createJob(jobName,jobConfigXml,true);
        Map<String,String> map=new HashMap<>();
//        map.put("userId","01");
//        map.put("remark","Alexie_测试接口调用Jenkins");
//        map.put("testCommand","pwd");
        map.put("userId",userId);
        map.put("remark",remark);
        map.put("testCommand",testCommand);

        Job job = jobMap.get(jobName);
        job.build(map,true);
        //job.setClient(jenkinsHttpClient);





    }

}
