package training.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("employees")
public class Employee {

    @Id
    private Long id;

    @Column("emp_name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
