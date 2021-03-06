package domain.smartphones
        ;

import java.util.List;

/**
 * Created by amaxilatis on 28/10/2015.
 */
public class SmartphoneData {

    private String entity_id;
    private String  attribute_id;
    private String function;
    private String from;
    private String to;
    private List<List<Object>> readings;

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<List<Object>> getReadings() {
        return readings;
    }

    public void setReadings(List<List<Object>> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "SmartphoneData{" +
                "entity_id='" + entity_id + '\'' +
                ", attribute_id='" + attribute_id + '\'' +
                ", function='" + function + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", readings=" + readings +
                '}';
    }
}
