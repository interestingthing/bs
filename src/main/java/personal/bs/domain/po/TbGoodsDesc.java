package personal.bs.domain.po;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table spu_desc
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class TbGoodsDesc implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.spuId
     *
     * @mbg.generated
     */
    private Integer spuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.introduction
     *
     * @mbg.generated
     */
    private String introduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.specification_items
     *
     * @mbg.generated
     */
    private String specificationItems;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.custom_attribute_items
     *
     * @mbg.generated
     */
    private String customAttributeItems;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.item_images
     *
     * @mbg.generated
     */
    private String itemImages;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.package_list
     *
     * @mbg.generated
     */
    private String packageList;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spu_desc.sale_service
     *
     * @mbg.generated
     */
    private String saleService;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table spu_desc
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.spuId
     *
     * @return the value of spu_desc.spuId
     *
     * @mbg.generated
     */
    public Integer getSpuid() {
        return spuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.spuId
     *
     * @param spuid the value for spu_desc.spuId
     *
     * @mbg.generated
     */
    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.introduction
     *
     * @return the value of spu_desc.introduction
     *
     * @mbg.generated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.introduction
     *
     * @param introduction the value for spu_desc.introduction
     *
     * @mbg.generated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.specification_items
     *
     * @return the value of spu_desc.specification_items
     *
     * @mbg.generated
     */
    public String getSpecificationItems() {
        return specificationItems;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.specification_items
     *
     * @param specificationItems the value for spu_desc.specification_items
     *
     * @mbg.generated
     */
    public void setSpecificationItems(String specificationItems) {
        this.specificationItems = specificationItems == null ? null : specificationItems.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.custom_attribute_items
     *
     * @return the value of spu_desc.custom_attribute_items
     *
     * @mbg.generated
     */
    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.custom_attribute_items
     *
     * @param customAttributeItems the value for spu_desc.custom_attribute_items
     *
     * @mbg.generated
     */
    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems == null ? null : customAttributeItems.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.item_images
     *
     * @return the value of spu_desc.item_images
     *
     * @mbg.generated
     */
    public String getItemImages() {
        return itemImages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.item_images
     *
     * @param itemImages the value for spu_desc.item_images
     *
     * @mbg.generated
     */
    public void setItemImages(String itemImages) {
        this.itemImages = itemImages == null ? null : itemImages.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.package_list
     *
     * @return the value of spu_desc.package_list
     *
     * @mbg.generated
     */
    public String getPackageList() {
        return packageList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.package_list
     *
     * @param packageList the value for spu_desc.package_list
     *
     * @mbg.generated
     */
    public void setPackageList(String packageList) {
        this.packageList = packageList == null ? null : packageList.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spu_desc.sale_service
     *
     * @return the value of spu_desc.sale_service
     *
     * @mbg.generated
     */
    public String getSaleService() {
        return saleService;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spu_desc.sale_service
     *
     * @param saleService the value for spu_desc.sale_service
     *
     * @mbg.generated
     */
    public void setSaleService(String saleService) {
        this.saleService = saleService == null ? null : saleService.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu_desc
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbGoodsDesc other = (TbGoodsDesc) that;
        return (this.getSpuid() == null ? other.getSpuid() == null : this.getSpuid().equals(other.getSpuid()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getSpecificationItems() == null ? other.getSpecificationItems() == null : this.getSpecificationItems().equals(other.getSpecificationItems()))
            && (this.getCustomAttributeItems() == null ? other.getCustomAttributeItems() == null : this.getCustomAttributeItems().equals(other.getCustomAttributeItems()))
            && (this.getItemImages() == null ? other.getItemImages() == null : this.getItemImages().equals(other.getItemImages()))
            && (this.getPackageList() == null ? other.getPackageList() == null : this.getPackageList().equals(other.getPackageList()))
            && (this.getSaleService() == null ? other.getSaleService() == null : this.getSaleService().equals(other.getSaleService()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu_desc
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSpuid() == null) ? 0 : getSpuid().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getSpecificationItems() == null) ? 0 : getSpecificationItems().hashCode());
        result = prime * result + ((getCustomAttributeItems() == null) ? 0 : getCustomAttributeItems().hashCode());
        result = prime * result + ((getItemImages() == null) ? 0 : getItemImages().hashCode());
        result = prime * result + ((getPackageList() == null) ? 0 : getPackageList().hashCode());
        result = prime * result + ((getSaleService() == null) ? 0 : getSaleService().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu_desc
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", spuid=").append(spuid);
        sb.append(", introduction=").append(introduction);
        sb.append(", specificationItems=").append(specificationItems);
        sb.append(", customAttributeItems=").append(customAttributeItems);
        sb.append(", itemImages=").append(itemImages);
        sb.append(", packageList=").append(packageList);
        sb.append(", saleService=").append(saleService);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}