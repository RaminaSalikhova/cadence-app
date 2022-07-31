package com.innowise.cadenseapp.controller;

import com.innowise.cadenseapp.dto.WorkflowResponse;
import com.innowise.cadenseapp.workflow.WeatherWorkflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow/weather")
public class WeatherController {

    private final WorkflowClient workflowClient;

    public WeatherController(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @GetMapping
    public ResponseEntity<WorkflowResponse> startWorkflow() {
        WeatherWorkflow workflow = workflowClient.newWorkflowStub(WeatherWorkflow.class);
        WorkflowExecution execution = WorkflowClient.start(workflow::getAndStoreWeather);
        WorkflowResponse responseBody = WorkflowResponse.success(execution.getWorkflowId());

        return ResponseEntity.ok(responseBody);
    }
}
