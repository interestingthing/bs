package personal.bs.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @Author: chenjingang@gauzi.com  2019/5/12 18:21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecSku {


    /**
     * attributeName : 颜色
     * attributeValue : ["黑色","蓝色"]
     */

    private String attributeName;
    private List<String> attributeValue;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<String> getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(List<String> attributeValue) {
        this.attributeValue = attributeValue;
    }
}
