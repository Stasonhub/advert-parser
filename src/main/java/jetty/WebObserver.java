package jetty;

import api.Advert;
import api.AdvertEvent;
import api.interfaces.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WebObserver implements Observer{

    private HttpServletResponse response;
    private JSONObject obj = new JSONObject();

    public WebObserver(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void update(List<Advert> ad, AdvertEvent type) {
        JSONArray array = new JSONArray();
        ad.forEach(array::put);
        obj.put(type.toString(), array);
    }

    public void print() throws IOException {
        response.getWriter().println(obj);

    }
}
