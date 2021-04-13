package 大厂面试;

/**
 * 枚举 可以把他当做相当于数据库的表
 */
public enum CountryEnum {

    /**
     *
     */
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer recode;
    private String reMessage;

    public Integer getRecode() {
        return recode;
    }

    public void setRecode(Integer recode) {
        this.recode = recode;
    }

    public String getReMessage() {
        return reMessage;
    }

    public void setReMessage(String reMessage) {
        this.reMessage = reMessage;
    }

    CountryEnum(Integer recode, String reMessage) {
        this.recode = recode;
        this.reMessage = reMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum country: countryEnums){
            if (index == country.getRecode()){
                return country;
            }
        }
        return null;
    }
}
