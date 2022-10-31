package unoeste.fipp.silvio.webpiadas.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unoeste.fipp.silvio.webpiadas.models.Usuario;
import unoeste.fipp.silvio.webpiadas.repositories.UsuarioRepository;

@RestController
@RequestMapping("/apis")
public class UsuarioRestController {

    @Autowired
    UsuarioRepository usuarioRepository; 

    // @RequestMapping("/listar-todos-usuarios")
    // public ResponseEntity <Object> buscarTodos(@RequestParam(value = "usenha,uemail")String email, String senha)
    // {   
    //     List<Usuario> user = usuarioRepository.findAllWithFilter(filtro);
    //     return new ResponseEntity<>(user,HttpStatus.OK);
    // }

    @PostMapping("/usuario")
    public Usuario CadUser(@RequestBody Usuario usuario) {return this.usuarioRepository.save(usuario);}
    
}
