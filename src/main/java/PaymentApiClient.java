import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PaymentApiClient {

    private final RequestSpecification spec;

    public PaymentApiClient() {
        this.spec = new RequestSpecBuilder().setBaseUri(EnvironmentConfig.getURL())
                .setContentType(ContentType.JSON).addFilter(new RequestLoggingFilter()).addFilter(new ResponseLoggingFilter())
                .build();
    }

    public Response createPayment(PaymentRequest payload){
        return given().spec(spec).body(payload).when().post("/api/v1/payments");

    }

    public Response getHealthCheck(){
        return given().spec(spec).when().get("/__admin/health");

    }
}
