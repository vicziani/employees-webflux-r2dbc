package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Flux<EmployeeDto> listEmployees() {
        return employeeRepository.findAll().map(this::toEmployeeDto);
    }

    public Mono<EmployeeDto> findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .map(this::toEmployeeDto);
    }

    public Mono<EmployeeDto> createEmployee(@RequestBody Mono<CreateEmployeeCommand> command) {
        return command
                .map(this::toEmployee)
                .flatMap(employeeRepository::save)
                .map(this::toEmployeeDto);
    }

    public Mono<EmployeeDto> updateEmployee(long id, Mono<UpdateEmployeeCommand> command) {
        return command
                .map(c -> toEmployee(id, c))
                .flatMap(employeeRepository::save)
                .map(this::toEmployeeDto);
    }

    public Mono<Void> deleteEmployee(long id) {
        return employeeRepository.deleteById(id);
    }

    private EmployeeDto toEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName());
    }

    private Employee toEmployee(CreateEmployeeCommand command) {
        return new Employee(command.getName());
    }

    private Employee toEmployee(long id, UpdateEmployeeCommand command) {
        return new Employee(id, command.getName());
    }


}
