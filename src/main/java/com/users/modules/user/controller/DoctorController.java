package com.users.modules.user.controller;

import com.common.entity.user.CrCase;
import com.common.entity.user.CrDoctorComment;
import com.common.entity.user.CrDoctorInfo;
import com.users.modules.user.service.ICrCaseService;
import com.users.modules.user.service.ICrDoctorCommentService;
import com.users.modules.user.service.IDoctorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by xiangchao on 2019/5/27.
 */
@Controller
@RequestMapping("/user/doctor")
@CrossOrigin(origins = "*")
@Api(description = "医生接口")
public class DoctorController {

    @Autowired
    private IDoctorInfoService doctorInfoService;

    @Autowired
    private ICrCaseService caseService;

    @Autowired
    private ICrDoctorCommentService crDoctorCommentService;

    @ApiOperation(value = "根据医生编号查询医生详情")
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getInfoById(@ApiParam("医生编号") @PathVariable Long id, Model model){
        CrDoctorInfo doctorInfo = doctorInfoService.getDoctorDetail(id);
        model.addAttribute("detail",doctorInfo);
        return new ModelAndView("views/userDetail","doctorModel",model);
    }
    @ApiOperation(value = "根据医生编号查询医生案例")
    @RequestMapping(value = "/case/{id}",method = RequestMethod.GET)
    public ModelAndView getCaseById(@ApiParam("医生编号") @PathVariable Long id, Model model){
        List<CrCase> list = caseService.getCaseByDoctorId(id);
        model.addAttribute("list",list);
        return new ModelAndView("views/caseDetail","caseModel",model);
    }
    @ApiOperation(value = "根据医生编号查询留言信息")
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ModelAndView getCommentById(@ApiParam("医生编号") @PathVariable Long id, Model model){
        List<CrDoctorComment> list = crDoctorCommentService.queryCommentsById(id);
        model.addAttribute("list",list);
        return new ModelAndView("views/doctorComment","commentModel",model);
    }
}
