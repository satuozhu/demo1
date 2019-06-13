package com.users.modules.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.common.entity.user.CrSysMessage;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.util.IdGenerator;
import com.users.component.util.RedisUtils;
import com.users.modules.mapper.primary.user.CrSysMessageDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "Kafka测试接口")
public class KafkaTestController {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CrSysMessageDAO crSysMessageDAO;

    //http://192.168.1.21:8003/user/sendPushMessage
    @LogForController
    @ApiOperation(value = "推送消息")
    @RequestMapping(value = "/sendPushMessage", method = RequestMethod.GET)
    public JSONObject sendPushMessage() {
        log.info("==============>>Kafka推送消息push_message");
        long now = System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", idGenerator.nextId());
        jsonObject.put("msgType", 0);
        jsonObject.put("msgTitle", "关于6月1日御医吧系统维护暂停使用公告");
        jsonObject.put("msgContent", "尊敬的用户您好！因服务器升级需要，御医吧系统定于6月1日暂停使用1天，预计系统将于6月2日正常使用，给您造成不便还请谅解。");
        jsonObject.put("msgStatus", false);
        jsonObject.put("userId", -1);
        jsonObject.put("beanId", 0);
        jsonObject.put("createTime", now);
        jsonObject.put("updateTime", now);
        kafkaTemplate.send("push_message", jsonObject.toString());
        return jsonObject;
    }

//    @KafkaListener(topics = "push_message", groupId = "crYuyibaTest")
    @KafkaListener(topics = "push_message")
    public void listenerPushMessage(String message) {
//        String message = record.value();
        log.info("==============>>Kafka接收推送消息push_message：" + message);
//        JSONObject jsonObject = JSONObject.parseObject(message);
//        System.out.println("jsonObject = " + jsonObject);
        CrSysMessage csm = JSONObject.parseObject(message, CrSysMessage.class);
        System.out.println("csm = " + csm);
        crSysMessageDAO.insertSelective(csm);//主键冲突，可能是分区并发的原因

//        List<CrSysMessage> list = new LinkedList<>();//增删
//        boolean isTrue = redisUtils.exists("pushMessage");
//        if (isTrue) {
//            list = (List<CrSysMessage>) redisUtils.get("pushMessage");
//        }
//        list.add(0, csm);//往首位添加元素
//        redisUtils.set("pushMessage", list);
    }

    //http://192.168.1.21:8003/user/sendMessage01
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "Kafka发送消息01")
    @RequestMapping(value = "/sendMessage01", method = RequestMethod.GET)
    public String sendMessage01() {
        log.info("==============>>Kafka发送消息Kafka_cheshi01");
        kafkaTemplate.send("Kafka_cheshi01", "this is a message一二三01");
        return "success";
    }

//    @KafkaListener(topics = "Kafka_cheshi01", groupId = "crYuyibaTest")
    @KafkaListener(topics = "Kafka_cheshi01")
    public void listenerMessage01(String message) {
        log.info("==============>>Kafka接收消息Kafka_cheshi01：" + message);
    }

    //http://192.168.1.21:8003/user/sendMessage02
    @LogForController
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @ApiOperation(value = "Kafka发送消息02")
    @RequestMapping(value = "/sendMessage02", method = RequestMethod.GET)
    public String sendMessage02() {
        log.info("==============>>Kafka发送消息Kafka_cheshi02");
        kafkaTemplate.send("Kafka_cheshi02", "this is a message四五六02");
        return "success";
    }

//    @KafkaListener(topics = "Kafka_cheshi02", groupId = "crYuyibaTest")
    @KafkaListener(topics = "Kafka_cheshi02")
    public void listenerMessage02(ConsumerRecord<?, String> record) {
        System.out.println(record);
        String value = record.value();
        log.info("==============>>Kafka接收消息Kafka_cheshi02：" + value);

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("消费者开始消费message：" + message);
        }
    }

}