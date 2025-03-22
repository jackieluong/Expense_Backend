//package com.BK.Expense.dto;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.Instant;
//import java.util.List;
//
//@Getter
//@Setter
//public class TripCreateRequest {
//
//
//    private ExpenseRequest expenseInfo;
//
//    private TripRequest tripInfo;
//
//    @Getter
//    @Setter
//    public static class ExpenseRequest{
//        private long employeeId;
//        private float amount;
//        private String description;
//
//    }
//
//    @Getter
//    @Setter
//    public static class TripRequest{
//        private Instant startDate;
//        private Instant endDate;
//        private String reason;
//
//        @JsonProperty("place")
//        private String location;
//
//        private List<Member> members;
//    }
//
//    @Getter
//    @Setter
//    public static class Member{
//        private long id;
//    }
//
//}
