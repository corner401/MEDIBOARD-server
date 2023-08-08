package medicalboard.backend.dataController;

import medicalboard.backend.datamodel.Emergency;
import org.springframework.web.bind.annotation.RestController;
import medicalboard.backend.repository.EmergencyRepository;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;



@RestController
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepository;


    @Value("${api.serviceKey}")
    private String serviceKey;



    @RequestMapping("/emergency")
    public String emergency_save(Model model) throws Exception {


        StringBuffer sb = new StringBuffer("https://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire?");
        sb.append("serviceKey=");
        sb.append(serviceKey);
        sb.append("&pageNo=1&numOfRows=1000");

        URL url = new URL(sb.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/xml");
        conn.connect();


        // 응답으로 받은 XML 데이터 파싱 (JDOM 라이브러리)
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(conn.getInputStream());

        Element root = document.getRootElement();
        Element body = root.getChild("body");
        Element items = body.getChild("items");
        List<Element> item_list = items.getChildren("item");


        for (Element item : item_list) {

            String hpid = item.getChildText("hpid"); //기관코드
            String hvidate = item.getChildText("hvidate");
            String dutyname = item.getChildText("dutyName"); //병원명
            Integer hvec = Integer.parseInt(item.getChildText("hvec") == null ? "0" : item.getChildText("hvec")); //일반 - int
            Integer hvoc = Integer.parseInt(item.getChildText("hvoc") == null ? "0" : item.getChildText("hvoc")); //내과중환자실 Long
            Integer hvcc = Integer.parseInt(item.getChildText("hvcc") == null ? "0" : item.getChildText("hvcc")); //내과중환자실 Long
            Integer hvncc = Integer.parseInt(item.getChildText("hvncc") == null ? "0" : item.getChildText("hvncc")); //내과중환자실 Long
            Integer hvccc = Integer.parseInt(item.getChildText("hvccc") == null ? "0" : item.getChildText("hvccc")); //내과중환자실 Long
            Integer hvicc = Integer.parseInt(item.getChildText("hvicc") == null ? "0" : item.getChildText("hvicc"));
            Integer hvgc = Integer.parseInt(item.getChildText("hvgc") == null ? "0" : item.getChildText("hvgc")); //내과중환자실 Long
            String hvctayn = item.getChildText("hvctayn") == null ? "0" : item.getChildText("hvctayn"); //내과중환자실 Long
            String hvmriayn = item.getChildText("hvmriayn") == null ? "N" : item.getChildText("hvmriayn");
            String hvangioayn = item.getChildText("hvangioayn") == null ? "N" : item.getChildText("hvangioayn");
            String hvventiayn = item.getChildText("hvventiayn") == null ? "N" : item.getChildText("hvventiayn");
            String hvincuayn = item.getChildText("hvincuayn") == null ? "N" : item.getChildText("hvincuayn");
            String hvamyn = item.getChildText("hvamyn") == null ? "N" : item.getChildText("hvamyn");

            Integer hv2 = Integer.parseInt(item.getChildText("hv2") == null ? "0" : item.getChildText("hv2")); //내과중환자실 Long
            Integer hv3 = Integer.parseInt(item.getChildText("hv3") == null ? "0" : item.getChildText("hv3"));
            Integer hv4 = Integer.parseInt(item.getChildText("hv4") == null ? "0" : item.getChildText("hv4"));
            String hv5 = item.getChildText("hv5") == null ? "N1" : item.getChildText("hv5");
            Integer hv6 = Integer.parseInt(item.getChildText("hv6") == null ? "0" : item.getChildText("hv6"));
            Integer hv8 = Integer.parseInt(item.getChildText("hv8") == null ? "0" : item.getChildText("hv8"));
            Integer hv9 = Integer.parseInt(item.getChildText("hv9") == null ? "0" : item.getChildText("hv9"));
            String hv10 = item.getChildText("hv10") == null ? "N1" : item.getChildText("hv10"); //소아 유무
            String hv11 = item.getChildText("hv11") == null ? "N1" : item.getChildText("hv11");
            Integer hv13 = Integer.parseInt(item.getChildText("hv13") == null ? "0" : item.getChildText("hv13"));
            Integer hv14 = Integer.parseInt(item.getChildText("hv14") == null ? "0" : item.getChildText("hv14"));
            Integer hv15 = Integer.parseInt(item.getChildText("hv15") == null ? "0" : item.getChildText("hv15"));
            Integer hv16 = Integer.parseInt(item.getChildText("hv16") == null ? "0" : item.getChildText("hv16"));
            Integer hv17 = Integer.parseInt(item.getChildText("hv17") == null ? "0" : item.getChildText("hv17"));
            Integer hv18 = Integer.parseInt(item.getChildText("hv18") == null ? "0" : item.getChildText("hv18"));
            Integer hv19 = Integer.parseInt(item.getChildText("hv19") == null ? "0" : item.getChildText("hv19"));
            Integer hv21 = Integer.parseInt(item.getChildText("hv21") == null ? "0" : item.getChildText("hv21"));
            Integer hv22 = Integer.parseInt(item.getChildText("hv22") == null ? "0" : item.getChildText("hv22"));
            Integer hv23 = Integer.parseInt(item.getChildText("hv23") == null ? "0" : item.getChildText("hv23"));
            Integer hv24 = Integer.parseInt(item.getChildText("hv24") == null ? "0" : item.getChildText("hv24"));
            Integer hv25 = Integer.parseInt(item.getChildText("hv25") == null ? "0" : item.getChildText("hv25"));
            Integer hv26 = Integer.parseInt(item.getChildText("hv26") == null ? "0" : item.getChildText("hv26"));
            Integer hv29 = Integer.parseInt(item.getChildText("hv29") == null ? "0" : item.getChildText("hv29"));
            Integer hv30 = Integer.parseInt(item.getChildText("hv30") == null ? "0" : item.getChildText("hv30"));
            Integer hv31 = Integer.parseInt(item.getChildText("hv31") == null ? "0" : item.getChildText("hv31"));
            Integer hv32 = Integer.parseInt(item.getChildText("hv32") == null ? "0" : item.getChildText("hv32"));
            Integer hv33 = Integer.parseInt(item.getChildText("hv33") == null ? "0" : item.getChildText("hv33"));
            Integer hv36 = Integer.parseInt(item.getChildText("hv36") == null ? "0" : item.getChildText("hv36"));
            Integer hv37 = Integer.parseInt(item.getChildText("hv37") == null ? "0" : item.getChildText("hv37"));
            Integer hv38 = Integer.parseInt(item.getChildText("hv38") == null ? "0" : item.getChildText("hv38"));
            Integer hv40 = Integer.parseInt(item.getChildText("hv40") == null ? "0" : item.getChildText("hv40"));
            String dutytel3 = item.getChildText("dutytel3") == null ? "N" : item.getChildText("dutytel3");


            Emergency em = new Emergency(hpid, hvidate, dutyname, hvec, hvoc, hvcc, hvncc, hvccc, hvicc, hvgc, hvctayn, hvmriayn, hvangioayn, hvventiayn, hvincuayn
                    , hvamyn, hv2, hv3, hv4, hv5, hv6, hv8, hv9, hv10, hv11, hv13, hv14, hv15, hv16, hv17, hv18, hv19, hv21, hv22
                    , hv23, hv24, hv25, hv26, hv29, hv30, hv31, hv32, hv33, hv36, hv37, hv38, hv40, dutytel3);
            System.out.println(em);
            emergencyRepository.save(em);
        }

        return "index";
    }


}