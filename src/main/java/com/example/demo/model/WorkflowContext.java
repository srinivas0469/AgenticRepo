package com.example.demo.model;


import jakarta.persistence.*;




@Entity
@Table(name = "workflow_context")
public class WorkflowContext {

    @Id
    private String contextId;

    @Lob
    @Column(name = "context_data")
    private String contextData;

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getContextData() {
        return contextData;
    }

    public void setContextData(String contextData) {
        this.contextData = contextData;
    }
}
