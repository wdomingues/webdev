package controller;

import application.Funcionario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Arrays.asList;

/**
 *
 * @author fgenu
 */
public class Permissions {
    static void requireRole(HttpServletRequest request, String... role) {
        requireLogin(request);
        HttpSession session = request.getSession();
        Funcionario usuario = (Funcionario) session.getAttribute("usuario");
        boolean allowed = asList(role).contains(usuario.getPapel());
        if (!allowed) {
            throw new PermissionDeniedException("Permissões insuficientes para realizar a operação.");
        }
    }

    static void requireUserId(HttpServletRequest request, Integer... id) {
        requireLogin(request);
        HttpSession session = request.getSession();
        Funcionario usuario = (Funcionario) session.getAttribute("usuario");
        boolean allowed = asList(id).contains(usuario.getId());
        if (!allowed) {
            throw new PermissionDeniedException("Permissão negada ao usuário; operação não pôde ser realizada.");
        }
    }
    
    private static void requireLogin(HttpServletRequest request) {
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
