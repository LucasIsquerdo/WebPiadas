package unoeste.fipp.silvio.webpiadas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unoeste.fipp.silvio.webpiadas.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> 
{
    @Query(value="SELECT * FROM usuario u WHERE u.senha=senha",nativeQuery=true)
        public List<Usuario> findAllWithFilter(@Param("senha") String senha);
}
