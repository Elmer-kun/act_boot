package com.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elmer
 * @date 2022/7/9 16:30
 */
@RestController
public class DeploymentBpmn {

    @Autowired
    RepositoryService repositoryService;

    @RequestMapping(path = "/deployment")
    public String DeploymentBpmnXml(@RequestBody String xmlStr){
        try {
            JSONObject jsonObject = JSONObject.parseObject(xmlStr);
            xmlStr = jsonObject.getString("xmlStr");
            repositoryService.createDeployment().addString("demo.bpmn", xmlStr).deploy();
            return "success";
        }catch (Exception e){
            System.out.println(e);
            return "bad";
        }
    }

    @RequestMapping(path = "/getDeployment")
    public String getDeployment(@RequestBody String data){
        try{
            JSONObject jsonObject = JSONObject.parseObject(data);
            String d = jsonObject.getString("processId");

            Deployment deployment = repositoryService.createDeployment().addString("demo.bpmn", d).deploy();
            return "Success";
        }catch (Exception e){
            System.out.println(e);
            return "bad";
        }
    }

    @RequestMapping(path = "/getUsers")
    public JSONObject getUsers(){
        JSONObject users = new JSONObject();
        JSONArray jsonArray = new JSONArray(); //todo:从数据库获取用户数据  当前只是测试功能
        jsonArray.add("张三");
        jsonArray.add("lisi");
        jsonArray.add("wangwu");
        users.put("users", jsonArray);
        return users;
    }

    @RequestMapping(path = "/getGroups")
    public JSONObject getGroups(){
        JSONObject gropus = new JSONObject();
        JSONArray jsonArray = new JSONArray(); //todo:从数据库获取用户数据  当前只是测试功能
        jsonArray.add("group");
        jsonArray.add("组");
        jsonArray.add("部门");
        gropus.put("groups", jsonArray);
        return gropus;
    }

}
