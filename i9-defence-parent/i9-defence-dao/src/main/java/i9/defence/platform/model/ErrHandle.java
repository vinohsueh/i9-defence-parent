package i9.defence.platform.model;

import java.util.Date;

public class ErrHandle {
    private Integer id;

    private String handleCon;

    private Integer handleManagerId;

    private Date handleDate;

    private Integer handleState;

    private String eqDeviceId;

    private String eqAddRess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHandleCon() {
        return handleCon;
    }

    public void setHandleCon(String handleCon) {
        this.handleCon = handleCon == null ? null : handleCon.trim();
    }

    public Integer getHandleManagerId() {
        return handleManagerId;
    }

    public void setHandleManagerId(Integer handleManagerId) {
        this.handleManagerId = handleManagerId;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    public String getEqDeviceId() {
        return eqDeviceId;
    }

    public void setEqDeviceId(String eqDeviceId) {
        this.eqDeviceId = eqDeviceId == null ? null : eqDeviceId.trim();
    }

    public String getEqAddRess() {
        return eqAddRess;
    }

    public void setEqAddRess(String eqAddRess) {
        this.eqAddRess = eqAddRess == null ? null : eqAddRess.trim();
    }
}