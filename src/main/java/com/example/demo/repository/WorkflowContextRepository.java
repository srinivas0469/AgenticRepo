package com.example.demo.repository;


import com.example.demo.model.WorkflowContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowContextRepository extends JpaRepository<WorkflowContext, String> {
}
