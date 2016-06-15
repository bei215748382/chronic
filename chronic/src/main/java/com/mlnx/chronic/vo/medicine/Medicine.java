package com.mlnx.chronic.vo.medicine;

/**
 * Created by Administrator on 2016/5/9.
 * 药品信息查询 网站
 * http://apistore.baidu.com/apiworks/servicedetail/866.html
 * 药品信息
 */
public class Medicine {

    private String id;

    private String drugName;    // 药品名称
    private String img; // 药品图片
    private String blfy; //不良反应
    private String jj;  // 禁忌
    private String manu;    // 药厂公司
    private String pzwh;    // "国药准字H13024049",
    private String syz; // 用于感冒发热、头痛、神经痛及风湿痛等。
    private String zc;  // 密闭，干燥处保存
    private String zycf;    // 药品组成

    private String yfyl;    // 临时变量
    
    

    public Medicine() {

    }

    public Medicine(Medicine medicine) {
        id = medicine.getId();
        img = medicine.getImg();
        blfy = medicine.getBlfy();
        jj = medicine.getJj();
        manu = medicine.getManu();
        pzwh = medicine.getPzwh();
        syz = medicine.getSyz();
        zc = medicine.getZc();
        zycf = medicine.getZycf();
        yfyl = medicine.getYfyl();
        drugName = medicine.getDrugName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBlfy() {
        return blfy;
    }

    public void setBlfy(String blfy) {
        this.blfy = blfy;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getSyz() {
        return syz;
    }

    public void setSyz(String syz) {
        this.syz = syz;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getZycf() {
        return zycf;
    }

    public void setZycf(String zycf) {
        this.zycf = zycf;
    }


    public String getYfyl() {
        return yfyl;
    }

    public void setYfyl(String yfyl) {
        this.yfyl = yfyl;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", drugName='" + drugName + '\'' +
                ", img='" + img + '\'' +
                ", blfy='" + blfy + '\'' +
                ", jj='" + jj + '\'' +
                ", manu='" + manu + '\'' +
                ", pzwh='" + pzwh + '\'' +
                ", syz='" + syz + '\'' +
                ", zc='" + zc + '\'' +
                ", zycf='" + zycf + '\'' +
                ", yfyl='" + yfyl + '\'' +
                '}';
    }
}
