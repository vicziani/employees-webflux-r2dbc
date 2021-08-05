package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Flux<EmployeeDto> listEmployees() {
        return employeeService.listEmployees();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EmployeeDto>> findEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeDto> createEmployee(@RequestBody Mono<CreateEmployeeCommand> command) {
        return employeeService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public Mono<EmployeeDto> updateEmployee(@PathVariable("id") long id, @RequestBody Mono<UpdateEmployeeCommand> command) {
        return employeeService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployee(@PathVariable("id") long id) {
        return employeeService.deleteEmployee(id);
    }

}
