package com.users.modules.pres.controller;

import com.common.entity.pres.CrPrescription;
import com.sun.javafx.sg.prism.NGShape;
import com.users.component.entity.Message;
import com.users.modules.pres.service.ICrPrescriptionService;
import com.users.modules.role.service.impl.SysModuleServiceImpl;
import com.users.modules.user.responseBody.crAppointment.SelectDoctorInfoTopResBody;
import com.users.modules.user.service.AppointmentService;
import com.users.modules.user.service.CrUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/24.
 */
@Controller
@RequestMapping(value = "/pres")
@Slf4j
@CrossOrigin(origins = "*")//cors解决ajax请求跨域的问题
@Api(description = "处方接口")
public class CrPrescriptionController {

    @Autowired
    private ICrPrescriptionService prescriptionService;
    @Autowired
    private AppointmentService appointmentService;
    /**
     * 根据患者编号查询所有处方信息
     */
    @ResponseBody
    @ApiOperation("根据患者编号查询所有处方信息")
    @RequestMapping(value = "/queryPresList/{userId}",method = RequestMethod.GET)
    public Message queryPresList(@ApiParam("路径中传入用户编号") @PathVariable Long userId){
        Message message = new Message();
        List<CrPrescription> list = prescriptionService.getUserPres(userId);
        message.setCode(200);
        message.setData(list);
        return message;
    }
//    /**
//     * 根据处方编号查询
//     */
//    @ResponseBody
//    @ApiOperation("根据处方编号查询")
//    @RequestMapping(value = "/queryPresDetail/{presId}",method = RequestMethod.GET)
//    public Message queryPresDetail(@ApiParam("路径中传入处方编号") @PathVariable Long presId){
//        Message message = new Message();
//        CrPrescription detail = prescriptionService.getPresDetails(presId);
//        message.setCode(200);
//        message.setData(detail);
//        return message;
//    }
    /**
     * 根据处方编号,查询h5页面
     */
    @ApiOperation("根据处方编号,查询h5页面")
    @RequestMapping(value = "/h5/{presId}",method = RequestMethod.GET)
    public ModelAndView h5(@ApiParam("路径中传入处方编号") @PathVariable Long presId, Model model){
        CrPrescription detail = prescriptionService.getPresDetails(presId);
        SelectDoctorInfoTopResBody doctor =  appointmentService.selectDoctorInfoById(detail.getDoctorId());
        model.addAttribute("detail",detail);
        model.addAttribute("doctor",doctor);
        return  new ModelAndView("views/presDetail","presModel",model);
    }
}
