package com.devops.employee.controller;

import com.devops.employee.entity.Employee;
import com.devops.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("employees",
                service.getAllEmployees());

        return "index";
    }

    @GetMapping("/employees/new")
    public String newEmployee(Model model){

        model.addAttribute("employee",
                new Employee());

        return "add-employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee){

        service.saveEmployee(employee);

        return "redirect:/";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id,
                               Model model){

        model.addAttribute("employee",
                service.getEmployeeById(id));

        return "edit-employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute Employee employee){

        service.updateEmployee(id,
                employee);

        return "redirect:/";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){

        service.deleteEmployee(id);

        return "redirect:/";
    }

}