package unoeste.fipp.silvio.webpiadas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unoeste.fipp.silvio.webpiadas.models.Usuario;
import unoeste.fipp.silvio.webpiadas.repositories.UsuarioRepository;
import unoeste.fipp.silvio.webpiadas.security.JWTTokenProvider;

@RestController
@RequestMapping("/security")
public class SecurityRestController {

    @Autowired
    UsuarioRepository usuarioRepository; 
    
    @PostMapping("/autenticar")
    public ResponseEntity <Object> autenticar(String login, String senha)
    {
        List <Usuario> user = usuarioRepository.findAllWithFilter(senha);
        int i=0,pos=0;
        for(i=0;i<user.size();i++)
            if (senha.equals(user.get(i).getSenha())&&login.equals(user.get(i).getEmail()))
                    pos=1;
        String token="";
        if (pos==1)
        {
            token = JWTTokenProvider.getToken(senha, "ADM");
            System.out.println(token);
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("ACESSO NAO PERMITIDO",HttpStatus.NOT_ACCEPTABLE);
    }
}