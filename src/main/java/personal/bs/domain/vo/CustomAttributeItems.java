package personal.bs.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: chenjingang@gauzi.com  2019/5/12 18:25
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomAttributeItems {

    /**1
     * text : 全手工
     * value : 手工
     */

    private String text;
    private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
