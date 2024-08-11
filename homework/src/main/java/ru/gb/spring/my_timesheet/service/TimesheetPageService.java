package ru.gb.spring.my_timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.my_timesheet.page.TimesheetPageDto;
import ru.gb.spring.my_timesheet.model.Project;
import ru.gb.spring.my_timesheet.model.Timesheet;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public List<TimesheetPageDto> findAll() {
        return timesheetService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.findById(id) // Optional<Timesheet>
                .map(this::convert);
    }

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.findById(timesheet.getProject().getId())
                .orElseThrow();

        TimesheetPageDto timesheetPageParameters = new TimesheetPageDto();
        timesheetPageParameters.setProjectName(project.getName());
        timesheetPageParameters.setProjectId(String.valueOf(project.getId()));
        timesheetPageParameters.setId(String.valueOf(timesheet.getId()));
        timesheetPageParameters.setEmployeeId(String.valueOf(timesheet.getEmployee().getId()));
        timesheetPageParameters.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageParameters.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

        return timesheetPageParameters;
    }

}
