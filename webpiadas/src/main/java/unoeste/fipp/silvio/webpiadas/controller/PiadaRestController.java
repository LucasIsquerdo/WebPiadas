package unoeste.fipp.silvio.webpiadas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unoeste.fipp.silvio.webpiadas.models.Piada;
import unoeste.fipp.silvio.webpiadas.repositories.PiadaRepository;

@RestController
@RequestMapping("/apis")
public class PiadaRestController {
    
    @Autowired
    PiadaRepository piadaRepository; 
    
    @RequestMapping("/listar-todas-piadas")
    public ResponseEntity <Object> buscarTodas(@RequestParam(value = "filtro")String filtro)
    {   
        List<Piada> piadas = piadaRepository.findAllWithFilter(filtro);
        return new ResponseEntity<>(piadas,HttpStatus.OK);
    }

    @RequestMapping("/update")
    public ResponseEntity <Piada> update(@RequestParam(value = "id")Piada id)
    {   
        
        Optional<Piada> p = piadaRepository.findById(id.getId()); 
        int rank = p.get().getRanking();
        rank+=1;
        id.setCategoria(p.get().getCategoria());
        id.setKeywords(p.get().getKeywords());
        id.setRanking(rank);
        id.setTexto(p.get().getTexto());
        id.setTitulo(p.get().getTitulo());
        this.piadaRepository.save(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
        
    }

    @PostMapping("/piada")
    public Piada cadPiada(@RequestBody Piada piada) {return this.piadaRepository.save(piada);}
    
    

    
}
