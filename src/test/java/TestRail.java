import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestRail {
    APIClient client;
    Long runID;

    public TestRail() {
        client = new APIClient("https://matkovskaanna.testrail.net/");
        client.setUser("anuta07.05@gmail.com");
        client.setPassword("Anuta0705");
    }

    public void startRun(Integer projectID, String runName) throws Exception {

        Map data = new HashMap();
        data.put("name", runName);
        JSONObject r = (JSONObject) client.sendPost(String.format("add_run/%d", projectID), data);
        this.runID = (Long) r.get("id");

    }

    public void endRun() throws Exception {
        client.sendPost(String.format("close_run/%d", this.runID), new HashMap());
    }

    public void setResult(Integer caseID, Integer testNGResult)   {
        try {
        Map data = new HashMap();
        data.put("status_id", convertResult(testNGResult));
        client.sendPost(String.format("add_result_for_case/%d/%d", this.runID, caseID), data);
        }
        catch (java.lang.Exception e ) {
             }
    }

    private Integer convertResult(Integer testNGResult) {
        switch (testNGResult) {
            case 1:
                return 1;   // Success
            case 2:
                return 5;   // Failure
            case 3:
                return 2;   // Skip/Blocked
            default:
                return 4;  //Retest
        }
    }
}
