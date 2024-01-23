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
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long transaction_id;
    LocalDate date;
    LocalTime time;
    String principal;
    String operation;

    public Logger(LocalDate date, LocalTime time, String principal, String operation) {
        this.date = date;
        this.time = time;
        this.principal = principal;
        this.operation = operation;
    }
}
