package dwr.MiniaturowaTablica.api.ZTM.cities.gdansk.Models.Stop_;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GeneralInfoStops
{
    private   String lastUpdate;
    private List<Stop> stops;

    @Override
    public String toString() {
        return "GeneralInfoStops{" +
                "lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
