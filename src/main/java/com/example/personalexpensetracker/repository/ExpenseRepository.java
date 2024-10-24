package com.example.personalexpensetracker.repository;

import com.example.personalexpensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <Expense, Long> {

}
