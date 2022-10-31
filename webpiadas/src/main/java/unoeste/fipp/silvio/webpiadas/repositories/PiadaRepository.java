package unoeste.fipp.silvio.webpiadas.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unoeste.fipp.silvio.webpiadas.models.Piada;

public interface PiadaRepository extends JpaRepository<Piada,Long>{

    @Query(value="SELECT * from piada p INNER JOIN categoria c on c.cat_id = p.cat_id WHERE p.pi_keywords LIKE %:filtro% or c.cat_nome LIKE %:filtro% ORDER BY pi_ranking DESC", nativeQuery = true)
        List<Piada> findAllWithFilter(@Param("filtro")String filtro);

    @Query(value="UPDATE piada SET pi_ranking = pi_ranking+1 WHERE pi_id = id ", nativeQuery = true)
        List<Piada> findById(@Param("id")long id);


    
}
