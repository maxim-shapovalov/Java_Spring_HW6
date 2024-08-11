package ru.gb.spring.my_timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Описание структуры json-ответа на REST-запросы.
 * Т.е. запросы, ответ на которые - JSON.
 */
// эта аннотация ломбок позволяет не прописывать геттеры и сеттеры, они идут вместе с ней
@Data
@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include //  Аннотация говорит методам equals и hashCode с каким полем работать(т.е. в данном
    // случае сравнение будет по полю Id)
    private Long id;
//    private Long projectId;
//    private Long employeeId;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Employee employee;
    private Integer minutes;
//    private LocalDateTime createdAt;
    private LocalDate createdAt;
}
