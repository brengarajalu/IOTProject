package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aditi on 19/04/15.
 */
public class Utility {

    public String getFormattedPlaces(String getData){
        String places="";
        int i=0;

        getData = getData.replace("[","");
        getData = getData.replace("]","");
        getData = getData.replace("{","");
        getData = getData.replace("}","");
        getData = getData.replace("\"","");

        List<String> listOfPlaces = new ArrayList<String>(Arrays.asList(getData.split(",")));

        places += "<div class=\"table-responsive\">\n" +
                "  <table class=\"table table-striped\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th></tr>\n" +
                "    </thead>\n" +
                "    <tbody>";
        for(String str: listOfPlaces){
            List<String> keyValue = new ArrayList<String>(Arrays.asList(str.split(":")));
            i++;
            if(i%2 != 0)
                places += "<tr>";
            places += "<td>";
            if(keyValue.size()==2)
                places += keyValue.get(1);
            else
                places += "null";
            places += "</td>";
            //  places += "<td></td>";
            if(i%2==0)
                places += "</tr>";
        }
        places += "</tbody>\n" +
                "  </table>\n" +
                "</div>";

        return places;
    }

    public String getFormattedGeofences(String getData){
        String places="";
        String val="";
        int i=0;

        getData = getData.replace("[","");
        getData = getData.replace("]","");
        getData = getData.replace("{","");
        getData = getData.replace("}","");
        getData = getData.replace("\"","");
        getData = getData.replace("geofences:","");

        List<String> listOfItems = new ArrayList<String>(Arrays.asList(getData.split(",")));

        places += "<div class=\"table-responsive\">\n" +
                "  <table class=\"table table-striped\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Address Line One</th>\n" +
                "        <th>Place Id</th>\n" +
                "        <th>Place Attributes</th>\n" +
                "        <th>Geofence Type</th>\n" +
                "        <th>Geofence Radius</th>\n" +
                "        <th>Geofence Visibility</th>\n" +
                "        <th>Geofence Location</th></tr>\n" +
                "    </thead>\n" +
                "    <tbody>";

        for(String str: listOfItems){
            List<String> keyValue = new ArrayList<String>(Arrays.asList(str.split(":")));
            i++;

            if(str.contains("id:")) {
                i = 1;
                val = new String("");
                places += "</td>";
            }

            if(i>=8){
                if(i==8)
                    places += "<td>";
                for(String s: keyValue){
                    if(!s.equalsIgnoreCase("location")) {
                        val += s;
                        val +=" ";
                    }
                }

                if(i%2 !=0) {
                    places += val;
                    places += "<br>";
                }
            }
            else {
                switch (i) {
                    case 1:
                        places += "<tr>";
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";
                        break;

                    case 6:
                        if (keyValue.get(0).equalsIgnoreCase("geoFenceCircle")) {
                            places += "<td> Circle </td>";
                            places += "<td>";
                            if(keyValue.size()==3)
                                places += keyValue.get(2);
                            else
                                places += "null";
                            places += "</td>";
                        } else if (keyValue.get(0).equalsIgnoreCase("geoFencePolygon")) {
                            places += "<td> Polygon </td>";
                            places += "<td> NA </td>";
                        }
                        break;

                    case 9:
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";
                        places += "</tr>";
                        break;

                    default:
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";

                }
            }
        }

        places += "</tbody>\n" +
                "  </table>\n" +
                "</div>";

        return places;
    }

    public String getFormattedBeacons(String getData){
        String places="";
        int i=0;

        getData = getData.replace("[","");
        getData = getData.replace("]","");
        getData = getData.replace("{","");
        getData = getData.replace("}","");
        getData = getData.replace("\"","");

        List<String> listOfItems = new ArrayList<String>(Arrays.asList(getData.split(",")));

        places += "<div class=\"table-responsive\">\n" +
                "  <table class=\"table table-striped\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Factory Id</th>\n" +
                "        <th>Icon Url</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Latitude</th>\n" +
                "        <th>Longitude</th>\n" +
                "        <th>Visibility</th>\n" +
                "        <th>Battery Level</th>\n" +
                "        <th>Hardware</th>\n" +
                "        <th>Owner</th></tr>\n" +
                "    </thead>\n" +
                "    <tbody>";

        for(String str: listOfItems){
            List<String> keyValue = new ArrayList<String>(Arrays.asList(str.split(":")));
            i++;
            if(i==1)
                places += "<tr>";
            places += "<td>";
            if(keyValue.size()==2)
                places += keyValue.get(1);
            else
                places += "null";
            places += "</td>";
            //  places += "<td></td>";
            if(i==10)
                places += "</tr>";
        }
        places += "</tbody>\n" +
                "  </table>\n" +
                "</div>";

        return places;
    }
    // Added By Surbhi Garg
    public String getFormattedMap(String getData){
        String places="";
        String val="";
        int i=0;

        getData = getData.replace("[","");
        getData = getData.replace("]","");
        getData = getData.replace("{","");
        getData = getData.replace("}","");
        getData = getData.replace("\"","");
        //getData = getData.replace("geofences:","");

        List<String> listOfItems = new ArrayList<String>(Arrays.asList(getData.split(",")));

        places += "<div class=\"table-responsive\">\n" +
                "  <table class=\"table table-striped\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>MachineIdentifier</th>\n" +
                "        <th>ProcessIdentifier</th>\n" +
                "        <th>Counter</th>\n" +
                "        <th>TimeSecond</th>\n" +
                "        <th>Date</th>\n" +
                "        <th>Time</th>\n" +
                "        <th>Beacon Id</th>\n" +
                "        <th>No. of Sightings</th>\n" +
                "         <th>Created At</th>\n" +
                "         <th>Leave At</th></tr>\n" +
                //<th>No. of Sightings</th></th>\n" +
      /*  places += "<div class=\"table-responsive\">\n" +
                "  <table class=\"table table-striped\">\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>BeaconId</th>\n" +
                "        <th>NoOfSightings</th>\n" +
                "        <th>CreatedAt</th>\n" +
                "        <th>LeaveAt</th><tr>\n" +

               // "         <th>Value</th></tr>\n" +*/

                "    </thead>\n" +
                "    <tbody>";

        for(String str: listOfItems){
            List<String> keyValue = new ArrayList<String>(Arrays.asList(str.split(":")));
            i++;

            if(str.contains("id:")) {
                i = 1;
                val = new String("");
                places += "</td>";
            }

            if(i>=11){
                if(i==11)
                    places += "<td>";
                for(String s: keyValue){
                    if(!s.equalsIgnoreCase("value")) {
                        val += s;
                        val +=" ";
                    }
                }

                if(i%2 !=0) {
                    places += val;
                    places += "<br>";
                }
            }
            else {
                switch (i) {
                    case 1:
                        places += "<tr>";
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";
                        break;

                   /* case 6:
                        if (keyValue.get(0).equalsIgnoreCase("geoFenceCircle")) {
                            places += "<td> Circle </td>";
                            places += "<td>";
                            if(keyValue.size()==3)
                                places += keyValue.get(2);
                            else
                                places += "null";
                            places += "</td>";
                        } else if (keyValue.get(0).equalsIgnoreCase("geoFencePolygon")) {
                            places += "<td> Polygon </td>";
                            places += "<td> NA </td>";
                        }
                        break;*/

                    case 11:
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";
                        places += "</tr>";
                        break;

                    default:
                        places += "<td>";
                        if(keyValue.size()==2)
                            places += keyValue.get(1);
                        else
                            places += "null";
                        places += "</td>";

                }
            }
        }

        places += "</tbody>\n" +
                "  </table>\n" +
                "</div>";

        return places;
    }


}
