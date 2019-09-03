import java.util.HashMap;
import org.json.simple.JSONObject;
public class JsonHelper {
	public String logininfo(String id,String password) {
		JSONObject jsonobject = new JSONObject();
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("ID", id);
		hashMap.put("Password",password);
		jsonobject = new JSONObject(hashMap);
		return jsonobject.toString();
	}
}
