package TestPackage;

import com.shaft.driver.SHAFT;
import org.apache.http.entity.ContentType;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.dashboard.DashboardVisualizer.dashboardVisualizer;

public class AdminPortalTests {
    /**
     * https://abstracta.github.io/jmeter-java-dsl/
     */
    String uri = "https://oyn-gateway-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/Identity/api/Identity/Login";
    String loginBody = """
            {email: "store@admin.com", password: "P@ssw0rd"}""";

    @Test
    public void happyScenario() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup()
                        .rampToAndHold(5000, Duration.ofSeconds(30), Duration.ofSeconds(30))
                        .rampTo(0, Duration.ofSeconds(30))
                        .children(
                        httpSampler(uri).post(loginBody, ContentType.APPLICATION_JSON).children(
                                responseAssertion().containsSubstrings("OK")
                        )
                ),
                dashboardVisualizer() //comment this line for unattended execution
        ).run();

        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(1));
//        assertThat(stats.overall().errorsCount()).isLessThan(10);
    }
}
