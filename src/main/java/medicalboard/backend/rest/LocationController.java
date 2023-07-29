package medicalboard.backend.rest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import medicalboard.backend.model.HLocation;
import medicalboard.backend.repository.HLocationRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URL;

@RestController
public class LocationController {

    String tag_name = null;
    String hpid = "";
    String dutyname = "";
    String dutyAddr = "";
    String dutyDiv= "";
    String dutyDivName="";
    String dutyTel3="";
    String DUTYERYN="";
    String DUTYTIME1C="";
    String DUTYTIME2C="";
    String DUTYTIME3C="";
    String DUTYTIME4C="";
    String DUTYTIME5C="";
    String DUTYTIME6C="";
    String DUTYTIME7C="";
    String DUTYTIME8C="";
    double WGS84LAT= 0.0;
    double WGS84LON= 0.0;

    @Autowired
    private HLocationRepository hLocationRepository;

    @Value("${api.SeoulserviceKey}")
    private String SserviceKey;

    @RequestMapping("/location")
    public String location_save(Model model) throws Exception {
        String sb = "http://openapi.seoul.go.kr:8088/"
                + "serviceKey="
                + SserviceKey
                + "/xml/TvEmgcHospitalInfo/1/100/";

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        URL xmlUrl = new URL(sb);
        xmlUrl.openConnection().getInputStream();
        parser.setInput(xmlUrl.openStream(), "utf-8");
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                tag_name = parser.getName();
                System.out.println("tag:" + tag_name);
                String data = parser.getText();
                System.out.println("0" + data);
                switch (tag_name) {

                    case "RESULT":
                    case "MESSAGE":
                    case "CODE":
                        System.out.println("1: " + data);
                        break;
                    case "HPID":
                        hpid = data;//기관코드
                        System.out.println("2" + data);
                        break;
                    case "DUTYNAME":
                        dutyname = data;
                        System.out.println("3" + data);
                        break;
                    case "DUTYADDR":
                        dutyAddr = data;
                        break;
                    case "DUTYDIV":
                        dutyDiv = data;
                    case "DUTYDIVNAM":
                        dutyDivName = data;
                        break;
                    case "DUTYTEL3":
                        dutyTel3 = data;
                        break;
                    case "DUTYERYN":
                        DUTYERYN = data;
                        break;
                    case "DUTYTIME1C":
                        DUTYTIME1C = data;
                        break;
                    case "DUTYTIME2C":
                        DUTYTIME2C = data;
                        break;
                    case "DUTYTIME3C":
                        DUTYTIME3C = data;
                        break;
                    case "DUTYTIME4C":
                        DUTYTIME4C = data;
                        break;
                    case "DUTYTIME5C":
                        DUTYTIME5C = data;
                        break;
                    case "DUTYTIME6C":
                        DUTYTIME6C = data;
                        break;
                    case "DUTYTIME7C":
                        DUTYTIME7C = data;
                        break;
                    case "DUTYTIME8C":
                        DUTYTIME8C = data;
                        break;
                    case "WGS84LAT":
                        WGS84LAT = Long.parseLong(data);
                        break;
                    case "WGS84LON":
                        WGS84LON = Long.parseLong(data);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                eventType = parser.next();
            } else {
                HLocation hl = new HLocation(hpid, dutyname, dutyAddr, dutyDiv, dutyDivName, dutyTel3, DUTYERYN, DUTYTIME1C, DUTYTIME2C, DUTYTIME3C, DUTYTIME4C, DUTYTIME5C, DUTYTIME6C, DUTYTIME7C, DUTYTIME8C, WGS84LAT, WGS84LON);
                hLocationRepository.save(hl);
            }
        }
        return "location";
    }
}
