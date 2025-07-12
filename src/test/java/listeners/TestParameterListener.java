package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import utilities.ExecutionContext;

public class TestParameterListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        ITestContext context = result.getTestContext();
        String browser = context.getCurrentXmlTest().getParameter("browser");
        String runMode = context.getCurrentXmlTest().getParameter("runMode");

        ExecutionContext.set(browser, runMode);
        System.out.println("TestParameterListener.beforeInvocation - Setting ExecutionContext (Thread: " + Thread.currentThread().getId() + "): Browser='" + browser + "', RunMode='" + runMode + "'");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        ExecutionContext.clear();
        System.out.println("TestParameterListener.afterInvocation - Cleared ExecutionContext (Thread: " + Thread.currentThread().getId() + ")");
    }
}
