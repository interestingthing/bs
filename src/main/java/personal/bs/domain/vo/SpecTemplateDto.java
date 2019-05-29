package personal.bs.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: chenjingang@gauzi.com  2019/5/3 22:41
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SpecTemplateDto {

    private Integer id;
    /**
     * extendAttributes : [{"text":"123"},{"text":"123"}]
     * name : 规格名称
     * specValueIds : [{"id":1,"text":"颜色"}]
     */
//[{“name”:”规格名称”,”extendAttributes”:[“规格选项1”,“规格选项2”.... ]  } , ....  ]

    private String text;
    /**
     * 扩展属性
     */
    private List<ExtendAttributesBean> extendAttributes;
    /**
     * 关联属性
     */
    private List<SpecValueIdsBean> specValueIds;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtendAttributesBean {
        /**
         * text : 123
         */

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpecValueIdsBean {
        /**
         * id : 1
         * text : 颜色
         */

        private int id;
        private String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
