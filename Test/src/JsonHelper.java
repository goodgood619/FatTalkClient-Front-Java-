import java.util.HashMap;
import org.json.simple.JSONObject;
public class JsonHelper {
	private JsonParser jsonParser;
	public String logininfo(String id,String password) {
		JSONObject jsonobject = new JSONObject();
		//HashMap<String,Object> hashMap = new HashMap<String, Object>();
		//hashMap.put("ID", id);
		//hashMap.put("Password",password);
		//jsonobject = new JSONObject(hashMap);
		jsonobject.put("ID",id);
		jsonobject.put("Password",password);
		return jsonobject.toString();
	}
	
	public HashMap<String,String> getlogininfo(String data) throws Exception{
		HashMap<String,String> retHashMap = new HashMap<String, String>();
		jsonParser = new JsonParser(data);
		retHashMap.put("NickName",jsonParser.GetstringValue("Nickname"));
		return retHashMap;
	}
}
