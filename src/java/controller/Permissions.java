package controller;

import application.Funcionario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fgenu
 */
public class Permissions {
    static void requireRole(HttpServletRequest request, String role) {
        requireLogin(request);
        HttpSession session = request.getSession();
        Funcionario usuario = (Funcionario) session.getAttribute("usuario");
        if (!usuario.getPapel().equals(role)) {
            throw new PermissionDeniedException("Permissões insuficientes para realizar a operação.");
        }
    }
    
    static void requireLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null) {
            throw new PermissionDeniedException("Sessão não encontrada; operação não pôde ser realizada.");
        } else {
            Funcionario usuario = (Funcionario) session.getAttribute("usuario");
            if(usuario == null) {
                throw new PermissionDeniedException("Usuário não encontrado; operação não pôde ser realizada.");
            }
        }
    }
}
