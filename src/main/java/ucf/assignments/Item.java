package ucf.assignments;

public class Item {
    private String name;
    private String serialNum;
    private double value;

    public Item(String name, String serialNum, double value) {
        this.name = name;
        this.serialNum = serialNum;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
