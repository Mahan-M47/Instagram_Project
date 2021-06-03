package Client.Controller;

public class Response {
    private String title;
    private Data data;

    public Response(String title, Data data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public Data getData() {
        return data;
    }
}
