package com.users.modules.user.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.common.entity.user.CrSysMessage;
import com.users.component.util.IdGenerator;
import com.users.component.util.RedisUtils;
import com.users.modules.mapper.primary.user.CrSysMessageDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 消息定时任务类
 *
 * @author lyl
 * @date 2019/5/29 10:58
 */
@Component
@Slf4j
public class PushMessage {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CrSysMessageDAO crSysMessageDAO;

//    //https://blog.csdn.net/onedaycbfly/article/details/79093829
////    @Scheduled(cron = "0/30 * * * * ?") // 每隔30秒执行一次（秒 分 时 日 月 ? 年）
//    @Scheduled(cron = "0 0/60 * * * ?") // 每隔60分钟执行一次（秒 分 时 日 月 ? 年）
//    public void removePushMessage() {
//        //Thread.sleep(1000 * 5);//延时5s
//        log.info("执行移除历史推送消息定时任务...");
//        redisUtils.remove("pushMessage");
//    }
//
//    @Scheduled(cron = "0/30 * * * * ?") // 每隔30秒执行一次（秒 分 时 日 月 ? 年）
////    @Scheduled(cron = "0 0/3 * * * ?") // 每隔3分钟执行一次（秒 分 时 日 月 ? 年）
//    public void savePushMessage() {
//        log.info("执行推送系统消息定时任务...");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long now = System.currentTimeMillis();
//        CrSysMessage csm = new CrSysMessage();
//        csm.setId(idGenerator.nextId());
//        csm.setMsgType(0);
//        csm.setMsgTitle("当前时间提醒公告");
//        csm.setMsgContent("现在是北京时间" + sdf.format(new Date(now)));
//        csm.setMsgStatus(false);//消息状态(0未读、1已读)
//        csm.setUserId(-1l);
//        csm.setBeanId(0l);
//        csm.setCreateTime(now);
//        csm.setUpdateTime(now);
//        crSysMessageDAO.insertSelective(csm);
//
////        List<CrSysMessage> list = new LinkedList<>();//增删
////        boolean isTrue = redisUtils.exists("pushMessage");
////        if (isTrue) {
////            list = (List<CrSysMessage>) redisUtils.get("pushMessage");
////        }
////        list.add(0, csm);//往首位添加元素
////        redisUtils.set("pushMessage", list);
//    }

}