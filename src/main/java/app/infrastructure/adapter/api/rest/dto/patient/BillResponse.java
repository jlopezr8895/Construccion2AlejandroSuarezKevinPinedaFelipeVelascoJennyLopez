package app.infrastructure.adapter.api.rest.dto.patient;

public class BillResponse {
    private String bill;

    public BillResponse() {
    }

    public BillResponse(String bill) {
        this.bill = bill;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
