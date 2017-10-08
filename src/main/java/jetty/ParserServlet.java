package jetty;

import api.AdParseException;
import api.AdsParserFacade;
import api.Project;
import clientcli.ClientSettings;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

public class ParserServlet extends HttpServlet {

    private AdsParserFacade adsParserFacade;

    private static final long serialVersionUID = 1L;
    public ParserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        try {
            if(path.contains("/list")){
                doList(response);
            }else if(path.contains("/run")){
                doParsing(response);
            }else{
                response.getWriter().println("404");
            }
        } catch (AdParseException e) {
            response.getWriter().println("error -_-");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doList(HttpServletResponse response)
            throws UnsupportedEncodingException, IOException, AdParseException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ClientSettings settings = new ClientSettings();
        adsParserFacade = new AdsParserFacade(settings);
        Stream<Project> projs = adsParserFacade.listProjects();
        JSONArray jsonArray = new JSONArray();
        projs.forEach(p -> {
            JSONObject proj = new JSONObject();
            proj.put("id", p.getId());
            proj.put("name", p.getName());
            proj.put("url", p.getUrl());
            jsonArray.put(proj);
        });
        response.getWriter().println(jsonArray);
    }

    protected void doParsing(HttpServletResponse response)
            throws UnsupportedEncodingException, IOException, AdParseException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ClientSettings settings = new ClientSettings();
        adsParserFacade = new AdsParserFacade(settings);
        WebObserver ob = new WebObserver(response);
        adsParserFacade.addObserver(ob);
        adsParserFacade.runParsing();
        ob.print();
    }
}