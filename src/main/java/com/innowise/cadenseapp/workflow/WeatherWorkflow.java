package com.innowise.cadenseapp.workflow;

import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;

public interface WeatherWorkflow {
    String TASK_LIST = "Weather";

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 600, taskList = TASK_LIST)
    void getAndStoreWeather(String city);

    @SignalMethod
    void terminate();
}
