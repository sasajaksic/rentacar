package rentacar.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import rentacar.entity.Appuser;
import rentacar.repository.AppuserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthInterceptor implements HandlerInterceptor {

    private AppuserRepository _userRepository;

    private ProtectedRoute[] protectedRoutes = {
//            new ProtectedRoute("GET", "/myReservations/"),
            new ProtectedRoute("PUT", "/vehicle/{id}"),
            new ProtectedRoute("POST", "/vehicle/{id}"),
            new ProtectedRoute("DELETE", "/vehicle/{id}"),
            new ProtectedRoute("GET", "/appusers/"),
            new ProtectedRoute("GET", "/rents/"),
            new ProtectedRoute("GET", "/paymentMethods/"),
            new ProtectedRoute("GET", "/damages/"),
            new ProtectedRoute("GET", "/fines/"),
            new ProtectedRoute("GET", "/reports/"),
    };

    AuthInterceptor(AppuserRepository appuserRepository) {
        this._userRepository = appuserRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String requestMethod = request.getMethod();
        String requestRoute = request.getRequestURI();

        List<ProtectedRoute> tmpProtectedRoutes = new ArrayList<ProtectedRoute>();
        for (ProtectedRoute protectedRoute : protectedRoutes) {
            tmpProtectedRoutes.add(new ProtectedRoute(protectedRoute.getMethod(), protectedRoute.getRoute()));
        }


        LinkedHashMap<String, String> routeParams = (LinkedHashMap<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        for (Map.Entry<String, String> entry : routeParams.entrySet()) {
            for (ProtectedRoute protectedRoute : tmpProtectedRoutes) {
                if (protectedRoute.getRoute().contains("{" + entry.getKey() + "}")) {
                    protectedRoute.setRoute(protectedRoute.getRoute().replace("{" + entry.getKey() + "}", entry.getValue()));
                }
            }
        }

        for (ProtectedRoute protectedRoute : tmpProtectedRoutes) {
            if (requestMethod.equals(protectedRoute.getMethod()) && requestRoute.equals(protectedRoute.getRoute())) {
                return this.checkAuthentification(request, response);
            }
        }

        return true;
    }

    private boolean checkAuthentification(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getHeader("email");
        String password = request.getHeader("password");

        if (email == null || password == null) {
            response.sendError(401, "Authentication is required");
            return false;
        }

        Optional<Appuser> appUser = _userRepository.findByEmailContainingIgnoreCase(email).stream().findFirst();

        if (!appUser.isPresent()) {
            response.sendError(401, "User with email address " + email + " is not found");
            return false;
        }

        if (!appUser.get().getPassword().equals(password)) {
            response.sendError(401, "Wrong credentials");
            return false;
        }

        return true;
    }
}
