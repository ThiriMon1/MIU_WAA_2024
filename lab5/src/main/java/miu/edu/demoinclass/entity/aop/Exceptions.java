package miu.edu.demoinclass.entity.aop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exceptions{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long transaction_id;
    LocalDate localDate;
    LocalTime localTime;
    String principle;
    String operation;
    String execution_type;

    public Exceptions(LocalDate localDate, LocalTime localTime, String principle, String operation, String execution_type) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.principle = principle;
        this.operation = operation;
        this.execution_type = execution_type;
    }
}
