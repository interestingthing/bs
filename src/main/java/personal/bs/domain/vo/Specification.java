package personal.bs.domain.vo;

import lombok.Data;
import personal.bs.domain.po.SpecPO;
import personal.bs.domain.po.SpecValuePO;

import java.io.Serializable;
import java.util.List;

/**
 * 组合实体类
 */
@Data
public class Specification implements Serializable {

    // 规格实体
    private SpecPO specPO;

    // 规格选项集合
    private List<SpecValuePO> specValuePOList;

}
