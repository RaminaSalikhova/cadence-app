package com.innowise.cadenseapp.workflow;

import com.uber.cadence.BadRequestError;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import org.testng.internal.invokers.Arguments;

import java.io.IOException;

public interface WeatherWorkflow {
    String TASK_LIST = "Weather";

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 600, taskList = TASK_LIST)
    void getAndStoreWeather(String city) throws IOException, BadRequestError;

    @SignalMethod
    void terminate();
}
