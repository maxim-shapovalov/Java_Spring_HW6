package ru.gb.spring.my_timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.spring.my_timesheet.model.Employee;
import ru.gb.spring.my_timesheet.model.Project;
import ru.gb.spring.my_timesheet.model.Timesheet;
import ru.gb.spring.my_timesheet.repository.EmployeeRepository;
import ru.gb.spring.my_timesheet.repository.ProjectRepository;
import ru.gb.spring.my_timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application {
	/**
	 * spring-data - ...
	 * spring-data-jdbc - зависимость, которая предоставляет удобные преднастроенные инструемнты
	 * для работы c реляционными БД
	 * spring-data-jpa - зависимость, которая предоставляет удобные преднастроенные инструемнты
	 * для работы с JPA
	 *      spring-data-jpa
	 *       /
	 *     /
	 *   jpa   <------------- hibernate (ecliselink ...)
	 *
	 * spring-data-mongo - завимость, которая предоставляет инструменты для работы с mongo
	 * spring-data-aws
	 *
	 *
	 */

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

//		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
//		jdbcTemplate.execute("delete from project");

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepo.save(project);
		}

		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);
		Generator generator = new Generator();
		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setFirstName(generator.generateMaleFirstName());
			employee.setLastName(generator.generateMaleLastName());
			employee.setPhone(generator.generatePhone());
			employee.setDepartment(generator.generateDepartment());
			employeeRepo.save(employee);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDateTime createdAt = LocalDateTime.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1).plusHours(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProject(projectRepo.getReferenceById(ThreadLocalRandom.current().nextLong(1, 6)));
			timesheet.setEmployee(employeeRepo.getReferenceById(ThreadLocalRandom.current().nextLong(1,6)));
//			timesheet.setCreatedAt(createdAt));
			timesheet.setCreatedAt(LocalDate.from(createdAt));
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}
	}
}
