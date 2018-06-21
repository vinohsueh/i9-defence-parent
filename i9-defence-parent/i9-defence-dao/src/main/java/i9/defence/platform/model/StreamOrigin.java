package i9.defence.platform.model;

import java.util.Date;

import i9.defence.platform.utils.StringUtil;

public class StreamOrigin {
    private Integer id;

    private String jsonstr;

    private Date submitDate;
    
    private String channelId;
    
    public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsonstr() {
        return jsonstr;
    }

    public void setJsonstr(String jsonstr) {
        this.jsonstr = jsonstr == null ? null : jsonstr.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
    
    public String getSubmitDateStr() {
    	if(submitDate!=null) {
    		return StringUtil.dateToStringByRep(submitDate, "yyyy-MM-dd HH:mm:ss");
    	}
        return "";
    }
}