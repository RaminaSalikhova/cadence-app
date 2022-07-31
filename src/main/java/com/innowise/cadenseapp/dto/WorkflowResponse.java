package com.innowise.cadenseapp.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class WorkflowResponse {
    String workflowId;

    Optional<String> errorMessage;

    public WorkflowResponse(String workflowId) {
        this.workflowId=workflowId;
    }

    public WorkflowResponse(String workflowId, Optional<String> errorMessage) {
        this.workflowId=workflowId;
        this.errorMessage=errorMessage;
    }
    public static WorkflowResponse success(String workflowId) {
        return new WorkflowResponse(workflowId );
    }

    public static WorkflowResponse error(String workflowId, String errorMessage) {
        return new WorkflowResponse(workflowId, Optional.of(errorMessage));
    }
}