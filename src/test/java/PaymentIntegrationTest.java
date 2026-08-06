import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PaymentIntegrationTest {

    private PaymentApiClient apiClient;

    @BeforeClass
    public void setupClient() {
        apiClient = new PaymentApiClient();
    }

    @Test(description = "Verify Payment Service Health Check Endpoint")
    public void testServiceHealth() {
        Response response = apiClient.getHealthCheck();
        assertThat(response.getStatusCode(), is(equalTo(200)));
    }

    @Test(description = "Verify Valid Payment Processing Contract")
    public void testSuccessfulPaymentCreation() {
        PaymentRequest request = new PaymentRequest.Builder()
                .setAccountId("ACC-EUR-99482")
                .setAmount(250.50)
                .setCurrency("EUR")
                .setReference("N26-TEST-PAYMENT")
                .build();

        Response response = apiClient.createPayment(request);

        assertThat(response.getStatusCode(), is(equalTo(201)));
        assertThat(response.jsonPath().getString("status"), is(equalTo("SUCCESS")));
        assertThat(response.jsonPath().getString("transactionId"), startsWith("tx_n26_"));
    }
}
