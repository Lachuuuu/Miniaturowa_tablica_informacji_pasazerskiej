package dwr.MiniaturowaTablica.api.ZTM.cities.warszawa;

import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.JSONClients.TimeTable;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.Displays.DisplayDTOList;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.Displays.WarsawDisplay;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.JSONClients.staticDataLoader;
import dwr.MiniaturowaTablica.api.ZTM.Displays.DisplayDTO;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.TimeTable.TimeComparator;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.TimeTable.WarsawTimeTable;
import dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.WarsawLines;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.JSONClients.Lines.loadLine;

@Repository
public class ZTMWarsawRepository {
    public static DisplayDTOList displayDTOList;
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private TimeTable timeTable;
    private ExecutorService executor;

    public static List<DisplayDTO> getAllDisplays() throws IOException {
        List<WarsawDisplay> displayList;
        List<DisplayDTO> displayDTOS = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        displayList = staticDataLoader.loadDataFromJsonFile("./src/main/resources/static/ztmWarszawa.json");

        if (!displayList.isEmpty()) {
            displayList.forEach(e -> {
                DisplayDTO displayDTO = new DisplayDTO(e);
                displayDTOS.add(displayDTO);
            });
            displayDTOList = new DisplayDTOList(displayDTOS);

            return displayDTOList.displayDTOS;
        }
        return null;
    }


    public List<String> getIdStopsForDisplayCode(String displayCode) {
        List<String> idStops = new ArrayList<>();

        MongoCollection<Document> collection = mongoOperations.getCollection("displays");

        BasicDBObject query = new BasicDBObject();
        query.put("displayCode", displayCode);
        FindIterable<Document> cursor = collection.find(query);

        for (Document document : cursor) {
            idStops.add(WarsawDisplay.prepareIdStop(document.get("idStop1").toString()));
            if(document.getInteger("idStop2")>0) idStops.add(WarsawDisplay.prepareIdStop(document.get("idStop2").toString()));
        }

        return idStops;
    }

    public Set<WarsawLines> getAllLines(String busStopId){
        //get all idStops by busStopId
        List<String> idStops =  getIdStopsForDisplayCode(busStopId);
        Set<WarsawLines> linesForBusStopId = new HashSet<>(); // contains all unique lines
        for (String s : idStops) {
            linesForBusStopId.addAll(loadLine(busStopId, s));
        }

        return linesForBusStopId;
    }


    //all lines with busStopsId
    public Collection<WarsawTimeTable> getTimeTableForDisplay(String displayCode) throws IOException {

        //get all busStopIds
        Set<WarsawLines> warsawLinesSet = getAllLines(displayCode);
        Set<WarsawTimeTable> warsawTimeTableSet = new TreeSet<>(new TimeComparator());

        for(WarsawLines e : warsawLinesSet){
            warsawTimeTableSet.addAll(timeTable.getLineTimetable(displayCode,e.getIdStop(),e.getLinia()));
            }

        LocalTime now = LocalTime.now();
        warsawTimeTableSet.removeIf(e -> LocalTime.parse(e.getEstimatedTime()).isBefore(now));

        //System.out.println(TimeTable.iloscZapytan);

        return warsawTimeTableSet;
    }


}
