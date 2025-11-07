package app.infrastructure.adapter.api.rest.dto.inventory;

public class ProcedureResponse {
    private String id;
    private String name;
    private String description;
    private double cost;
    private boolean requiresSpecialist;

    public ProcedureResponse() {
    }

    public ProcedureResponse(String id, String name, String description, double cost, boolean requiresSpecialist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }
}
