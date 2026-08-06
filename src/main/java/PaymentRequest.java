import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {
    @JsonProperty("account_id")
    private String accountId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("reference")
    private String reference;

    private PaymentRequest(Builder builder) {
        this.accountId = builder.accountId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.reference = builder.reference;
    }

    // Getters
    public String getAccountId() { return accountId; }
    public Double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getReference() { return reference; }

    public static class Builder{
        private String accountId;
        private Double amount;
        private String currency;
        private String reference;

        public Builder setAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public PaymentRequest build(){
            return new PaymentRequest(this);
        }
    }
}
