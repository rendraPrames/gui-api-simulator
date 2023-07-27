package api;

public class ApiRequest {
    private String url;
    private String path;
    private Person requestBody;

    // Constructor, getters, setters, and other methods here

    public ApiRequest(String url, Person requestBody) {
        this.url = url;
        this.path = path;
        this.requestBody = requestBody;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Person getRequestBody() {
        return requestBody;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRequestBody(Person requestBody) {
        this.requestBody = requestBody;
    }
}
