//package com.BK.Expense.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//public class Trip {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    protected long id;
//
//    private Instant startDate;
//
//    private Instant endDate;
//
//    private String reason;
//
//    private String location;
//
//    private int numOfMem;
//
//    @ManyToMany
//    @JoinTable(name = "trip_member", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
//    List<Account> members = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
//    List<ExpenseRequest> expenseRequests;
//
//    void addMember(Account member){
//        members.add(member);
//        ++numOfMem;
//    }
//
//
//}
