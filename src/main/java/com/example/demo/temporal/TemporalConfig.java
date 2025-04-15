package com.example.demo.temporal;

import com.example.demo.temporal.AgenticWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TemporalConfig {

    @Bean
    public WorkflowClient workflowClient() {
        return WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient client) {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("AGENTIC_TASK_QUEUE");
        worker.registerWorkflowImplementationTypes(AgenticWorkflowImpl.class);
        return factory;
    }
}
