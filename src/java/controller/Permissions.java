package controller;

import application.Funcionario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fgenu
 */
public class Permissions {
    static void requireRole(String role, HttpServletRequest request) throws IOException {
        Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
        if (!usuario.getPapel().equals(role)) {
            throw new PermissionDeniedException("Permissões insuficientes para realizar a operação.");
        }
    }
}
