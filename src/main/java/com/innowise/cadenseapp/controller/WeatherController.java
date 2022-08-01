package com.innowise.cadenseapp.controller;

import com.innowise.cadenseapp.dto.WorkflowResponse;
import com.innowise.cadenseapp.workflow.WeatherWorkflow;
import com.uber.cadence.BadRequestError;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/workflow/weather")
public class WeatherController {

    private final WorkflowClient workflowClient;

    public WeatherController(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WorkflowResponse> startWorkflow(@PathVariable("city") String city) {

        WeatherWorkflow workflow = workflowClient.newWorkflowStub(WeatherWorkflow.class);
        WorkflowExecution execution = WorkflowClient.start(() -> {
            try {
                workflow.getAndStoreWeather(city);
            } catch (IOException | BadRequestError e) {
                throw new RuntimeException(e);
            }
        });
        WorkflowResponse responseBody = WorkflowResponse.success(execution.getWorkflowId());

        return ResponseEntity.ok(responseBody);
    }


    @DeleteMapping("/{workflowId}")
    public ResponseEntity<WorkflowResponse> terminateWorkflow(@PathVariable String workflowId) {
        WeatherWorkflow workflow = workflowClient.newWorkflowStub(WeatherWorkflow.class, workflowId);
        workflow.terminate();

        WorkflowResponse responseBody = WorkflowResponse.success(workflowId);

        return ResponseEntity.ok(responseBody);
    }

}
