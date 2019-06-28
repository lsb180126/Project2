package poly.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import poly.dto.AirplaneDTO;

public class Parse1 {
	
	private String getTagValue(String tag, Element e) {
		
		NodeList nodeList = e.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nodeList.item(0);
		if(nValue == null)
			
		 return null;
		
		
		return nValue.getNodeValue();
	}

	public String parse(String slt, String pageNo) {
		int page = 1;
		try {
			
			
		    StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=wLAm6CYpJB8hO6WXusHqrEepBnuUitjMDIzFrRcS8VnqPk%2Fubrdfl0XkJUp05gcuS3ceGSaqGBp93BvR4cdgXQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("schLineType","UTF-8") + "=" + URLEncoder.encode(slt, "UTF-8")); /*국내 / 국제*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); //page번호
	        
	       
	        URL url = new URL(urlBuilder.toString()); /*위의 urlBuilder를 하나로 합쳐서 url 이라는 변수에 저장*/
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");/*데이터를 받아오는 형식을 정함(GET or POST)*/
	        conn.setRequestProperty("Content-type", "application/xml");
	        System.out.println("Response code: " + conn.getResponseCode());/*응답(Response code)를 보여준느 부분*/
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();/*stringBuilder인 sb를 생성*/
	        String line; /*문자열 line 생성*/
	        while ((line = rd.readLine()) != null) {/*만약 rd로부터 받아온 readLine이 0이 아니면 sb에 line 값을 계속 추가*/
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
	        String url2 = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList?ServiceKey=wLAm6CYpJB8hO6WXusHqrEepBnuUitjMDIzFrRcS8VnqPk%2Fubrdfl0XkJUp05gcuS3ceGSaqGBp93BvR4cdgXQ%3D%3D&schLineType=" + slt + "&pageNo=" + page;
	        
	        /* xml 파싱 */
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url2); 
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			
			NodeList nodeList = doc.getElementsByTagName("item");/*xml태그이름 중  item 부분을 전부 긁어모음*/
			
			System.out.println("파싱할 리스트 수" + nodeList.getLength());
			
			for(int temp = 0; temp < nodeList.getLength(); temp++) {
				
				Node node = nodeList.item(temp); //태그안에 있는 요소를 가져오기위해 Element를 형변환 하여 다시 담아줌
				
				
				Element e = (Element)node;
				
				System.out.println("#################");
				
				
				
				System.out.println("항공사 : " + getTagValue("airlineKorean", e));
				String airlineKorean = CmmUtil.nvl(getTagValue("airlineKorean", e).toString());
				
				System.out.println("항공편명 : " + getTagValue("airFln", e));
				String airFln = CmmUtil.nvl(getTagValue("airFln", e).toString());
				
				System.out.println("출발공항 : " + getTagValue("boardingKor", e));
				String boardingKor = CmmUtil.nvl(getTagValue("boardingKor", e).toString());
				
				System.out.println("도착공항 : " + getTagValue("arrivedKor", e));
				String arrivedKor = CmmUtil.nvl(getTagValue("arrivedKor", e).toString());
				
				System.out.println("예정시간 : " + getTagValue("std", e));
				String std = CmmUtil.nvl(getTagValue("std", e).toString());
				
				
				
				
				
				AirplaneDTO AirplaneDTO = new AirplaneDTO();
				AirplaneDTO.setAirlineKorean(airlineKorean);
				AirplaneDTO.setAirFln(airFln);
				AirplaneDTO.setBoardingKor(boardingKor);
				AirplaneDTO.setArrivedKor(arrivedKor);
				AirplaneDTO.setStd(std);
				
				
				String hi1 = AirplaneDTO.getAirlineKorean();
				System.out.println(hi1);
				String hi2 = AirplaneDTO.getAirFln();
				System.out.println(hi2);
				String hi3 = AirplaneDTO.getBoardingKor();
				System.out.println(hi3);
				String hi4 = AirplaneDTO.getArrivedKor();
				System.out.println(hi4);
				String hi5 = AirplaneDTO.getStd();
				System.out.println(hi5);
				
				
				
				
				page ++;
				System.out.println("page number : "+page);
			}
			
			
			
			
			
			
		} catch(Exception e) {
			
			 e.printStackTrace();
		}

		return null;
	}

	

	

	
	
	
}
	
