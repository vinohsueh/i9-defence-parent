package i9.defence.platform.model;

public class EquipmentFailure {
    private Integer id;

    private String conductor;

    private Integer dealState;

    private Integer eqmonitorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor == null ? null : conductor.trim();
    }

    public Integer getDealState() {
        return dealState;
    }

    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }

    public Integer getEqmonitorId() {
        return eqmonitorId;
    }

    public void setEqmonitorId(Integer eqmonitorId) {
        this.eqmonitorId = eqmonitorId;
    }
}