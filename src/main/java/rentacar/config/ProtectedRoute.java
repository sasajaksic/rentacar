package rentacar.config;

public class ProtectedRoute {

    ProtectedRoute(String method, String route) {
        this.method = method;
        this.route = route;
    }

    String method;
    String route;

    public String getMethod() {
        return method;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
