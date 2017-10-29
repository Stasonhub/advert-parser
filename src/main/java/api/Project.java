package api;


import org.json.JSONObject;

public class Project {


    private int id;
    private String name;
    private String url;
    private Boolean active;

    public Project(int id, String name, String url, Boolean active) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return id+". "+name+" -"+url;
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", getName());
        obj.put("url", getUrl());
        return obj;
    }
}
