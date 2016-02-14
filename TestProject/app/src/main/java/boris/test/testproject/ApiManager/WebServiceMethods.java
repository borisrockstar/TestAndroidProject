package boris.test.testproject.ApiManager;

import boris.test.testproject.model.SafeJSONObject;


public class WebServiceMethods {
	
	/**
	 * To get Australian States
	 * @return
	 */
		public SafeJSONObject createTrack() {
			String methodName = "api/create";
			String jsonObjSend = "phone=1234567890" + "&internationalCode=123";
			String jsonStringRecv = JSONParser.SendHttpPostRequest(methodName, jsonObjSend);
			if(jsonStringRecv == null)
				return null;
			SafeJSONObject object = new SafeJSONObject(jsonStringRecv);
			return object;
		}
}
