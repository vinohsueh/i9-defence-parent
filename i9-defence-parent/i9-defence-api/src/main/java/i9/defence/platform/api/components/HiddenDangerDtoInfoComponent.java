package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.vo.HiddenDangerDto;

/**
 * 创建时间：2018年4月14日 上午9:34:49
 * 
 * @author lby
 * @version
 * 
 */
public class HiddenDangerDtoInfoComponent {
    private HiddenDangerDto hiddenDangerDto;

    public HiddenDangerDtoInfoComponent setHiddenDangerDto(HiddenDangerDto hiddenDangerDto) {
        this.hiddenDangerDto = hiddenDangerDto;
        return this;
    }

    public JSONObject build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hiddenCount", hiddenDangerDto.getHiddeCount());
        jsonObject.put("warningCount", hiddenDangerDto.getWarningCount());
        return jsonObject;
    }
}
