package com.innowise.cadenseapp;

import com.innowise.cadenseapp.activity.FetchWeatherActivity;
import com.innowise.cadenseapp.activity.FetchWeatherActivityImpl;
import com.innowise.cadenseapp.workflow.WeatherWorkflow;
import com.innowise.cadenseapp.workflow.WeatherWorkflowImpl;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;

import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.AssertJUnit.assertEquals;

public class FetchWeatherActivityTest {
    private TestWorkflowEnvironment testEnv;
    private Worker worker;
    private WorkflowClient workflowClient;

    @BeforeTest
    public void setUp() {
        testEnv = TestWorkflowEnvironment.newInstance();
        worker = testEnv.newWorker(WeatherWorkflow.TASK_LIST);
        worker.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);

        workflowClient = testEnv.newWorkflowClient();
    }

    @AfterTest
    public void tearDown() {
        testEnv.close();
    }
//
//    @Test
//    public void testActivityImpl() {
//        worker.registerActivitiesImplementations(new FetchWeatherActivityImpl());
//        testEnv.start();
//
//        // Get a workflow stub using the same task list the worker uses.
//        WeatherWorkflow workflow = workflowClient.newWorkflowStub(WeatherWorkflow.class);
//        // Execute a workflow waiting for it to complete.
//        void workflowId = workflow.getAndStoreWeather();
//        assertEquals("53b0d56a-4516-47ad-8c38-f3816b0d3665", workflowId);
//    }
//
//    @Test
//    public void testMockedActivity() {
//        GreetingActivities activities = mock(GreetingActivities.class);
//        when(activities.composeGreeting("Hello", "World")).thenReturn("Hello World!");
//        worker.registerActivitiesImplementations(activities);
//        testEnv.start();
//
//        // Get a workflow stub using the same task list the worker uses.
//        GreetingWorkflow workflow = workflowClient.newWorkflowStub(GreetingWorkflow.class);
//        // Execute a workflow waiting for it to complete.
//        String greeting = workflow.getGreeting("World");
//        assertEquals("Hello World!", greeting);
//    }
}
