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

    private WebObserver observer;
    private static final long serialVersionUID = 1L;
    public ParserServlet() {
        super();
        try {
            ClientSettings settings = new ClientSettings();
            adsParserFacade = new AdsParserFacade(settings);
            observer = new WebObserver();
            adsParserFacade.addObserver(observer);
        } catch (AdParseException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            if(path.contains("/list")){
                doProjs(request, response);
            }else if(path.contains("/run")){
                doParsing(response);
            }else{
                response.getWriter().println("404");
            }
        } catch (Exception e) {
            response.getWriter().println("error -_-");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void doProjs(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        if(!name.isEmpty() && !url.isEmpty()){
            adsParserFacade.createProject(new Project(0,name,url,true));
            response.getWriter().println("Project created.");
        }
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

    private void doParsing(HttpServletResponse response)
            throws UnsupportedEncodingException, IOException, AdParseException {
        adsParserFacade.runParsing();
        response.getWriter().println(observer.getObj());
    }

}
