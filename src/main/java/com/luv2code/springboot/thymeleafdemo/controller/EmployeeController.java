package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

//	// load employee data
//
//	private List<Employee> theEmployees;

//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}
	private final EmployeeService employeeService;

	public EmployeeController(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/addEmployee")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}
	@GetMapping("/updateEmployee")
	public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel) {

		Employee theEmployee = employeeService.findById(theId);
		if(theEmployee == null) {
			throw new RuntimeException();
		}
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null) {
			throw new RuntimeException();
		}
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";
	}
//	@GetMapping("/delete/{employeeId}")
//	public String deleteEmployeeById(@PathVariable("employeeId") int employeeId) {
//		Employee theEmployee = employeeService.findById(employeeId);
//		if(theEmployee == null) {
//			throw new RuntimeException();
//		}
//		employeeService.deleteById(employeeId);
//		return "redirect:/employees/list";
//	}
	// delete request #2
//	@PostMapping("/delete/{employeeId}")
//	public String deleteEmployeeById(@PathVariable("employeeId") int employeeId) {
//		Employee theEmployee = employeeService.findById(employeeId);
//		if(theEmployee == null) {
//			throw new RuntimeException();
//		}
//		employeeService.deleteById(employeeId);
//		return "redirect:/employees/list";
//	}
}









