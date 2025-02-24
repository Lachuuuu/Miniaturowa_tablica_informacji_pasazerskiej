package dwr.MiniaturowaTablica.api.ZTM.cities.warszawa.Models.TimeTable;

public class WarsawTimeTable {
   private String estimatedTime;
   private String headsign;
   private String tripId;

   public WarsawTimeTable(String estimatedTime, String headsign, String tripId) {
      setEstimatedTime(estimatedTime);
      setHeadsign(headsign);
      setTripId(tripId);
   }

   public String getEstimatedTime() {
      return estimatedTime;
   }

   public void setEstimatedTime(String estimatedTime) {
      Integer estimatedHour = Integer.parseInt(estimatedTime.substring(0,2).toString());
      if(estimatedHour>23){
         estimatedHour = estimatedHour-24;
         if(estimatedHour<9){
            estimatedTime = "0"+estimatedHour.toString() + estimatedTime.substring(2);
         }
         else{
            estimatedTime = estimatedHour.toString() + estimatedTime.substring(2);
         }

      }
      this.estimatedTime = estimatedTime;
   }

   public String getHeadsign() {
      return headsign;
   }

   public void setHeadsign(String headsign) {
      this.headsign = headsign;
   }

   public String getTripId() {
      return tripId;
   }

   public void setTripId(String tripId) {
      this.tripId = tripId;
   }

   @Override
   public String toString() {
      return  "czas='" + getEstimatedTime() + '\'' +
              ", kierunek='" + getHeadsign() + '\'' +
              ", linia='" + getTripId() + '\'' ;
   }
}

