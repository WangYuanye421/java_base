package org.wyy.tech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wyy.tech.entity.biz.Employee;

/**
 * @author Wyy
 **/
@Controller
@RequestMapping("push")
public class PushController {

    @GetMapping("pushEmp")
    public @ResponseBody
    Employee pushEmployee(){
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("wyy");
        emp.setEmpNo("001");
        return emp;
    }
}
